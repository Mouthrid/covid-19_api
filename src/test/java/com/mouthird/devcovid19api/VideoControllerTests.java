package com.mouthird.devcovid19api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mouthird.devcovid19api.controller.VideoController;
import com.mouthird.devcovid19api.dao.entity.Video;
import com.mouthird.devcovid19api.dao.entity.Channel;
import com.mouthird.devcovid19api.service.VideoService;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
class VideoControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private VideoController videoController;

    @Value("${APP_KEY}")
    private String APP_KEY;

    @MockBean
    private VideoService videoService;

    private Channel channel;

    private Date date;

    private Timestamp timestamp;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentationContextProvider) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationContextProvider)).build();

        date = new Date();
        timestamp = new Timestamp(date.getTime());
        channel = new Channel("1L", "test channel", "https://channel.jpg");

    }

    @Test
    void testGetSavedVideo() throws Exception {
        List<Video> videoList = new ArrayList<>();
        for(int i=1; i<=2; i++) {
            videoList.add(new Video(String.valueOf(i),"test title", "https://test.com",
                    "https://img.jpg", 10000, "saved", "03:29", channel));
        }

        when(this.videoService.getVideo("saved", 2)).thenReturn(videoList);
        mockMvc.perform(get("/api/v0/video").param("viewState", "saved").param("limit", "2"))
                .andExpect(jsonPath("$[0].title", is("test title")))
                .andExpect(jsonPath("$[0].videoUrl", is("https://test.com")))
                .andExpect(jsonPath("$[0].imgUrl", is("https://img.jpg")))
                .andExpect(jsonPath("$[0].viewCount", is(10000)))
                .andExpect(jsonPath("$[0].viewState", is("saved")))
                .andExpect(jsonPath("[0].duration", is("03:29")))
                .andExpect(status().isOk())
                .andDo(document("video/getSaved",
                        requestParameters(
                                parameterWithName("viewState").description("The state of video (live or saved"),
                                parameterWithName("limit").description("The number of Video for response (if Null shows 100 videos)")
                        ),
                        responseFields(
                                fieldWithPath("[].id").description("The unique Video Id"),
                                fieldWithPath("[].title").description("The title of Video"),
                                fieldWithPath("[].videoTime").description("The time of Video publish"),
                                fieldWithPath("[].videoUrl").description("The URL for the Video website"),
                                fieldWithPath("[].imgUrl").description("The image for the Video"),
                                fieldWithPath("[].viewCount").description("The Video view count"),
                                fieldWithPath("[].viewState").description("The Video view state ('waiting', 'watching', 'saved')"),
                                fieldWithPath("[].duration").description("The Video duration, e.q. '03:22'"),
                                fieldWithPath("[].channel.id").description("The Channel Id"),
                                fieldWithPath("[].channel.name").description("The Channel name"),
                                fieldWithPath("[].channel.imgUrl").description("The Video image")
                        )));
    }

    @Test
    void testGetLiveVideo() throws Exception {
        List<Video> videoList = new ArrayList<>();
        for(int i=1; i<=2; i++) {
            videoList.add(new Video(String.valueOf(i),"test title", "https://test.com",
                    "https://img.jpg", 10000, "watching", "03:29", channel));
        }

        when(this.videoService.getVideo("live", 2)).thenReturn(videoList);
        mockMvc.perform(get("/api/v0/video").param("viewState", "live").param("limit", "2"))
                .andExpect(jsonPath("$[0].title", is("test title")))
                .andExpect(jsonPath("$[0].videoUrl", is("https://test.com")))
                .andExpect(jsonPath("$[0].imgUrl", is("https://img.jpg")))
                .andExpect(jsonPath("$[0].viewCount", is(10000)))
                .andExpect(jsonPath("$[0].viewState", is("watching")))
                .andExpect(jsonPath("[0].duration", is("03:29")))
                .andExpect(status().isOk())
                .andDo(document("video/getLive",
                        requestParameters(
                                parameterWithName("viewState").description("The state of video (live or saved"),
                                parameterWithName("limit").description("The number of Video for response (if Null shows 100 videos)")
                        ),
                        responseFields(
                                fieldWithPath("[].id").description("The unique Video Id"),
                                fieldWithPath("[].title").description("The title of Video"),
                                fieldWithPath("[].videoTime").description("The time of Video publish"),
                                fieldWithPath("[].videoUrl").description("The URL for the Video website"),
                                fieldWithPath("[].imgUrl").description("The image for the Video"),
                                fieldWithPath("[].viewCount").description("The Video view count"),
                                fieldWithPath("[].viewState").description("The Video view state ('waiting', 'watching', 'saved')"),
                                fieldWithPath("[].duration").description("The Video duration, e.q. '03:22'"),
                                fieldWithPath("[].channel.id").description("The Channel Id"),
                                fieldWithPath("[].channel.name").description("The Channel name"),
                                fieldWithPath("[].channel.imgUrl").description("The Video image")
                        )));
    }

    @Test
    void testPostVideo() throws Exception {
        Video video = new Video("1L", "test title", null, "https://test.com",
                "https://img.jpg", 10000, "watching", "03:29", channel);
        mockMvc.perform(post("/api/v0/video").header("appKey", APP_KEY).contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(video))).andExpect(status().isOk())
        .andDo(document("video/post",
                requestFields(
                        fieldWithPath("id").description("The unique Video Id"),
                        fieldWithPath("title").description("The title of Video"),
                        fieldWithPath("videoTime").description("Live video is null, saved video is 2021-07-25 10:14"),
                        fieldWithPath("videoUrl").description("The URL for the Video website"),
                        fieldWithPath("imgUrl").description("The image for the Video"),
                        fieldWithPath("viewCount").description("The Video view count"),
                        fieldWithPath("viewState").description("The Video view state ('waiting', 'watching', 'saved')"),
                        fieldWithPath("duration").description("The Video duration, e.q. '03:22'"),
                        fieldWithPath("channel.id").description("The Channel Id"),
                        fieldWithPath("channel.name").description("The Channel name"),
                        fieldWithPath("channel.imgUrl").description("The Video image")
                )));
    }

    @Test
    void testPutVideo() throws Exception {
        Video video = new Video("1L", "test title", null, "https://test.com",
                "https://img.jpg", 10000, "watching", "03:29", channel);
        mockMvc.perform(put("/api/v0/video").header("appKey", APP_KEY).contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(video))).andExpect(status().isOk())
                .andDo(document("video/put",
                        requestFields(
                                fieldWithPath("id").description("The unique Video Id"),
                                fieldWithPath("title").description("The title of Video"),
                                fieldWithPath("videoTime").description("Live video is null, saved video is 2021-07-25 10:14"),
                                fieldWithPath("videoUrl").description("The URL for the Video website"),
                                fieldWithPath("imgUrl").description("The image for the Video"),
                                fieldWithPath("viewCount").description("The Video view count"),
                                fieldWithPath("viewState").description("The Video view state ('waiting', 'watching', 'saved')"),
                                fieldWithPath("duration").description("The Video duration, e.q. '03:22'"),
                                fieldWithPath("channel.id").description("The Channel Id"),
                                fieldWithPath("channel.name").description("The Channel name"),
                                fieldWithPath("channel.imgUrl").description("The Video image")
                        )));
    }

    @Test
    void testDeleteVideo() throws Exception {
        mockMvc.perform(delete("/api/v0/video").header("appKey", APP_KEY).param("id", "1"))
                .andExpect(status().isOk())
                .andDo(document("video/delete",
                        requestParameters(parameterWithName("id").description("The Video id"))
                ));
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