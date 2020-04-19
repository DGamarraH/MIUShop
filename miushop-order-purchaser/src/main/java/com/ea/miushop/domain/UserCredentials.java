package com.ea.miushop.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_credentials")
public class UserCredentials {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCredentialsId;

    private String username;

    private String password;

    public UserCredentials() {
    }

    public Long getUserCredentialsId() {
        return userCredentialsId;
    }

    public void setUserCredentialsId(Long userCredentialsId) {
        this.userCredentialsId = userCredentialsId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
