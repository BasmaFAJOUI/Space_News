package com.SpaceNews.blogsfeed;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class BlogsServiceTest {

    @Mock
    private RestTemplate restTemplateBlogs;

    @InjectMocks
    private BlogsService blogsService;

    @Test
    public void testGetBlogs() {
        // Mocking the response from the API
        blog mockBlog1 = new blog("Title1", "Content1");
        blog mockBlog2 = new blog("Title2", "Content2");
        List<blog> mockBlogList = Arrays.asList(mockBlog1, mockBlog2);

        ApiResponseBlogs<blog> mockApiResponse = new ApiResponseBlogs<>();
        mockApiResponse.setResults(mockBlogList);

        ResponseEntity<ApiResponseBlogs<blog>> mockResponseEntity = ResponseEntity.ok(mockApiResponse);

        when(restTemplateBlogs.exchange(
                eq(BlogsService.Blogs_API_URL),
                eq(HttpMethod.GET),
                isNull(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(mockResponseEntity);

        // Calling the method under test
        List<blog> blogs = blogsService.getBlogs();

        // Asserting the results
        assertNotNull(blogs);
        assertEquals(2, blogs.size());
        assertEquals("Title1", blogs.get(0).getTitle());
        assertEquals("Title2", blogs.get(1).getTitle());
    }
}
