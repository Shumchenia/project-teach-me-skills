package com.example.projectteachmeskills.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewsErrorResponce {
    private String message;
    private Long timestamp;
}
