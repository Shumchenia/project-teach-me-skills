package com.example.projectteachmeskills.repositrory;

import com.example.projectteachmeskills.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {

    Optional<News> findByTitleContaining(String substring);

    List<News> findByCategory(String category);

    Optional<News> findByTitle(String title);
}
