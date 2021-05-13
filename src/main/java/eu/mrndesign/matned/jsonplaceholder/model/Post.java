package eu.mrndesign.matned.jsonplaceholder.model;

import eu.mrndesign.matned.jsonplaceholder.dto.IPostDTO;
import eu.mrndesign.matned.jsonplaceholder.exception.NullDataProvidedException;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Json_Place_Holder")
public class Post extends BaseEntity implements IPost{

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    private boolean isEdited;

    public Post() {
    }

    public Post(Integer userId, Integer id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
        isEdited = false;
    }

    @Override
    public IPost applyNew(IPostDTO dto) {
        if (dto != null)
                return new Post(
                        dto.getUserId() != null? dto.getUserId(): this.userId,
                        dto.getId() != null? dto.getId() : this.id,
                        dto.getBody() != null? dto.getBody() : this.body,
                        dto.getTitle() != null? dto.getTitle() : this.title);
        else throw new NullDataProvidedException();
    }

    @Override
    public void edit(IPostDTO data) {
        if (data != null){
            if(data.getBody() != null) this.body = data.getBody();
            if(data.getTitle() != null) this.title = data.getTitle();
            isEdited = true;
        }
        else throw new NullDataProvidedException();

    }

    @Override
    public Long getDataBaseId() {
        return dataBaseId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return isEdited == post.isEdited && Objects.equals(dataBaseId, post.dataBaseId) && Objects.equals(userId, post.userId) && Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(body, post.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataBaseId, userId, id, title, body, isEdited);
    }
}
