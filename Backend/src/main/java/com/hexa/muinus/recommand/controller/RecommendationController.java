package com.hexa.muinus.recommand.controller;

import com.hexa.muinus.recommand.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/recommend")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    /**
     * 사용자별 추천 API
     * 예시 URL: GET /api/recommend/user/123?limit=5
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserRecommendations(@PathVariable("userId") long userId,
                                                    @RequestParam(name = "limit", defaultValue = "5") int limit) {
        try {
            List<Long> recommendedItemIds = recommendationService.recommendForUser(userId, limit);
            return ResponseEntity.ok(recommendedItemIds);
        } catch (TasteException | SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Trending 추천 API
     * 예시 URL: GET /api/recommend/trending?limit=5
     */
    @GetMapping("/trending")
    public ResponseEntity<?> getTrendingRecommendations(@RequestParam(name = "limit", defaultValue = "5") int limit) {
        try {
            List<Long> trendingItems = recommendationService.getTrendingItems(limit);
            return ResponseEntity.ok(trendingItems);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}


