package eu.mrndesign.matned.jsonplaceholder.controller;

import eu.mrndesign.matned.jsonplaceholder.dto.IPostDTO;
import eu.mrndesign.matned.jsonplaceholder.dto.PostDTO;
import eu.mrndesign.matned.jsonplaceholder.service.PostService;
import eu.mrndesign.matned.jsonplaceholder.service.RestConnectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Collections;

import static eu.mrndesign.matned.jsonplaceholder.utils.JsonOps.asJsonString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith({SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("Test")
class PostControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private RestConnectionService restService;




    @Test
    void getAllSavedPostsFullInfoRedirect() throws Exception {
        String expectedUrl = "/jsonplaceholder/show-all?page=0&amount=20&filter=";

        mockMvc.perform(
                MockMvcRequestBuilders.get("/jsonplaceholder?simple=false"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(expectedUrl))
                .andReturn();

//        default scenario makes the same
        mockMvc.perform(
                MockMvcRequestBuilders.get("/jsonplaceholder"))
                .andExpect(redirectedUrl(expectedUrl));


    }

   @Test
    void getAllSavedPostsSimpleInfoRedirect() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/jsonplaceholder?simple=true"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/jsonplaceholder/show-all-simple?page=0&amount=20&filter="))
                .andReturn();
    }

    @Test
    void getAllSavedPostsErrorWrongData() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/jsonplaceholder?simple=true"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/jsonplaceholder/show-all-simple?page=0&amount=20&filter="))
                .andReturn();
    }

    @Test
    void downloadNewRandomPost() throws Exception {
        doNothing().when(restService).getRest();

        String expectedUrl = "/jsonplaceholder/show-all?page=0&amount=20&filter=";

        mockMvc.perform(
                MockMvcRequestBuilders.get("/jsonplaceholder/rest"))
                .andExpect(redirectedUrl(expectedUrl));

    }

    @Test
    void showAll() throws Exception {
        IPostDTO post = new PostDTO();

        Mockito.doReturn(Collections.singletonList(post)).when(postService).showAllSavedPostsPageWithTitleFilter(any(), any(), any());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/jsonplaceholder/show-all"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(Collections.singletonList(post))))
                .andReturn();
    }

    @Test
    void showAllSimple() throws Exception {
        IPostDTO post = new PostDTO();

        Mockito.doReturn(Collections.singletonList(post)).when(postService).showAllSavedPostsPageWithTitleFilterSimple(any(), any(), any());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/jsonplaceholder/show-all-simple"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(Collections.singletonList(post))))
                .andReturn();
    }

    @Test
    void getSingleRecordById() throws Exception {
        IPostDTO post = new PostDTO();

        Mockito.doReturn(post).when(postService).showPostWithId(any());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/jsonplaceholder/single/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(post)))
                .andReturn();
    }

    @Test
    void editPost() throws Exception {
        IPostDTO post = new PostDTO();

        Mockito.doReturn(post).when(postService).editPostInDB(anyLong(),any());
        mockMvc.perform(
                MockMvcRequestBuilders.post("/jsonplaceholder/1")
                        .content(asJsonString(post))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(post)))
                .andReturn();
    }

    @Test
    void deletePost() throws Exception {
        doNothing().when(postService).removePost(any());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/jsonplaceholder/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }
}