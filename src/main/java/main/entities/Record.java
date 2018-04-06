package main.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Record {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String value;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Record() {
    }

    public Record(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getUserInRecord() {
        return user;
    }

    public void setUserInRecord(User userInRecord) {
        this.user = userInRecord;
    }
}
