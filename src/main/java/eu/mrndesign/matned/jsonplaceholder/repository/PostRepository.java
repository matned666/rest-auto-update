package eu.mrndesign.matned.jsonplaceholder.repository;

import eu.mrndesign.matned.jsonplaceholder.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
