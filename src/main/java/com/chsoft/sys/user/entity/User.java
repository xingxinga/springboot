package com.chsoft.sys.user.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
    private String id;

    private String username;

    private String password;

    private String privatekeyfilepath;

    private String certificatefile;

    private String fabricusername;

    private String fabricaffiliation;

    private String fabricmspid;

}