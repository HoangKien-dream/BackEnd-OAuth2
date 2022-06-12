package com.example.springoauth2.service;

import com.example.springoauth2.entity.AuthorizationCode;
import com.example.springoauth2.entity.Client;
import com.example.springoauth2.entity.Credential;
import com.example.springoauth2.repository.RepoAuthenticationService;
import com.example.springoauth2.repository.RepoClient;
import com.example.springoauth2.repository.RepoCredential;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthenticationService {
    @Autowired
    RepoAuthenticationService repoAuthenticationService;
    @Autowired
    RepoClient repoClient;
    @Autowired
    RepoCredential repoCredential;

    public String generateCode(String clientId, String scope) {
            AuthorizationCode authorizationCode = new AuthorizationCode();
            authorizationCode.setCode(UUID.randomUUID().toString());
            authorizationCode.setClientId(clientId);
            authorizationCode.setScope(scope);
            return repoAuthenticationService.save(authorizationCode).getCode();
        }


    public String exchangeToken(String code, String clientId, String clientSecret) {
        AuthorizationCode authorizationCode = repoAuthenticationService.findByCode(code);
        Client client = repoClient.findByClientId(clientId);
        if (authorizationCode != null && client != null) {
            if (authorizationCode.getClientId().equals(clientId) && client.getClientSecret().equals(clientSecret)) {
                String token = new Oauth2Helper().generateToke();
                long expiredTime = new Oauth2Helper().expiredTime();
                Credential credential = new Credential();
                credential.setAccessToken(token);
                credential.setExpiredTime(expiredTime);
                credential.setCreatedAt(LocalDate.now());
                credential.setScope(authorizationCode.getScope());
                credential.setRefreshToken(UUID.randomUUID().toString());
                return repoCredential.save(credential).getAccessToken();
            }
            return "Không tồn tại AuthenticaionCode";
        }
        return "không tồn tại clientId";
    }

}
