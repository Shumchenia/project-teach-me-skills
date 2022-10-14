package com.example.projectteachmeskills.service.Impl;

import com.example.projectteachmeskills.entity.News;
import com.example.projectteachmeskills.repositrory.NewsRepository;
import com.example.projectteachmeskills.service.NewsService;
import com.example.projectteachmeskills.util.NewsByCategoryNotFoundException;
import com.example.projectteachmeskills.util.NewsByContainingNotFoundException;
import com.example.projectteachmeskills.util.NewsByIdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    @Transactional
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News save(Long id, News news) {
        if (!id.equals(news.getId())) {
            news.setId(id);
        }
       return save(news);
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
        List<News> news= new ArrayList<>(newsRepository.findByTitleContaining(substring));
        if(news.isEmpty()){
            throw new NewsByContainingNotFoundException();
        }
        return news;
    }

    @Override
    public List<News> findByCategory(String category){
        List<News> news= new ArrayList<>(newsRepository.findByCategory(category));
        if(news.isEmpty()){
            throw new NewsByCategoryNotFoundException();
        }
        return news;
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
