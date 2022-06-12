package com.example.springoauth2.repository;

import com.example.springoauth2.entity.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoScope extends JpaRepository<Scope,Integer> {
    Scope findByName(String name);
}
