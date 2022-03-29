package com.example.demo.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Member {
    @Id
    private String userId;
    private String userName;
    private String password;

    public Member(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public Member() {

    }
}
