package main.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String country;
    @Column
    private String locale;
    @Column
    private String address;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Address() {
    }

    public Address(Long id, String country, String locale, String address) {
        this.id = id;
        this.country = country;
        this.locale = locale;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUserInAddress() {
        return user;
    }

    public void setUserInAddress(User userInAddress) {
        this.user = userInAddress;
    }
}
