package com.freelance.controller;


import com.freelance.dto.BuyerDto;
import com.freelance.dto.SellerDto;
import com.freelance.dto.UserTokenizedDto;
import com.freelance.dto.auth.AuthDto;
import com.freelance.dto.auth.SignInDto;
import com.freelance.dto.auth.SignUpDto;
import com.freelance.entity.AuthEntity;
import com.freelance.entity.Buyer;
import com.freelance.entity.Seller;
import com.freelance.exception.InvalidOperationException;
import com.freelance.exception.ResourceNotFoundException;
import com.freelance.repository.BuyerRepository;
import com.freelance.repository.SellerRepository;
import com.freelance.service.AuthService;
import com.freelance.service.BuyerService;
import com.freelance.service.SellerService;
import com.freelance.service.TokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.freelance.dto.auth.UserRole.BUYER;
import static com.freelance.dto.auth.UserRole.SELLER;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService service;

    @Autowired
    private TokenProvider tokenService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto data) throws InvalidOperationException {
        ModelMapper modelMapper = new ModelMapper();
        if(BUYER.equals(data.getRole())){
            BuyerDto buyerDto = modelMapper.map(data, BuyerDto.class);
            buyerDto.setEmail(data.getUsername());
            BuyerDto generatedBuyerDto = buyerService.createBuyer(buyerDto);

            AuthDto authDto = modelMapper.map(data, AuthDto.class);
            authDto.setRoleId((generatedBuyerDto.getId()));
            service.signUp(authDto);
        }
        else if(SELLER.equals(data.getRole())){
            SellerDto sellerDto = modelMapper.map(data, SellerDto.class);
            sellerDto.setEmail(data.getUsername());
            SellerDto generatedSellerDto = sellerService.createSeller(sellerDto);

            AuthDto authDto = modelMapper.map(data, AuthDto.class);
            authDto.setRoleId((generatedSellerDto.getId()));
            service.signUp(authDto);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<UserTokenizedDto> signIn(@RequestBody SignInDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var authUser = authenticationManager.authenticate(usernamePassword);
        AuthEntity entity = (AuthEntity) authUser.getPrincipal();
        User user = new User(entity.getUsername(), entity.getPassword(), entity.getAuthorities());
        var accessToken = tokenService.generateAccessToken(user);
        UserTokenizedDto userTokenizedDto = null;
        if((BUYER.getValue()).equals(data.getRole())) {
            Buyer buyer = buyerRepository
                    .findById(entity.getRoleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Buyer with ID : " + entity.getRoleId() + " not found"));
            buyer.setEmail(entity.getUsername());
            userTokenizedDto = modelMapper.map(buyer, UserTokenizedDto.class);
            userTokenizedDto.setToken(accessToken);
        }
        else if((SELLER.getValue()).equals(data.getRole())) {
            Seller seller = sellerRepository
                    .findById(entity.getRoleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Seller with ID : " + entity.getRoleId() + " not found"));
            seller.setEmail(entity.getUsername());
            userTokenizedDto = modelMapper.map(seller, UserTokenizedDto.class);
            userTokenizedDto.setToken(accessToken);
        }
        return ResponseEntity.ok(userTokenizedDto);
    }
}
