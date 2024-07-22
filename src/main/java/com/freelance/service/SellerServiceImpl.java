package com.freelance.service;

import com.freelance.dto.SellerDto;
import com.freelance.entity.Seller;
import com.freelance.exception.ResourceNotFoundException;
import com.freelance.repository.SellerRepository;

import lombok.AllArgsConstructor;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService {

    private SellerRepository sellerRepository;
    private ModelMapper modelMapper;

    @Override
    public SellerDto createSeller(SellerDto sellerDto) {
        Seller seller = modelMapper.map(sellerDto, Seller.class);
        Seller savedSeller = sellerRepository.save(seller);
        return modelMapper.map(savedSeller, SellerDto.class);
    }

    @Override
    public SellerDto getSellerById(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Seller ID : " + sellerId + "does not exist."));
        SellerDto sellerDto = modelMapper.map(seller, SellerDto.class);
        return sellerDto;
    }

    @Override
    public Page<SellerDto> getAllSellers(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Seller> sellersPage = sellerRepository.findAll(pageable);
        Page<SellerDto> sellerDtosPage = sellersPage.map(seller -> modelMapper.map(seller, SellerDto.class));
        return sellerDtosPage;
    }

    @Override
    public SellerDto updateSeller(SellerDto newSellerDto) {
        sellerRepository.findById(newSellerDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Seller ID : " + newSellerDto.getId() + "does not exist."));

        Seller seller = sellerRepository.save(modelMapper.map(newSellerDto, Seller.class));
        SellerDto sellerDto = modelMapper.map(seller, SellerDto.class);
        return sellerDto;
    }

    @Override
    public void deleteSeller(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Seller ID : " + sellerId + "does not exist."));

        sellerRepository.deleteById(sellerId);
    }
}
