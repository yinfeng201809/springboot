package com.example.demo.domain;

import com.example.demo.spring.aopstudy.MyAopAnno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = true, unique = true)
    private String nickName;
    @Column(nullable = false)
    private String regTime;

    @javax.persistence.Transient
    List<String> phoneList;

    @Transient
    private Date dateTest;
    public String aopmethod(String name) {
        return name;
    }

    public String aopmethod2(String name) {
        if (name == null) {
//            throw new RuntimeException("name is null");
        }
        return name;
    }

    public User changeUser(User user) {
        return user;
    }

    @MyAopAnno
    public User annoUser(User user) {
        return user;
    }
}
