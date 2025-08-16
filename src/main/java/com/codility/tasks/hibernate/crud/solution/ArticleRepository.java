package com.codility.tasks.hibernate.crud.solution;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitleContaining(String title);
}
