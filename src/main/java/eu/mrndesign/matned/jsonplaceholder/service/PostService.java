package eu.mrndesign.matned.jsonplaceholder.service;

import eu.mrndesign.matned.jsonplaceholder.dto.IPostDTO;
import eu.mrndesign.matned.jsonplaceholder.dto.IPostDTOBase;
import eu.mrndesign.matned.jsonplaceholder.dto.PostDTO;
import eu.mrndesign.matned.jsonplaceholder.dto.PostDTOSimple;
import eu.mrndesign.matned.jsonplaceholder.exception.PostNotFoundException;
import eu.mrndesign.matned.jsonplaceholder.model.Deleted;
import eu.mrndesign.matned.jsonplaceholder.model.Post;
import eu.mrndesign.matned.jsonplaceholder.repository.DeletedRepository;
import eu.mrndesign.matned.jsonplaceholder.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService extends BaseService implements IService {

    private final PostRepository postRepository;
    private final DeletedRepository deletedRepository;

    public PostService(PostRepository postRepository,
                       DeletedRepository deletedRepository) {
        this.postRepository = postRepository;
        this.deletedRepository = deletedRepository;
    }

    @Override
    public IPostDTO savePostToDB(IPostDTO data) {
        Post postToSaveLocally = (Post) new Post().applyNew(data);
        return new PostDTO().applyNew(postRepository.save(postToSaveLocally));
    }

    @Override
    public IPostDTO editPostInDB(Long id, IPostDTO data) {
        Post postToEditLocally = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        postToEditLocally.edit(data);
        return new PostDTO().applyNew(postRepository.save(postToEditLocally));
    }

    @Override
    public IPostDTO showPostWithId(Long id) {
        return getiPostDTO(id);
    }

    @Override
    public List<IPostDTO> showAllSavedPostsPageWithTitleFilter(Integer startPage, Integer itemsPerPage, String titleFilter) {
        return covertToDTOPostList(postRepository.findAllFilteredByTitle(titleFilter, getPageable(startPage, itemsPerPage)).getContent());
    }

    @Override
    public List<IPostDTO> showAll() {
        return covertToDTOPostList(postRepository.findAll());
    }

    @Override
    public List<Integer> showAllIds() {
        return postRepository.findAllIds();
    }

    @Override
    public boolean existsById(Integer randomInt) {
        return postRepository.existById(randomInt);
    }

    @Override
    public boolean wasEdited(Long id) {
        return postRepository.wasEdited(id);
    }

    @Override
    public Long getIdByIdm(Integer x) {
        return postRepository.getDBIdById(x);
    }

    @Override
    public boolean wasDeleted(Integer x) {
        return deletedRepository.existsByRestId(x);
    }

    @Override
    public List<IPostDTOBase> showAllSavedPostsPageWithTitleFilterSimple(Integer page, Integer amount, String filter) {
        return postRepository.findAllFilteredByTitle(filter, getPageable(page, amount)).getContent().stream()
                .map(x-> new PostDTOSimple().applyNewSimple(x))
                .collect(Collectors.toList());
    }

    @Override
    public void removePost(Long id) {
        Post p = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        deletedRepository.save(new Deleted(p.getId()));
        postRepository.deleteById(id);
    }


    private IPostDTO getiPostDTO(Long id) {
        return new PostDTO().applyNew(postRepository.findById(id).orElseThrow(PostNotFoundException::new));
    }


}
