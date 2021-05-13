package eu.mrndesign.matned.jsonplaceholder.controller;

import eu.mrndesign.matned.jsonplaceholder.dto.IDTO;
import eu.mrndesign.matned.jsonplaceholder.dto.IPostDTO;
import eu.mrndesign.matned.jsonplaceholder.dto.IPostDTOBase;
import eu.mrndesign.matned.jsonplaceholder.dto.PostDTO;
import eu.mrndesign.matned.jsonplaceholder.service.IRestService;
import eu.mrndesign.matned.jsonplaceholder.service.IService;
import eu.mrndesign.matned.jsonplaceholder.service.PostService;
import eu.mrndesign.matned.jsonplaceholder.service.RestConnectionService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/jsonplaceholder")
public class PostController {

    private final IService postService;
    private final IRestService restService;

    public PostController(PostService postService, RestConnectionService restService) {
        this.postService = postService;
        this.restService = restService;
    }

    @GetMapping
    public void getAllSavedPosts(HttpServletResponse response,
                                 @RequestParam(defaultValue = "${default.page.start}", name = "page") Integer page,
                                 @RequestParam(defaultValue = "${default.page.size}", name = "amount") Integer amount,
                                 @RequestParam(defaultValue = "false", name = "simple") boolean isSimple,
                                 @RequestParam(defaultValue = "", name = "filter") String filter) throws IOException {
        StringBuilder pageBuilder = new StringBuilder();
        if (!isSimple) {
            pageBuilder.append("/jsonplaceholder/show-all?page=").append(page).append("&amount=").append(amount).append("&filter=").append(filter);
        } else {
            pageBuilder.append("/jsonplaceholder/show-all-simple?page=").append(page).append("&amount=").append(amount).append("&filter=").append(filter);
        }
       response.sendRedirect(pageBuilder.toString());

    }

    @GetMapping("/rest")
    public void downloadNewRandomPost(HttpServletResponse response,
                                      @RequestParam(defaultValue = "${default.page.start}", name = "page") Integer page,
                                      @RequestParam(defaultValue = "${default.page.size}", name = "amount") Integer amount,
                                      @RequestParam(defaultValue = "false", name = "simple") boolean isSimple,
                                      @RequestParam(defaultValue = "", name = "filter") String filter) throws IOException {
        restService.getRest();
        getAllSavedPosts(response, page, amount, isSimple, filter);
    }

    @GetMapping("/show-all")
    public List<IPostDTO> showAll(@RequestParam(defaultValue = "${default.page.start}", name = "page") Integer page,
                                  @RequestParam(defaultValue = "${default.page.size}", name = "amount") Integer amount,
                                  @RequestParam(defaultValue = "", name = "filter") String filter) {
        return postService.showAllSavedPostsPageWithTitleFilter(page, amount, filter);
    }

    @GetMapping("/show-all-simple")
    public List<IPostDTOBase> showAllSimple(@RequestParam(defaultValue = "${default.page.start}", name = "page") Integer page,
                                            @RequestParam(defaultValue = "${default.page.size}", name = "amount") Integer amount,
                                            @RequestParam(defaultValue = "", name = "filter") String filter) {
        return postService.showAllSavedPostsPageWithTitleFilterSimple(page, amount, filter);
    }


    @GetMapping("/single/{id}")
    public IDTO getSingleRecordById(@PathVariable Long id) {
        return postService.showPostWithId(id);
    }

    @PostMapping("/{id}")
    public IDTO editPost(@PathVariable Long id,
                         @RequestBody PostDTO dto) {
        return postService.editPostInDB(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.removePost(id);
    }


}
