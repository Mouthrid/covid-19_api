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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

    private Date date;

    private Timestamp timestamp;

    @Value("${APP_KEY}")
    private String APP_KEY;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentationContextProvider) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationContextProvider)).build();

        date = new Date();
        timestamp = new Timestamp(date.getTime());

    }

    @Test
    void testGetNews() throws Exception {
        List<News> newsList = new ArrayList<>();
        for(int i=1; i<=2; i++) {
            newsList.add(new News(String.valueOf(i),"test title", timestamp, "https://test.com",
                    "https://img.jpg", "This is test object."));
        }

        when(this.newsService.getNews(2)).thenReturn(newsList);
        mockMvc.perform(get("/api/v0/news").param("limit", "2"))
                .andExpect(jsonPath("$[0].title", is("test title")))
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
    void testPostNews() throws Exception {
        News news = new NewsIgnoreProperties("1L", "test title", timestamp, "https://test.com",
                    "https://img.jpg", "This is test object.");
        mockMvc.perform(post("/api/v0/news").header("appKey", APP_KEY).contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(news))).andExpect(status().isOk())
        .andDo(document("news/post",
                requestFields(
                        fieldWithPath("id").description("The unique News Id"),
                        fieldWithPath("title").description("The title of News"),
                        fieldWithPath("newsTime").description("The times of News publish (format: 2021-07-18T13:11:26Z)"),
                        fieldWithPath("newsUrl").description("The URL for the News website"),
                        fieldWithPath("imgUrl").description("The image for the News"),
                        fieldWithPath("description").description("The News description")
                )));
    }

    @Test
    void testPutNews() throws Exception {
        News news = new News("1L","test title", timestamp, "https://test.com",
                "https://img.jpg", "This is test object.");
        mockMvc.perform(put("/api/v0/news").header("appKey", APP_KEY).contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(news))).andExpect(status().isOk())
                .andDo(document("news/put",
                        requestFields(
                                fieldWithPath("id").description("The unique News Id"),
                                fieldWithPath("title").description("The title of News"),
                                fieldWithPath("newsTime").description("The time of News publish"),
                                fieldWithPath("crawlTime").description("The time of web crawler get News"),
                                fieldWithPath("newsUrl").description("The URL for the News website"),
                                fieldWithPath("imgUrl").description("The image for the News"),
                                fieldWithPath("description").description("The News description")
                        )));
    }

    @Test
    void testDeleteNews() throws Exception {
        mockMvc.perform(delete("/api/v0/news").header("appKey", APP_KEY).param("del_id", "1"))
                .andExpect(status().isOk())
                .andDo(document("news/delete",
                        requestParameters(parameterWithName("del_id").description("The News id"))
                ));
    }

    @Test
    void testExistsNews() throws Exception {
        mockMvc.perform(get("/api/v0/news").param("id", "1L"))
                .andExpect(status().isOk())
                .andDo(document("news/getExists",
                        requestParameters(parameterWithName("id").description("The News id")),
                        responseFields(fieldWithPath("state").description("exists or not"))));
    }

    @JsonIgnoreProperties({"crawlTime"})
    public class NewsIgnoreProperties extends News {
        public NewsIgnoreProperties(String id,
                                    String title,
                                    Timestamp newsTime,
                                    String newsUrl,
                                    String imgUrl,
                                    String description) {
            super(id, title, newsTime, newsUrl, imgUrl, description);
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