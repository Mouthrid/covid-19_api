package com.mouthird.devcovid19api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mouthird.devcovid19api.controller.NewsController;
import com.mouthird.devcovid19api.dao.entity.News;
import com.mouthird.devcovid19api.service.NewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
class NewsControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private NewsController newsController;

    @MockBean
    private NewsService newsService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentationContextProvider) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationContextProvider)).build();


    }

    @Test
    void testGetNews() throws Exception {
        List<News> newsList = new ArrayList<>();
        for(int i=1; i<=2; i++) {
            newsList.add(new News((long)i,"test title", LocalDate.parse("2021-07-12"), "https://test.com",
                    "https://img.jpg", "This is test object."));
        }

        when(this.newsService.getNews(2)).thenReturn(newsList);
        mockMvc.perform(get("/api/v0/news").param("limit", "2"))
                .andExpect(jsonPath("$[0].title", is("test title")))
                .andExpect(jsonPath("$[0].newsTime", is("2021-07-12")))
                .andExpect(jsonPath("$[0].newsUrl", is("https://test.com")))
                .andExpect(jsonPath("$[0].imgUrl", is("https://img.jpg")))
                .andExpect(jsonPath("$[0].description", is("This is test object.")))
                .andExpect(status().isOk())
                .andDo(document("news/get",
                        requestParameters(parameterWithName("limit").description("The number of News for response")),
                        responseFields(
                                fieldWithPath("[].id").description("The unique News Id"),
                                fieldWithPath("[].title").description("The title of News"),
                                fieldWithPath("[].newsTime").description("The time of News publish"),
                                fieldWithPath("[].crawlTime").description("The time of web crawler get News"),
                                fieldWithPath("[].newsUrl").description("The URL for the News website"),
                                fieldWithPath("[].imgUrl").description("The image for the News"),
                                fieldWithPath("[].description").description("The News description")
                        )));
    }

    @Test
    void testAddNews() throws Exception {
        List<News> newsList = new ArrayList<>();
        for(int i=1; i<=2; i++) {
            newsList.add(new NewsIgnoreProperties("test title", LocalDate.parse("2021-07-12"), "https://test.com",
                    "https://img.jpg", "This is test object."));
        }

        mockMvc.perform(post("/api/v0/news").contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(newsList))).andExpect(status().isOk())
        .andDo(document("news/post",
                requestFields(
                        fieldWithPath("[].title").description("The title of News"),
                        fieldWithPath("[].newsTime").description("The time of News publish (format: 2021-01-01)"),
                        fieldWithPath("[].newsUrl").description("The URL for the News website"),
                        fieldWithPath("[].imgUrl").description("The image for the News"),
                        fieldWithPath("[].description").description("The News description")
                )));
    }

    @JsonIgnoreProperties({"id","crawlTime"})
    public class NewsIgnoreProperties extends News {
        public NewsIgnoreProperties(String title,
                    LocalDate newsTime,
                    String newsUrl,
                    String imgUrl,
                    String description) {
            super(title, newsTime, newsUrl, imgUrl, description);
        }
    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}