package com.example.projectteachmeskills.job;

import com.example.projectteachmeskills.config.ParseConfigs;
import com.example.projectteachmeskills.dto.NewsDTO;
import com.example.projectteachmeskills.entity.News;
import com.example.projectteachmeskills.mapper.NewsMapper;
import com.example.projectteachmeskills.service.NewsService;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.expression.Lists;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class NewsParser {

    private NewsService newsService;

    private ParseConfigs url;

    @Scheduled(fixedDelay = 3600000)
    public void parseNews() {

        try {
            LocalDateTime lastParseTime = LocalDateTime.now().minusHours(1);

            Elements elNews = parsePage(url.getNewsUrl()).getElementsByClass("news-item");

            for (Element el : elNews) {

                LocalDateTime newsTime = convertTime(el.select("div[class=news-item__time]").text());

                if (newsTime.isAfter(lastParseTime) && newsTime.isBefore(LocalDateTime.now())) {

                    NewsDTO news = new NewsDTO();
                    String category = el.getElementsByClass("news-item__tag").text();
                    news.setContentUrl("https://www.championat.com/" + el.getElementsByTag("a")
                            .attr("href"));
                    news.setTitle(Objects.requireNonNull(el.getElementsByTag("a").first()).text());
                    news.setDateTime(newsTime);
                    news.setCategory(el.getElementsByClass("news-item__tag").text());

                    if (newsService.findByTitle(news.getTitle()).isEmpty() && !category.isEmpty()) {
                        newsService.save(news);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Document parsePage(String url) throws IOException {
        return Jsoup.connect(url)
                .userAgent("Chrome")
                .timeout(60000)
                .referrer("https://google.com")
                .get();
    }

    private LocalDateTime convertTime(String time) {
        List<String> list = List.of(time.split(":"));
        int hour = Integer.parseInt(list.get(0));
        int minute = Integer.parseInt(list.get(1));
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(hour, minute, 1));
    }
}
