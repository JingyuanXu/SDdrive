package com.udacity.jwdnd.course1.cloudstorage.entity;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

import java.util.List;

public interface CredentialAbstract {
    public List<Credential> getCredentialByUserId(Integer userId);
    public Credential getCredential(Integer noteId);
    public void deleteCredential(Integer noteId);
    public int addCredential(Credential credential);
    public void updateCredential(Credential credential);
}
