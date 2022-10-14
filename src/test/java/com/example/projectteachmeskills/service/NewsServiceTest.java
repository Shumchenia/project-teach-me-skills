package com.example.projectteachmeskills.service;

import com.example.projectteachmeskills.entity.News;
import com.example.projectteachmeskills.repositrory.NewsRepository;
import com.example.projectteachmeskills.service.Impl.NewsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class NewsServiceTest {

    private static final long NEWS_ID = 1L;
    private static final LocalDateTime NEWS_DATE_TIME = LocalDateTime.now();
    private static final String NEWS_TITLE = "Title";
    private static final String NEWS_CONTENT_URL = "https://www.championat.com//hockey/news-4851339-vasilevskij-otmetilsya-yarkim-spaseniem-schitkom-posle-broska-v-upor-v-matche-s-rejndzhers.html";
    private static final String NEWS_CATEGORY = "Хоккей";
    private NewsServiceImpl newsService;

    private NewsRepository newsRepository;

    @BeforeEach
    void init() {
        newsRepository = mock(NewsRepository.class);
        newsService = spy(new NewsServiceImpl(newsRepository));
    }

    @Test
    @DisplayName("check findAll method")
    void findAll() {
        News news =getMockedNews();
        doReturn(List.of(news)).when(newsRepository).findAll();
        List<News> newsList = newsService.findAll();
        assertEquals(1, newsList.size());
        assertEquals(news, newsList.get(0));
    }

    @Test
    @DisplayName("check if error throws when News is not found")
    void findById_throws_exception() {
        doThrow(new RuntimeException("News hasn't found"))
                .when(newsRepository).findById(NEWS_ID);

        Exception exception =
                assertThrows(RuntimeException.class, () -> newsService.findById(NEWS_ID));

        String expectedMessage = "News hasn't found";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("check if News returns when it is found")
    void findById_returns_news() {
        News mockedNews = getMockedNews();
        doReturn(Optional.of(mockedNews))
                .when(newsRepository).findById(NEWS_ID);

        News news = newsService.findById(NEWS_ID);

        assertEquals(mockedNews, news);
    }

    @Test
    void deleteById() {
        doNothing().when(newsRepository).deleteById(NEWS_ID);
        newsService.deleteById(NEWS_ID);

        verify(newsRepository, times(1)).deleteById(NEWS_ID);
        verify(newsRepository, times(1)).deleteById(anyLong());
        verify(newsRepository, never()).findById(anyLong());
    }

    @Test
    void save_with_same_id() {
        News mockedNews = getMockedNews();
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0))
                .when(newsRepository)
                .save(any(News.class));

        News result = newsService.save(NEWS_ID, mockedNews);
        assertEquals(mockedNews, result);
        assertEquals(NEWS_ID, result.getId());
    }

    @Test
    void save_with_different_id() {
        News mockedNews = getMockedNews();
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0))
                .when(newsRepository)
                .save(any(News.class));

        News result = newsService.save(NEWS_ID + 1, mockedNews);
        assertEquals(mockedNews, result);
        assertEquals(NEWS_ID + 1, result.getId());
    }

    @Test
    void findByTitleContaining_throws_exception(){
        doThrow(new RuntimeException("News hasn't found"))
                .when(newsRepository).findByTitleContaining("tit");

        Exception exception =
                assertThrows(RuntimeException.class, () -> newsService.findByTitleContaining("tit"));

        String expectedMessage = "News hasn't found";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void findByTitleContaining_returns_news(){
        News mockedNews=getMockedNews();
        doReturn(List.of(mockedNews))
                .when(newsRepository).findByTitleContaining("tit");
        List<News> news= newsService.findByTitleContaining("tit");

        assertEquals(1,news.size());
        assertEquals(mockedNews, news.get(0));
    }

    @Test
    void findByCategory_throws_exception() {
        doThrow(new RuntimeException("News hasn't found"))
                .when(newsRepository).findByCategory("хоккей");

        Exception exception =
                assertThrows(RuntimeException.class, () -> newsService.findByCategory("хоккей"));

        String expectedMessage = "News hasn't found";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void findByCategory_returns_news() {
        News mockedNews = getMockedNews();
        doReturn(List.of(mockedNews))
                .when(newsRepository).findByCategory("хоккей");

        List<News> news = newsService.findByCategory("хоккей");

        assertEquals(1,news.size());
        assertEquals(mockedNews, news.get(0));
    }

    @Test
    void findByTitle() {
        News mockedNews = getMockedNews();
        doReturn(Optional.of(mockedNews))
                .when(newsRepository).findByTitle("le");

        News news = newsService.findByTitle("le").get();

        assertEquals(mockedNews, news);
    }


    private News getMockedNews() {
        News news = new News();
        news.setId(NEWS_ID);
        news.setDateTime(NEWS_DATE_TIME);
        news.setTitle(NEWS_TITLE);
        news.setCategory(NEWS_CATEGORY);
        news.setContentUrl(NEWS_CONTENT_URL);
        return news;
    }
}
