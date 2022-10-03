package com.example.projectteachmeskills.job;

import com.example.projectteachmeskills.config.ParseUrl;
import com.example.projectteachmeskills.entity.News;
import com.example.projectteachmeskills.service.NewsServise;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@ConfigurationProperties(prefix = "parse.news")
public class NewsParser {

    private NewsServise newsServise;

    private ParseUrl url;

    @Scheduled(fixedDelay = 3600000)
    public void parseNews() {

        try {
            LocalDateTime lastParseTime = LocalDateTime.now().minusHours(1);

            Elements elNews = parsePage(url.getNews()).getElementsByClass("news-item");

            for (Element el : elNews) {

                LocalDateTime newsTime = convertTime(el.select("div[class=news-item__time]").text());

                if (newsTime.isAfter(lastParseTime)&& newsTime.isBefore(LocalDateTime.now())) {

                    News news = new News();

                    news.setContentUrl("https://www.championat.com/" + el.getElementsByTag("a")
                            .attr("href"));
                    news.setTitle(el.getElementsByTag("a").first().text());
                    news.setDateTime(newsTime);
                    news.setCategory(el.getElementsByClass("news-item__tag").text());

                    if(newsServise.findByTitle(news.getTitle()).isEmpty()){
                       newsServise.save(news);
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
                .timeout(6000)
                .referrer("https://google.com")
                .get();
    }

    private LocalDateTime convertTime(String time) {
        List<String> list = List.of(time.split(":"));
        Integer hour = Integer.parseInt(list.get(0));
        Integer minute = Integer.parseInt(list.get(1));
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(hour, minute, 1));
    }
}
