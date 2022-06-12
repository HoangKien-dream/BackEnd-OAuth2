package com.example.springoauth2.controller;

import com.example.springoauth2.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;
  @RequestMapping(method = RequestMethod.GET)
    public Map<String,String> getCode(@RequestParam String clientId,@RequestParam String scope){
      Map<String,String> map = new HashMap<>();
      map.put("AuthorazationCode",authenticationService.generateCode(clientId, scope));
       return map;
  }
  @RequestMapping(path = "/token",method = RequestMethod.GET)
  public Map<String, String> getToken(@RequestParam String code, @RequestParam String clientId, @RequestParam String clientSecret){
      String token = authenticationService.exchangeToken(code, clientId, clientSecret);
      Map<String,String> map = new HashMap<>();
      map.put("accessToken",token);
      return map;
  }
}
