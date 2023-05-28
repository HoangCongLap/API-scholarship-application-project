package com.scholarship.restservice.entites;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "TAIKHOAN")
@Data
public class LoginUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String usename;
    private String password;

    @Override
    public String toString() {
        return "LoginUser{" +
                "usename='" + usename + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}