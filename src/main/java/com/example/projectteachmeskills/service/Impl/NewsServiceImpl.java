package com.example.projectteachmeskills.service.Impl;

import com.example.projectteachmeskills.entity.News;
import com.example.projectteachmeskills.repositrory.NewsRepository;
import com.example.projectteachmeskills.service.NewsServise;
import com.example.projectteachmeskills.util.NewsByIdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class NewsServiceImpl implements NewsServise {

    private final NewsRepository newsRepository;

    @Override
    @Transactional
    public void save(News news) {
        newsRepository.save(news);
    }

    @Override
    @Transactional
    public void saveAll(List<News> news) {
        newsRepository.saveAll(news);
    }

    @Override
    public News findById(Long id) {
        Optional<News> foundNews=newsRepository.findById(id);
        return foundNews.orElseThrow(NewsByIdNotFoundException::new);
    }

    @Override
    public Optional<News> findByTitle(String title) {
        Optional<News> foundNews= newsRepository.findByTitle(title);
        return foundNews;
    }

    @Override
    public Optional<News> findByTitleContaining(String substring) {
        Optional<News> foundNews= newsRepository.findByTitleContaining(substring);
        return foundNews;
    }

    @Override
    public List<News> findByCategory(String category){
        return newsRepository.findByCategory(category);
    }
    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public void deleteById(Long id){
        newsRepository.deleteById(id);
    }
}
