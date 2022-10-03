package com.example.projectteachmeskills.controllers;

import com.example.projectteachmeskills.entity.News;
import com.example.projectteachmeskills.service.NewsServise;
import com.example.projectteachmeskills.util.NewsByIdNotFoundException;
import com.example.projectteachmeskills.util.NewsErrorResponce;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsServise newsServise;

    @GetMapping
    public List<News> findAll(){
        return newsServise.findAll();
    }

    @GetMapping("/{id}")
    public News findById(@PathVariable("id") Long id){
        return newsServise.findById(id);
    }

    @GetMapping("/category/{category}")
    public List<News> findByCategory(@PathVariable("category") String category){
        return newsServise.findByCategory(category);
    }

    @GetMapping("/containing/{containing}")
    public Optional<News> findByTitleContaining(@PathVariable("containing") String substring){
        return newsServise.findByTitleContaining(substring);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        newsServise.deleteById(id);
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
