package com.freelance.controller;

import com.freelance.dto.BuyerDto;
import com.freelance.service.BidService;
import com.freelance.service.BuyerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.freelance.Constants.*;

@AllArgsConstructor
@RestController // This class now becomes a Spring MVC Rest Controller and it is now capable to handle HTTP Requests
@RequestMapping(BUYER_API)
public class BuyerController {

    private BuyerService buyerService;

    @GetMapping(GET_BUYER_API)
    public ResponseEntity<BuyerDto> getBuyer(@RequestParam Long id) {
        BuyerDto buyerDto = buyerService.getBuyerById(id);
        return ResponseEntity.ok(buyerDto);
    }

    @GetMapping(GET_BUYERS_API)
    public ResponseEntity<Page<BuyerDto>> getAllBuyers(@RequestParam Integer pageNo,
                                                 @RequestParam Integer pageSize) {
        Page<BuyerDto> buyersDtoList = buyerService.getAllBuyers(pageNo, pageSize);
        return ResponseEntity.ok(buyersDtoList);
    }

    @PutMapping(UPDATE_BUYER_API)
    public ResponseEntity<BuyerDto> updateBuyerDetails(@RequestBody BuyerDto newBuyerDto) {
        BuyerDto buyerDto = buyerService.updateBuyer(newBuyerDto);
        return ResponseEntity.ok(buyerDto);
    }

    @DeleteMapping(DELETE_BUYER_API)
    public ResponseEntity<String> deleteBuyer(@RequestParam Long id) {
        buyerService.deleteBuyer(id);
        return ResponseEntity.ok("Buyer with ID : " + id + " has been deleted.");
    }
}
