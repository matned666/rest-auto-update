package eu.mrndesign.matned.jsonplaceholder.service;

import eu.mrndesign.matned.jsonplaceholder.dto.IPostDTO;
import eu.mrndesign.matned.jsonplaceholder.dto.PostDTO;
import eu.mrndesign.matned.jsonplaceholder.exception.NullDataProvidedException;
import eu.mrndesign.matned.jsonplaceholder.exception.PostNotFoundException;
import eu.mrndesign.matned.jsonplaceholder.model.IPost;
import eu.mrndesign.matned.jsonplaceholder.model.Post;
import eu.mrndesign.matned.jsonplaceholder.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith({SpringExtension.class})
@SpringBootTest
@ActiveProfiles("Test")
class PostServiceTest {

    @Autowired
    private PostService postService;

    @MockBean
    private PostRepository postRepository;

    private List<IPost> posts;
    private List<IPostDTO> postDTOs;
    private Pageable pageable;

    @BeforeEach
    void setup(){
        posts = new LinkedList<>();
        postDTOs = new LinkedList<>();
        pageable =  PageRequest.of(0, 5);

        posts.add(new Post(1, 1, "String title", "String body"));
        posts.add(new Post(1, 2, "String title2", "String body2"));
        postDTOs.add(new PostDTO().applyNew((Post) posts.get(0)));
        postDTOs.add(new PostDTO().applyNew((Post) posts.get(1)));
    }

    @Test
    void savePostToDB() {
        doReturn(posts.get(0)).when(postRepository).save(any());

        assertEquals(postDTOs.get(0), new PostDTO().applyNew((Post) posts.get(0)));
    }

    @Test
    void editPostInDB() {


        doReturn(Optional.of(posts.get(0))).when(postRepository).findById(anyLong());
        doReturn(posts.get(1)).when(postRepository).save(any());

        assertEquals(postDTOs.get(1), postService.editPostInDB(0L, postDTOs.get(1)));
        assertEquals(posts.get(1).getTitle(), posts.get(0).getTitle());
    }

    @Test
    void editPostInDBThrowsExceptionPostNotFound() {
        doReturn(Optional.empty()).when(postRepository).findById(anyLong());
        assertThrows(PostNotFoundException.class, ()->postService.editPostInDB(1L, postDTOs.get(1)));
    }

    @Test
    void editPostInDBThrowsException() {
        doReturn(Optional.of(posts.get(0))).when(postRepository).findById(anyLong());
        assertThrows(NullDataProvidedException.class, ()->postService.editPostInDB(1L, null));
    }

    @Test
    void showPostWithId() {
        doReturn(Optional.of(posts.get(0))).when(postRepository).findById(anyLong());
        assertEquals(postDTOs.get(0), postService.showPostWithId(0L));
    }

    @Test
    void showPostWithIdThrowsExceptionWhenNothingFound() {
        doReturn(Optional.empty()).when(postRepository).findById(anyLong());
        assertThrows(PostNotFoundException.class, ()->postService.showPostWithId(1L));
    }

    @Test
    void showAllSavedPostsPageWithTitleFilter() {
        doReturn(new PageImpl<>(posts.subList(0, 2), pageable, 3)).when(postRepository).findAllFilteredByTitle(any(), any(Pageable.class));
        assertEquals(postDTOs, postService.showAllSavedPostsPageWithTitleFilter(0, 5, "aaa"));
    }

}