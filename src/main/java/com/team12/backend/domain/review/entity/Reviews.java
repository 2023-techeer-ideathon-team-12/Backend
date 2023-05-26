package com.team12.backend.domain.review.entity;


import com.team12.backend.domain.gym.entity.Gym;
import com.team12.backend.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@AllArgsConstructor
@Entity
@Setter
@Builder
@NoArgsConstructor
@Table(name="reviews")
public class Reviews extends BaseEntity {

    @Id
    @Column(name = "review_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym")
    private Gym gym;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "star", nullable = false)
    private int star;

}
