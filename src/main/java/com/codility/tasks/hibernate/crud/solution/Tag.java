package com.codility.tasks.hibernate.crud.solution;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tag;

    @ManyToOne
    @JoinColumn(name="article_id", nullable = false)
    private Article article;

}
