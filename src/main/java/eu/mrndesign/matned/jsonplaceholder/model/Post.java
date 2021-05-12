package eu.mrndesign.matned.jsonplaceholder.model;

import eu.mrndesign.matned.jsonplaceholder.dto.IPostDTO;
import eu.mrndesign.matned.jsonplaceholder.exception.NullDataProvidedException;

import javax.persistence.*;

@Entity
@Table(name = "Json_Place_Holder")
public class Post implements IPost{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dataBaseId;

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

    public Long getDataBaseId() {
        return dataBaseId;
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
