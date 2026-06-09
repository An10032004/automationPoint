package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "essay")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Essay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Double score;

    @Column(columnDefinition = "TEXT")
    private String feedback;
}
