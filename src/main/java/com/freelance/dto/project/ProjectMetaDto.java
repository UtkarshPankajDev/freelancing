package com.freelance.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMetaDto {
    private Long sellerId;
    private String name;
    private String description;
    private Instant lastDateForBidding;
    private Long estimateDaysForCompletion;
}
