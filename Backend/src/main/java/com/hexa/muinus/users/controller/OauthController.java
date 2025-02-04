package com.hexa.muinus.users.controller;

import com.hexa.muinus.common.jwt.JwtProvider;
import com.hexa.muinus.users.domain.user.Users;
import com.hexa.muinus.users.service.OauthService;
import com.hexa.muinus.users.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OauthController {

    private final OauthService oauthService;
    private final JwtProvider jwtProvider;

    @GetMapping("/api/users/login")
    public void kakaoLogin(HttpServletResponse response) {
        oauthService.getAuthorizationCode(response);
    }

    @GetMapping("/api/users/kauth")
    public void kakaoLogin(@RequestParam("code") String authorizationCode, HttpServletResponse response) throws Exception {
        String accessToken = oauthService.getAccessTokenFromKakao(authorizationCode);
        String userEmail = oauthService.getUserKakaoProfile(accessToken);
        Users user = oauthService.findUser(userEmail, response);
        jwtProvider.issueTokens(user, response);
        oauthService.redirectToMainPage(response);
    }
}
