package com.SpaceNews.articlesfeed;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SpaceflightNewsController.class)
public class SpaceflightNewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpaceflightNewsService spaceflightNewsService;

    @InjectMocks
    private SpaceflightNewsController spaceflightNewsController;

    @Test
    void testHome() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    void testGetArticles() throws Exception {
        // Creating dummy data
        article article1 = new article("Title 1", "https://example.com/image1.jpg");
        article article2 = new article("Title 2", "https://example.com/image2.jpg");
        List<article> articles = Arrays.asList(article1, article2);

        // Mocking the service to return dummy data
        when(spaceflightNewsService.getArticles()).thenReturn(articles);

        mockMvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(view().name("articles"))
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attribute("articles", articles));
    }
}
