package com.project.jcloud.login;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class userDto {
    private String userId;
    private String userPw;
    private String userName;
    private String userDir;
    private String confWifi;
    private String createdDate;
    private int uploadFileCnt;
}
