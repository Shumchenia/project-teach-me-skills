package com.example.projectteachmeskills.mapper;


import com.example.projectteachmeskills.dto.NewsDTO;
import com.example.projectteachmeskills.dto.UserDTO;
import com.example.projectteachmeskills.entity.News;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsDTO toNewsDTO(News news);
    News toNews(NewsDTO newsDTO);
    List<UserDTO> toNewsDTOList(List<News> news);
}
