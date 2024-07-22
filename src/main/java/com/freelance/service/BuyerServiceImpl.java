package com.freelance.service;

import com.freelance.dto.BuyerDto;
import com.freelance.entity.Buyer;
import com.freelance.exception.ResourceNotFoundException;
import com.freelance.repository.BidRepository;
import com.freelance.repository.BuyerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service // It tells the spring container to create the spring bean for BuyerServiceImpl class
@AllArgsConstructor
public class BuyerServiceImpl implements BuyerService {

    private BuyerRepository buyerRepository;
    private BidRepository bidRepository;
    private ModelMapper modelMapper;

    @Override
    public BuyerDto createBuyer(BuyerDto buyerDto) {
        Buyer buyer = modelMapper.map(buyerDto, Buyer.class);
        Buyer savedBuyer = buyerRepository.save(buyer);
        return modelMapper.map(savedBuyer, BuyerDto.class);
    }

    @Override
    public BuyerDto getBuyerById(Long buyerId) {
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer ID : " + buyerId + "does not exist."));
        BuyerDto buyerDto = modelMapper.map(buyer, BuyerDto.class);
        return buyerDto;
    }

    @Override
    public BuyerDto getBuyerByEmail(String email) {
        Buyer buyer = buyerRepository.findByEmail(email);
        BuyerDto buyerDto = modelMapper.map(buyer, BuyerDto.class);
        return buyerDto;
    }

    @Override
    public Page<BuyerDto> getAllBuyers(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Buyer> buyersPage = buyerRepository.findAll(pageable);
        // TODO : Debug this
        Page<BuyerDto> buyersDtoPage = buyersPage.map(buyer -> modelMapper.map(buyer, BuyerDto.class));
        return buyersDtoPage;
    }

    @Override
    public BuyerDto updateBuyer(BuyerDto newBuyerDto) {
        buyerRepository.findById(newBuyerDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Buyer ID : " + newBuyerDto.getId() + "does not exist."));

        Buyer buyer = buyerRepository.save(modelMapper.map(newBuyerDto, Buyer.class));
        BuyerDto buyerDto = modelMapper.map(buyer, BuyerDto.class);
        return buyerDto;
    }

    @Override
    public void deleteBuyer(Long buyerId) {
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer ID : " + buyerId + "does not exist."));

        // TODO : Check if this is working
        bidRepository.deleteAllBidsByBuyerId(buyerId);
        buyerRepository.deleteById(buyerId);
    }
}
