package com.example.pagination;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Ocean implements Comparable<Ocean> {
    @Id
    private UUID id;

    private String name;

    @Transient
    private List<Fish> fishList;

    @Override
    public int compareTo(final Ocean o) {
        return name.compareTo(o.getName());
    }
}
