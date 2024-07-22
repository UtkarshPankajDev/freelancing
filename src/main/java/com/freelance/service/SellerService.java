package com.freelance.service;

import com.freelance.dto.SellerDto;
import org.springframework.data.domain.Page;

public interface SellerService {
    SellerDto createSeller(SellerDto sellerDto);
    SellerDto getSellerById(Long sellerId);
    Page<SellerDto> getAllSellers(Integer pageNo, Integer pageSize);
    SellerDto updateSeller(SellerDto newSellerDto);
    void deleteSeller(Long sellerId);
}
