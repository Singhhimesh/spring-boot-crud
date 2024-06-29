package com.bagisto.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Username can not be empty !!")
    @Size(min = 3, max = 255, message = "Username must be between 3 - 12 character")
    @Column(name = "user_name")
    private String username;

    @NotBlank(message = "Email can not be empty")
    @Email(message = "Email is invalid format")
    private String email;

    @Size(min = 3, max = 12, message = "Password must be between 3 - 12 character")
    private String password;

    @AssertTrue(message = "Must agree terms and condition")
    private boolean agreed;

    public User() {
    }

    public User(boolean agreed, String email, Integer id, String password, String username) {
        this.agreed = agreed;
        this.email = email;
        this.id = id;
        this.password = password;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAgreed() {
        return agreed;
    }

    public void setAgreed(boolean agreed) {
        this.agreed = agreed;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", agreed="
                + agreed + "]";
    }
}