package com.freelance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bid",
        indexes = {@Index(name = "project_id_index",  columnList="project_id"),
                @Index(name = "buyer_id_index", columnList="buyer_id")})
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "buyer_id", nullable = false)
    private Long buyerId;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @Column(name = "is_per_hour", nullable = false)
    private Boolean isPerHour;

    @Column(name = "is_selected", nullable = false)
    private Boolean isSelected = false;

//    @Version
//    private String version;
}
