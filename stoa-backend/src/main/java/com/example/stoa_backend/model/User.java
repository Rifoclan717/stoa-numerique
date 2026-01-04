package com.example.stoa_backend.model;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
public class User {

    @Id
    private long Id;
    private String username;

    public User(String username) {
        this.username = username;
    }

}

    

