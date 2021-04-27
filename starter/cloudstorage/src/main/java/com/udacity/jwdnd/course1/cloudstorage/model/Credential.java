package com.udacity.jwdnd.course1.cloudstorage.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public Integer getCredentialid() {
        return credentialid;
    }

    public void setCredentialid(Integer credentialid) {
        this.credentialid = credentialid;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }


}
