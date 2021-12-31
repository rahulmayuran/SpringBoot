package com.stock.market.User.Model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Entity(name="user")
@Table(name="stock_user")
@Data
@Document("user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int userId;

    private String emailId;
    private String username;
    private String password;
    private String role;

    public User(int userId, String emailId, String username, String password, String role) {
        this.userId = userId;
        this.emailId = emailId;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
