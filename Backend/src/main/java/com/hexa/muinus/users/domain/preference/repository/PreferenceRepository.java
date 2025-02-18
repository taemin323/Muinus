package com.hexa.muinus.users.domain.preference.repository;

import com.hexa.muinus.elasticsearch.dto.PreferTrendsProjection;
import com.hexa.muinus.users.domain.preference.Preference;
import com.hexa.muinus.users.domain.preference.PreferenceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.util.List;

public interface PreferenceRepository extends JpaRepository<Preference, PreferenceId> {
    @Query(value = """
        SELECT
            me.item_id AS itemId,
            i.item_name AS itemName,
            me.count_mine AS purchaseCount, -- 30일 동안 구매 일수 (횟수 - 빈도)
            sm.sum_every / t.total_count AS trendRating -- 30일 모든 유저 구매 개수 합계 (상품별) / 30일 동안 모든 유저가 구매한 상품 개수 총 합 
        FROM ( 
                SELECT
                    p.item_id,
                    COUNT(p.daily_score) AS count_mine -- 구매 일수
                FROM preference p
                JOIN users u
                  ON u.user_no = p.user_no
                WHERE p.updated_at >= monthAgo
                  AND u.email = :userEmail
                GROUP BY p.item_id
            ) me
        JOIN (
                -- 전체 유저
                SELECT
                    item_id,
                    SUM(daily_score)    AS sum_every,   -- 아이템별 총 구매 개수
                FROM preference
                WHERE updated_at >= monthAgo
                GROUP BY item_id
            ) sm
        ON me.item_id = sm.item_id
        JOIN (
            -- 전체 유저
            SELECT
                SUM(daily_score) AS total_count
            FROM preference
            WHERE updated_at >= monthAgo
        ) t
        
        JOIN item i
            ON i.item_id = me.item_id
        
        ORDER BY me.item_id;
    """, nativeQuery = true)
    public List<PreferTrendsProjection> findItemsByScore(String userEmail, LocalDate monthAgo);
}
