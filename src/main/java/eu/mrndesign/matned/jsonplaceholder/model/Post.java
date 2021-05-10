package eu.mrndesign.matned.jsonplaceholder.model;

import eu.mrndesign.matned.jsonplaceholder.dto.IPostDTO;
import eu.mrndesign.matned.jsonplaceholder.dto.PostDTO;
import eu.mrndesign.matned.jsonplaceholder.exception.NullDataProvidedException;

import javax.persistence.*;

@Entity
@Table(name = "Json_Place_Holder")
public class Post implements IPost{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dataBaseId;

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    public Post() {
    }

    public Post(Integer userId, Integer id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @Override
    public IPost applyNew(IPostDTO entity) {
        if (entity != null)
                return new Post(
                        entity.getUserId(),
                        entity.getId(),
                        entity.getBody(),
                        entity.getTitle());
        else throw new NullDataProvidedException();
    }

    public static Post apply(IPostDTO data) {
        return new Post();
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
