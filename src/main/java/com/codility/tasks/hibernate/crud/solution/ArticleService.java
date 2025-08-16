package com.codility.tasks.hibernate.crud.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
class ArticleService {
    @Value("${articles.blacklist}")
    String[] blacklist;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    TagRepository tagRepository;

    public Optional<ArticleDTO> findById(Long id) {
        return articleRepository.findById(id).map(this::mapToDTO);
    }

    public List<ArticleDTO> findByTitle(String title) {
        return articleRepository.findByTitleContaining(title).stream().map(this::mapToDTO).toList();
    }

    @Transactional
    public Long create(ArticleDTO articleDTO) {
        if(Arrays.stream(blacklist).anyMatch(banString->
                articleDTO.getContent().contains(banString)
        )){
            throw new RuntimeException("Article content contain forbidden word");
        }
        return articleRepository.save(
                Article.builder()
                        .title(articleDTO.getTitle())
                        .content(articleDTO.getContent())
                        .tags(articleDTO.getTags().stream().map(tag->Tag.builder().tag(tag).build()).toList())
                        .build())
                .getId();
    }


    @Transactional
    public void update(Long id, ArticleDTO articleDTO) {
        if(Arrays.stream(blacklist).anyMatch(banString->
                articleDTO.getContent().contains(banString)
        )){
            throw new RuntimeException("Article content contain forbidden word");
        }

        articleRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        articleRepository.save(
                Article.builder()
                        .id(id)
                        .title(articleDTO.getTitle())
                        .content(articleDTO.getContent())
                        .build()
        );

        articleDTO.getTags().stream().map(tag->Tag.builder().article(Article.builder().id(id).build()).tag(tag).build())
                .forEach(tagRepository::save);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    public ArticleDTO mapToDTO(Article article) {
        return ArticleDTO.builder()
                .title(article.getTitle())
                .content(article.getContent())
                .tags(article.getTags().stream().map(Tag::getTag).toList())
                .build();
    }
}

