package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.CredentialAbstract;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService implements CredentialAbstract {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CredentialMapper credentialMapper;

    public CredentialService(UserMapper userMapper, CredentialMapper credentialMapper) {
        this.userMapper = userMapper;
        this.credentialMapper = credentialMapper;
    }
    @Override
    public List<Credential> getCredentialByUserId(Integer userId) {
        return credentialMapper.getCredentialByUserId(userId);
    }
    @Override
    public Credential getCredential(Integer noteId) {
        return credentialMapper.getCredential(noteId);
    }
    @Override
    public void deleteCredential(Integer noteId) {
        credentialMapper.deleteCredential(noteId);
    }
//    public void addCredential(String url, String userName, String credentialUserName, String key, String password) {
//        Integer userId = userMapper.getUserByName(userName).getUserId();
//        Credential credential = new Credential(0, url, credentialUserName, key, password, userId);
//        credentialMapper.createCredential(credential);
//    }

    @Override
    public int addCredential(Credential credential) {
        return credentialMapper.createCredential(credential);
    }
    @Override
    public void updateCredential(Credential credential) {credentialMapper.updateCredential(credential);};
}
