package com.udacity.jwdnd.course1.cloudstorage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Credential {
    @JsonProperty("credentialid")
    private Integer credentialid;
    @JsonProperty("url")
    private String url;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("key")
    private String key;
    @JsonProperty("password")
    private String password;
    @JsonProperty("userid")
    private Integer userid;

    public Credential(Integer credentialid, String url, String userName, String key, String password, Integer userid) {
        this.credentialid = credentialid;
        this.url = url;
        this.userName = userName;
        this.key = key;
        this.password = password;
        this.userid = userid;
    }



}
