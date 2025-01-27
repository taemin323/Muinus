package com.hexa.muinus.users.controller;

import com.hexa.muinus.users.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OauthController {

    private final OauthService oauthService;

    @GetMapping("/api/users/login")
    public ResponseEntity<?> kakaoLogin(@RequestParam("code") String authorizationCode) {
        String accessToken = oauthService.getAccessTokenFromKakao(authorizationCode);
        String userEmail = oauthService.getUserKakaoProfile(accessToken);
        return ResponseEntity.ok(oauthService.findUser(userEmail));
    }
}
