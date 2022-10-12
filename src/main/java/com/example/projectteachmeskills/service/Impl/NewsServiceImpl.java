package com.example.projectteachmeskills.service.Impl;

import com.example.projectteachmeskills.dto.NewsDTO;
import com.example.projectteachmeskills.entity.News;
import com.example.projectteachmeskills.mapper.NewsMapper;
import com.example.projectteachmeskills.repositrory.NewsRepository;
import com.example.projectteachmeskills.service.NewsService;
import com.example.projectteachmeskills.util.NewsByIdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    private final NewsMapper newsMapper;

    @Override
    @Transactional
    public void save(NewsDTO newsDTO) {
        News news =newsMapper.toNews(newsDTO);
        System.out.println(news);
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
        return newsRepository.findByTitle(title);
    }

    @Override
    public List<News> findByTitleContaining(String substring) {
        return newsRepository.findByTitleContaining(substring)
                .stream().collect(Collectors.toList());
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
    @Transactional
    public void deleteById(Long id){
        newsRepository.deleteById(id);
    }
}
