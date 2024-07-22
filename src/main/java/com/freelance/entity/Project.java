package com.freelance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO : ManyToOne? Dekh lenge
    // @OneToOne(fetch = FetchType.LAZY)
    @Column(name = "seller_id", nullable = false)
    private Long sellerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date_of_posting", nullable = false)
    private Instant dateOfPosting;

    @Column(name = "last_date_for_bidding", nullable = false)
    private Instant lastDateForBidding;

    @Column(name = "estimate_days_for_completion", nullable = false)
    private Long estimateDaysForCompletion;
}
