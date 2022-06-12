package com.example.springoauth2.repository;

import com.example.springoauth2.entity.AuthorizationCode;
import com.example.springoauth2.entity.Client;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepoAuthenticationService extends JpaRepository<AuthorizationCode,Integer> {
  AuthorizationCode findByClientId(String clientId);
    AuthorizationCode findByCode(String code);
}
