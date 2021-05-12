package eu.mrndesign.matned.jsonplaceholder.service;

import eu.mrndesign.matned.jsonplaceholder.dto.IPostDTO;
import eu.mrndesign.matned.jsonplaceholder.dto.PostDTO;
import eu.mrndesign.matned.jsonplaceholder.model.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseService {

    @Value("${default.page.start}")
    protected Integer defaultStartPage;
    @Value("${default.page.size}")
    protected Integer defaultPageSize;

    protected Pageable getPageable(Integer startPage, Integer itemsPerPage) {
        if (itemsPerPage == null)
            itemsPerPage = defaultPageSize;
        if (startPage == null)
            startPage = defaultStartPage;
        if (itemsPerPage < 1)
            itemsPerPage = 1;
        return getPageableInner(startPage, itemsPerPage);
    }

    protected List<IPostDTO> covertToDTOPostList(List<Post> content) {
        return content.stream()
                .map(x->new PostDTO().applyNew(x))
                .collect(Collectors.toList());
    }

    private Pageable getPageableInner(Integer startPage, Integer itemsPerPage) {
        Pageable pageable;
        try {
            pageable = PageRequest.of(startPage, itemsPerPage);
        } catch (Exception e) {
            e.printStackTrace();
            pageable = PageRequest.of(startPage, itemsPerPage);
        }
        return pageable;
    }


}
