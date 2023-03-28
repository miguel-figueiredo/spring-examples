package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Fish {
    @Id
    private Long id;
    private String name;
}
