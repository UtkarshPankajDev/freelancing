package com.freelance.repository;

import com.freelance.entity.Bid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
    Page<Bid> findByProjectId(Long projectId, Pageable pageable);
    Page<Bid> findByBuyerId(Long buyerId, Pageable pageable);
    void deleteByProjectId(Long projectId);
    void deleteAllBidsByBuyerId(Long buyerId);
    long countByBuyerIdAndProjectId(Long buyerId, Long projectId);
    Bid findByBuyerIdAndProjectId(Long buyerId, Long projectId);
}
