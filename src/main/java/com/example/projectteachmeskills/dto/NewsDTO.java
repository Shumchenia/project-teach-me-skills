package com.example.projectteachmeskills.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO {
    String title;
    String contentUrl;
    LocalDateTime dateTime;
    String category;
}
