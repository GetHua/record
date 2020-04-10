package com.fern.record.form;

import lombok.Data;

@Data
public class UserForm {

    private String id;

    private String username;
    private String nickname;
    private String headImg;
    private String password;

    private String groupId;
}
