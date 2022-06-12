package com.example.springoauth2.repository;

import com.example.springoauth2.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoClient extends JpaRepository<Client,String> {
    Client findByClientId(String name);
}
