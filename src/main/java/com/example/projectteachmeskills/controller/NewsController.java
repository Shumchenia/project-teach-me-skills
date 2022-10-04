package com.example.projectteachmeskills.controller;

import com.example.projectteachmeskills.entity.News;
import com.example.projectteachmeskills.service.NewsService;
import com.example.projectteachmeskills.util.NewsByIdNotFoundException;
import com.example.projectteachmeskills.util.NewsErrorResponce;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public List<News> findAll(){
        return newsService.findAll();
    }

    @GetMapping("/{id}")
    public News findById(@PathVariable("id") Long id){
        return newsService.findById(id);
    }

    @GetMapping("/category/{category}")
    public List<News> findByCategory(@PathVariable("category") String category){
        return newsService.findByCategory(category);
    }
    @GetMapping("/containing/{containing}")
    public List<News> findByTitleContaining(@PathVariable("containing") String substring){
        return newsService.findByTitleContaining(substring);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        newsService.deleteById(id);
    }


    @ExceptionHandler
    private ResponseEntity<NewsErrorResponce> handleException(NewsByIdNotFoundException e){
        NewsErrorResponce responce = new NewsErrorResponce(
                "News with this id wasn't found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responce, HttpStatus.NOT_FOUND);
    }
}
