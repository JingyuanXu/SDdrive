package com.udacity.jwdnd.course1.cloudstorage.entity;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

public interface CredentialAbstract {
    public Credential[] getCredentialByUserId(Integer userId);
    public Credential getCredential(Integer noteId);
    public void deleteCredential(Integer noteId);
    public void addCredential(String url, String userName, String credentialUserName, String key, String password);
}
