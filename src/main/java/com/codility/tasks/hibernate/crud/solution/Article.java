package com.codility.tasks.hibernate.crud.solution;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @OneToMany(mappedBy = "article", orphanRemoval = true , cascade = CascadeType.PERSIST)
    private List<Tag> tags;
}

