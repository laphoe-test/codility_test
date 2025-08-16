package com.codility.tasks.hibernate.crud.solution;

import lombok.*;

import java.util.List;


@Data
@Getter
@Setter
@Builder
class ArticleDTO {
    private String title;
    private String content;
    private List<String> tags;
}


