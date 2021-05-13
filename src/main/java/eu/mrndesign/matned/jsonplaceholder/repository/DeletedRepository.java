package eu.mrndesign.matned.jsonplaceholder.repository;

import eu.mrndesign.matned.jsonplaceholder.model.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DeletedRepository extends JpaRepository<Deleted, Long> {

    @Query("select case when count(d)> 0 then true else false end from Deleted d where d.id = ?1")
    boolean existsByRestId(Integer x);

    @Query("select d from Deleted  d where d.id = ?1")
    Optional<Deleted> findByRestId(Integer id);
}
