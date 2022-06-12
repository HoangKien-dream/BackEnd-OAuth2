package com.example.springoauth2.repository;

import com.example.springoauth2.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCredential extends JpaRepository<Credential,Integer> {
    Credential findByAccessToken(String name);
}
