package com.freelance.service;

import com.freelance.dto.BidDto;
import com.freelance.entity.Bid;
import com.freelance.entity.Buyer;
import com.freelance.entity.Project;
import com.freelance.exception.InvalidOperationException;
import com.freelance.exception.ResourceNotFoundException;
import com.freelance.repository.BidRepository;
import com.freelance.repository.BuyerRepository;
import com.freelance.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class BidServiceImpl implements BidService {

    private ModelMapper modelMapper;
    private BidRepository bidRepository;
    private ProjectRepository projectRepository;
    private BuyerRepository buyerRepository;

    @Override
    public BidDto createBid(BidDto bidDto) throws InvalidOperationException {
        Project project = projectRepository
                .findById(bidDto.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project with ID : " + bidDto.getProjectId() + " not found."));

        if (project.getLastDateForBidding().isBefore(Instant.now()))
            throw new InvalidOperationException("Deadline for creating the bid has crossed.");

        long count = bidRepository.countByBuyerIdAndProjectId(bidDto.getBuyerId(), bidDto.getProjectId());
        if (count != 0)
            throw new InvalidOperationException("Buyer cannot place more than 1 bid for same project");

        Bid bid = modelMapper.map(bidDto, Bid.class);
        Bid savedBid = bidRepository.save(bid);
        return modelMapper.map(savedBid, BidDto.class);
    }

    @Override
    public BidDto getBidById(Long bidId) {
        Bid bid = bidRepository.findById(bidId)
                .orElseThrow(() -> new ResourceNotFoundException("Bid ID : " + bidId + " not present."));
        BidDto bidDto = modelMapper.map(bid, BidDto.class);
        return bidDto;
    }

    @Override
    public BidDto getBidByBuyerIdAndProjectId(Long buyerId, Long projectId) {
        Bid bid = bidRepository.findByBuyerIdAndProjectId(buyerId, projectId);
        if (bid == null)
            return null;
        BidDto bidDto = modelMapper.map(bid, BidDto.class);
        return bidDto;
    }

    @Override
    public Page<BidDto> getBidsByProjectId(Long projectId, Integer pageNo, Integer pageSize) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project ID : " + projectId + " not present."));

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Bid> bidsPage = bidRepository.findByProjectId(projectId, pageable);
        Page<BidDto> bidsDto = bidsPage.map(bid -> modelMapper.map(bid, BidDto.class));
        return bidsDto;
    }

    @Override
    public Page<BidDto> getBidsByBuyerId(Long buyerId, Integer pageNo, Integer pageSize) {
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer ID : " + buyerId + " not present."));

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Bid> bidsPage = bidRepository.findByBuyerId(buyerId, pageable);
        Page<BidDto> bidsDto = bidsPage.map(bid -> modelMapper.map(bid, BidDto.class));
        return bidsDto;
    }

    @Override
    public BidDto updateBid(BidDto newBidDto) throws InvalidOperationException {
        Bid oldBid = bidRepository.findById(newBidDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bid with bidId : " + newBidDto.getId() + " not found."));

        if (!oldBid.getBuyerId().equals(newBidDto.getBuyerId()))
            throw new InvalidOperationException("Seller cannot be changed.");
        if (!oldBid.getProjectId().equals(newBidDto.getProjectId()))
            throw new InvalidOperationException("Project cannot be changed.");

        Project project = projectRepository
                .findById(oldBid.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project with ID : " + oldBid.getProjectId() + " not found."));
        if (project.getLastDateForBidding().isBefore(Instant.now()))
            throw new InvalidOperationException("Deadline for updating the bid has crossed.");

        oldBid.setCost(newBidDto.getCost());
        oldBid.setIsPerHour(newBidDto.getIsPerHour());

        Bid bid = bidRepository.save(oldBid);
        return modelMapper.map(bid, BidDto.class);
    }

    @Override
    public void deleteBid(Long bidId) {
        Bid bid = bidRepository.findById(bidId)
                .orElseThrow(() -> new ResourceNotFoundException("Bid with ID : " + bidId + " not present."));
        bidRepository.deleteById(bidId);
    }
}
