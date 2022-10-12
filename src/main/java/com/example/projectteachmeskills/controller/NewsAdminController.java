package com.example.projectteachmeskills.controller;

import com.example.projectteachmeskills.dto.NewsDTO;
import com.example.projectteachmeskills.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/news")
public class NewsAdminController {

    private final NewsService newsService;

    @PostMapping
    public void news(@RequestBody NewsDTO news){
        System.out.println(news);
        newsService.save(news);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        newsService.deleteById(id);
    }

}
