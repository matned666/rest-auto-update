package eu.mrndesign.matned.jsonplaceholder.dto;

public class PostDTO implements IPostDTO {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getBody() {
        return body;
    }
}
