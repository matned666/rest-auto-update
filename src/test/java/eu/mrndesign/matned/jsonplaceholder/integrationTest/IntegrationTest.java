package eu.mrndesign.matned.jsonplaceholder.integrationTest;

import eu.mrndesign.matned.jsonplaceholder.dto.IPostDTO;
import eu.mrndesign.matned.jsonplaceholder.dto.PostDTO;
import eu.mrndesign.matned.jsonplaceholder.model.IPost;
import eu.mrndesign.matned.jsonplaceholder.model.Post;
import eu.mrndesign.matned.jsonplaceholder.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.jsonplaceholder.utils.JsonOps.asJsonString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("Test")
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    private List<IPost> posts;
    private List<IPostDTO> postDTOs;

    @BeforeEach
    void setup(){
        posts = new LinkedList<>();
        postDTOs = new LinkedList<>();

        posts.add(new Post(1, 1, "String title", "String body"));
        posts.add(new Post(1, 2, "String title2", "String body2"));
        postDTOs.add(new PostDTO().applyNew((Post) posts.get(0)));
        postDTOs.add(new PostDTO().applyNew((Post) posts.get(1)));
    }

    @Test
    void integration() throws Exception {
        assertEquals(0, postRepository.findAll().size());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/jsonplaceholder/rest"));

//        expect two records, one added automaticly and the seccond manually with /rest
        assertEquals(2, postRepository.findAll().size());


        IPost post = postRepository.findAll().get(0);
        IPostDTO postDTO = new PostDTO(1,1,"test", "test");
        assertNotEquals(postRepository.findAll().get(0).getTitle(), "test");

//        edit records
        mockMvc.perform(
                MockMvcRequestBuilders.post("/jsonplaceholder/"+post.getDataBaseId())
                        .content(asJsonString(postDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        assertEquals(postRepository.findAll().get(0).getTitle(), "test");

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/jsonplaceholder/"+post.getDataBaseId()))
                .andExpect(status().isOk());

        assertEquals(1, postRepository.findAll().size());

    }
}
