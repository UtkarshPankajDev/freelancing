package com.freelance.service;

import com.freelance.dto.BidDto;
import com.freelance.exception.InvalidOperationException;
import org.springframework.data.domain.Page;

public interface BidService {
    BidDto createBid(BidDto bidDto) throws InvalidOperationException;
    BidDto getBidById(Long bidId);
    BidDto getBidByBuyerIdAndProjectId(Long buyerId, Long projectId);
    Page<BidDto> getBidsByProjectId(Long projectId, Integer pageNo, Integer pageSize);
    Page<BidDto> getBidsByBuyerId(Long buyerId, Integer pageNo, Integer pageSize);
    BidDto updateBid(BidDto newBidDto) throws InvalidOperationException;
    void deleteBid(Long bidId);
}
