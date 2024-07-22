package com.freelance.service;

import com.freelance.dto.BuyerDto;
import org.springframework.data.domain.Page;

public interface BuyerService {
    BuyerDto createBuyer(BuyerDto buyerDto);
    BuyerDto getBuyerById(Long buyerId);
    BuyerDto getBuyerByEmail(String email);
    Page<BuyerDto> getAllBuyers(Integer pageNo, Integer pageSize);
    BuyerDto updateBuyer(BuyerDto newBuyerDto);
    void deleteBuyer(Long buyerId);
}
