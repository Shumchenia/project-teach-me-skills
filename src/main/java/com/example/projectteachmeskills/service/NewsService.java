package com.example.projectteachmeskills.service;

import com.example.projectteachmeskills.entity.News;

import java.util.List;
import java.util.Optional;

public interface NewsService {

    News save(News news);

    News save(Long id,News news);

    News findById(Long id);

    Optional<News> findByTitle(String title);

    List<News> findByCategory(String category);

    List<News> findByTitleContaining(String substring);

    List<News> findAll();

    void deleteById(Long id);


}
