package eu.mrndesign.matned.jsonplaceholder.repository;

import eu.mrndesign.matned.jsonplaceholder.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where lower(p.title) like concat('%', lower(:filter), '%')")
    Page<Post> findAllFilteredByTitle(@Param("filter") String filter, Pageable pageable);

    @Query("select p.id from Post p")
    List<Integer> findAllIds();

    @Query("select case when count(p)> 0 then true else false end from Post p where p.id = ?1")
    boolean existById(Integer id);

    @Query("select p.dataBaseId from Post p where p.id = ?1")
    Long getDBIdById(Integer id);

    @Query("select case when p.isEdited = true then true else false end from  Post p where p.dataBaseId = ?1")
    boolean wasEdited(Long id);
}
