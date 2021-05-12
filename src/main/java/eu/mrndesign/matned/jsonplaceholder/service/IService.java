package eu.mrndesign.matned.jsonplaceholder.service;

import eu.mrndesign.matned.jsonplaceholder.dto.IPostDTO;
import eu.mrndesign.matned.jsonplaceholder.dto.IPostDTOBase;

import java.util.List;

public interface IService {

    IPostDTO savePostToDB(IPostDTO data);
    IPostDTO editPostInDB(Long id, IPostDTO data);
    IPostDTO showPostWithId(Long id);
    List<IPostDTO> showAllSavedPostsPageWithTitleFilter(Integer startPage, Integer itemsPerPage, String titleFilter);
    List<IPostDTOBase> showAllSavedPostsPageWithTitleFilterSimple(Integer page, Integer amount, String filter);
    void removePost(Long id);
    List<IPostDTO> showAll();
    List<Integer> showAllIds();

    boolean existsById(Integer randomInt);
    boolean wasEdited(Long id);
    Long getIdByIdm(Integer x);

    boolean wasDeleted(Integer x);
}
