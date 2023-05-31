package com.example.pagination;

import lombok.Getter;

@Getter
public class OceanFishPair {

    private Ocean ocean;
    private Fish fish;

    public OceanFishPair(Ocean ocean, Fish fish) {
        this.ocean = ocean;
        this.fish = fish;
    }
}