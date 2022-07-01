package com.example.demo.domain;

import com.example.demo.spring.validation.MyConstraint;
import com.example.demo.support.UserSexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity  implements Serializable {

    private static final long serialVersionUID = 1L;
    @MyConstraint(min = 1,max = 10)
    private Long id;
    private String userName;
    private String passWord;
    private UserSexEnum userSex;
    private String nickName;

    public UserEntity(String userName, String passWord, UserSexEnum userSex) {
        super();
        this.passWord = passWord;
        this.userName = userName;
        this.userSex = userSex;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "userName " + this.userName + ", pasword " + this.passWord + "sex " + userSex.name();
    }
}
