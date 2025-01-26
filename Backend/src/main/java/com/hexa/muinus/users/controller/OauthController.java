package com.hexa.muinus.users.controller;

import com.hexa.muinus.users.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OauthController {

    private final OauthService oauthService;

    @GetMapping("/api/users/login")
    public ResponseEntity<?> kakaoLogin(@RequestParam("code") String authorizationCode) {
        oauthService.requestAccessTokenFromKakao(authorizationCode);
        return ResponseEntity.ok().build();
    }
}
