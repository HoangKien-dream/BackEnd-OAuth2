package com.example.springoauth2.repository;

import com.example.springoauth2.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoAccount extends JpaRepository<Account,Integer> {
    Account findByUsername(String name);
}
