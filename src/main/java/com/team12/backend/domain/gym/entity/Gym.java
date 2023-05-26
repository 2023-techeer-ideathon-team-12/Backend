package com.team12.backend.domain.gym.entity;

import com.sun.istack.NotNull;
import com.team12.backend.domain.review.entity.Reviews;
import com.team12.backend.global.common.BaseEntity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "gyms")
public class Gym extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gym_id")
    private Long id;

    @Column(name = "gym_name")
//    @NotNull
    private String name;

    @Column
//    @NotNull
    @ColumnDefault("0")
    private String address;

    @Column(name = "currentUser_number")
//    @NotNull
    private Integer currentUser;

    @Column
//    @NotNull
    private Integer area;

    @Column
//    @NotNull
    private String machine;

//    @Column
    @NotNull
    private Double starAvg;

    @Column
//    @NotNull
    private String thumbnailUrl;

    @Column(name = "gym_latitude")
//    @NotNull
    private Double latitude;

    @Column(name = "gym_longitude")
//    @NotNull
    private Double longitude;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reviews> reviews ;


    @Builder
    public Gym(
        String name,
        String address,
        Integer currentUser,
        Integer area,
        String machine,
        Double starAvg,
        String thumbnailUrl,
        Double latitude,
        Double longitude
    ) {
        this.name = name;
        this.address = address;
        this.currentUser = currentUser;
        this.area = area;
        this.machine = machine;
        this. starAvg = starAvg;
        this.thumbnailUrl = thumbnailUrl;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void increaseCurrentUser() {
        this.currentUser += 1;
    }

    public void decreaseCurrentUser() {
        this.currentUser -= 1;
    }
    public void setStarAvg(Double starAvg) {
        this.starAvg = starAvg;
    }
}
