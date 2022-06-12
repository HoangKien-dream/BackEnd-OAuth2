package com.example.springoauth2.controller;

import com.example.springoauth2.entity.Account;
import com.example.springoauth2.entity.Client;
import com.example.springoauth2.entity.Credential;
import com.example.springoauth2.entity.Scope;
import com.example.springoauth2.repository.RepoAccount;
import com.example.springoauth2.repository.RepoClient;
import com.example.springoauth2.repository.RepoCredential;
import com.example.springoauth2.repository.RepoScope;
import com.example.springoauth2.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/user")
public class AccountController {
    @Autowired
    RepoAccount repoAccount;
    @Autowired
    RepoClient repoClient;
    @Autowired
    RepoScope repoScope;
    @Autowired
    RepoCredential repoCredential;

    public static String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }

    @RequestMapping(path = "login", method = RequestMethod.POST)
    public Map<String, String> login(@RequestBody Account account) {
        Map<String, String> map = new HashMap<>();
        Account account1 = repoAccount.findByUsername(account.username);
        if (account1 != null) {
            if (account1.getPassword().equals(account.getPassword())) {
                map.put("Message", "Đăng nhập thành công");
                map.put("username", account1.username);
                return map;
            }
        }
        map.put("Message", "Đăng nhập thất bại");
        return map;
    }

    @RequestMapping(path = "auth", method = RequestMethod.GET)
    public Map<String, Object> getConsentScreen(@RequestParam String id) {
        Client client = repoClient.findByClientId(id);
        List<Scope> scopes = repoScope.findAll();
        if (client != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("client", client);
            map.put("scope", scopes);
            return map;
        }
        return null;
    }


    @RequestMapping(path = "profile", method = RequestMethod.GET)
    public Map<String, String> profile() {
        String token = getBearerTokenHeader();
        String[] accessToken = token.split(" ");
        Credential credential = repoCredential.findByAccessToken(accessToken[1]);
        Map<String, String> map = new HashMap<>();
        if (credential.getScope().equals("profile")) {
            map.put("username", "Hoàng Kiên");
            map.put("thumbnail", "https://i.vietgiaitri.com/2021/2/27/dac-san-gai-xinh-truong-nguoi-ta-khi-chup-can-mat-ngu-quan-sac-sao-than-thai-dinh-cao-3d3-5602224.jpg");
            map.put("gender", "Nam");
            return map;
        }
        map.put("forbbiden", "Bạn không có quyền vào trang này");
        return map;
    }

    @RequestMapping(path = "list", method = RequestMethod.GET)
    public Map<String, String> list() {
        String token = getBearerTokenHeader();
        String[] accessToken = token.split(" ");
        Credential credential = repoCredential.findByAccessToken(accessToken[1]);
        Map<String, String> map = new HashMap<>();
        if (credential.getScope().equals("listAccount")) {
            map.put("Name1", "Hoàng Kiên");
            map.put("Name2", "Hoàng Nguyên");
            map.put("Name3", "Ngô Quang");
            return map;
        }
        map.put("forbbiden", "Bạn không có quyền vào trang này");
        return map;

    }
}
