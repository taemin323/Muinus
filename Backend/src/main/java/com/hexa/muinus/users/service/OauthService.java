package com.hexa.muinus.users.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OauthService {

    @Value("${kakao.auth.client_id}")
    private String clientId;

    @Value("${kakao.auth.redirect_url}")
    private String redirectUrl;

    public String requestAccessTokenFromKakao(String authorizationCode) {
        log.info("requestAccessTokenFromKakao");

        // 카카오로 액세스 토큰 받기 위한 객체 생성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("redirect_uri", redirectUrl);
        body.add("code", authorizationCode);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, headers);

        // 카카오로 액세스 토큰 발급 요청
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        // 반환 값에서 액세스 토큰 추출
        ObjectMapper objectMapper = new ObjectMapper();
        String accessToken = "";
        try {
            accessToken = objectMapper.readTree(responseEntity.getBody()).get("access_token").asText();
            log.info("Access token:" + accessToken);
        } catch (Exception e) {
            log.error("카카오 액세스 토큰 교환 오류 발생");
        }
        return accessToken;
    }
}
