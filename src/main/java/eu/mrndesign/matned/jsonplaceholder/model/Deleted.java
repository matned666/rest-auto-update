package eu.mrndesign.matned.jsonplaceholder.model;

import javax.persistence.Entity;

@Entity
public class Deleted extends BaseEntity{

    private Integer id;

    public Integer getId() {
        return id;
    }

    public Deleted() {
    }

    public Deleted(Integer id) {
        this.id = id;
    }

    @Override
    public Long getDataBaseId() {
        return dataBaseId;
    }
}
