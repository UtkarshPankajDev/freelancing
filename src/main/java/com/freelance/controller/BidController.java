package com.freelance.controller;

import com.freelance.dto.BidDto;
import com.freelance.exception.InvalidOperationException;
import com.freelance.exception.ResourceNotFoundException;
import com.freelance.service.BidService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.freelance.Constants.*;

@AllArgsConstructor
@RestController
@RequestMapping(BID_API)
public class BidController {

    private BidService bidService;

    @PostMapping(CREATE_BID_API)
    public ResponseEntity<BidDto> createBid(@RequestBody BidDto bidDto) throws InvalidOperationException {
        BidDto savedBid = bidService.createBid(bidDto);
        return new ResponseEntity<BidDto>(savedBid, HttpStatus.CREATED);
    }

    @GetMapping(GET_BY_BID_ID_API)
    public ResponseEntity<BidDto> getBid(@RequestParam Long bidId) {
        BidDto bidDto = bidService.getBidById(bidId);
        return ResponseEntity.ok(bidDto);
    }

    @GetMapping(GET_BID_BY_BUYER_AND_PROJECT_API)
    public ResponseEntity<BidDto> isBidByBuyerForProject(@RequestParam Long buyerId,
                                                         @RequestParam Long projectId) {
        BidDto bidDto = bidService.getBidByBuyerIdAndProjectId(buyerId, projectId);
        if (bidDto == null)
            throw new ResourceNotFoundException("No bids created for projectId : " + projectId + " by buyerId : " + buyerId);
        return ResponseEntity.ok(bidDto);
    }

    @GetMapping(GET_BIDS_BY_PROJECT_API)
    public ResponseEntity<Page<BidDto>> getBidsByProjectId(@RequestParam Long projectId,
                                                           @RequestParam Integer pageNo,
                                                           @RequestParam Integer pageSize) {
        Page<BidDto> bidsDto = bidService.getBidsByProjectId(projectId, pageNo, pageSize);
        return ResponseEntity.ok(bidsDto);
    }

    @GetMapping(GET_BIDS_BY_BUYER_API)
    public ResponseEntity<Page<BidDto>> getBidsByBuyerId(@RequestParam Long buyerId,
                                                         @RequestParam Integer pageNo,
                                                         @RequestParam Integer pageSize) {
        Page<BidDto> bidsDto = bidService.getBidsByBuyerId(buyerId, pageNo, pageSize);
        return ResponseEntity.ok(bidsDto);
    }

    @PutMapping(UPDATE_BID_API)
    public ResponseEntity<BidDto> updateBidDetails(@RequestBody BidDto newBidDto) throws InvalidOperationException {
        BidDto bidDto = bidService.updateBid(newBidDto);
        return ResponseEntity.ok(bidDto);
    }

    @DeleteMapping(DELETE_BID_API)
    public ResponseEntity<String> deleteBid(@RequestParam Long bidId) {
        bidService.deleteBid(bidId);
        return ResponseEntity.ok("Bid with ID : " + bidId + " has been deleted.");
    }
}
