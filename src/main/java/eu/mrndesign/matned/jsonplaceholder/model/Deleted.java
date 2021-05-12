package eu.mrndesign.matned.jsonplaceholder.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Deleted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long databaseId;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public Deleted() {
    }

    public Deleted(Integer id) {
        this.id = id;
    }
}
