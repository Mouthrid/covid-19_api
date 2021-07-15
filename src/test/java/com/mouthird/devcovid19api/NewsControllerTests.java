package com.mouthird.devcovid19api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

import com.mouthird.devcovid19api.controller.NewsController;
import com.mouthird.devcovid19api.dao.entity.News;
import com.mouthird.devcovid19api.service.NewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    private List<News> newsList = new ArrayList<>();

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentationContextProvider) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationContextProvider)).build();

        for(int i=1; i<=2; i++) {
            newsList.add(new News((long)i,"test title", LocalDate.parse("2021-07-12"), "https://test.com",
                    "https://img.jpg", "This is test object."));
        }
    }

    @Test
    void testGetNews() throws Exception {
        when(this.newsService.getNews(2)).thenReturn(newsList);
        mockMvc.perform(get("/api/v0/news").param("limit", "2"))
                .andExpect(jsonPath("$[0].title", is("test title")))
                .andExpect(jsonPath("$[0].newsTime", is("2021-07-12")))
                .andExpect(jsonPath("$[0].newsUrl", is("https://test.com")))
                .andExpect(jsonPath("$[0].imgUrl", is("https://img.jpg")))
                .andExpect(jsonPath("$[0].description", is("This is test object.")))
                .andExpect(status().isOk())
                .andDo(document("news",
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

}