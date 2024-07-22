package com.freelance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BidDto {
    private Long id;
    private Long projectId;
    private Long buyerId;
    private Double cost;
    private Boolean isPerHour;
    private Boolean isSelected;
}
