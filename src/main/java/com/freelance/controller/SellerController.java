package com.freelance.controller;

import com.freelance.dto.SellerDto;
import com.freelance.service.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.freelance.Constants.*;

@AllArgsConstructor
@RestController
@RequestMapping(SELLER_API)
public class SellerController {

    private SellerService sellerService;

    @GetMapping(GET_SELLER_API)
    public ResponseEntity<SellerDto> getSeller(@RequestParam Long id) {
        SellerDto sellerDto = sellerService.getSellerById(id);
        return ResponseEntity.ok(sellerDto);
    }

    @GetMapping(GET_SELLERS_API)
    public ResponseEntity<Page<SellerDto>> getAllSellers(@RequestParam Integer pageNo,
                                                         @RequestParam Integer pageSize) {
        Page<SellerDto> sellerDtoList = sellerService.getAllSellers(pageNo, pageSize);
        return ResponseEntity.ok(sellerDtoList);
    }

    @PutMapping(UPDATE_SELLER_API)
    public ResponseEntity<SellerDto> updateSellerDetails(
                                                        @RequestBody SellerDto newSellerDto) {
        SellerDto sellerDto = sellerService.updateSeller(newSellerDto);
        return ResponseEntity.ok(sellerDto);
    }

    @DeleteMapping(DELETE_SELLER_API)
    public ResponseEntity<String> deleteSeller(@RequestParam Long id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.ok("Seller with ID : " + id + " has been deleted.");
    }
}
