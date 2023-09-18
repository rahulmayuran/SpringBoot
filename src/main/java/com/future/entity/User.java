package com.future.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "USER_TBL")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String gender;
}
