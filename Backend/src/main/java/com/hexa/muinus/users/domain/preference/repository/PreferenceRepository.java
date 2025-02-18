package com.hexa.muinus.users.domain.preference.repository;

import com.hexa.muinus.elasticsearch.dto.PreferTrends;
import com.hexa.muinus.elasticsearch.dto.PrefereTrendsProjection;
import com.hexa.muinus.users.domain.preference.Preference;
import com.hexa.muinus.users.domain.preference.PreferenceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.util.List;

public interface PreferenceRepository extends JpaRepository<Preference, PreferenceId> {
    @Query(value = """
        SELECT i.item_name, i.item_id
        FROM preference p
        JOIN users u ON u.user_no = p.user_no
        AND  u.email = :userEmail
        JOIN item i ON i.item_no = p.item_no
        WHERE p.updated_at = :today
        -- AND		daily_score = 0  -- 어제 먹은거랑 비슷한 건 빼자
    """, nativeQuery = true)
    public List<PrefereTrendsProjection> findItemsByScore(String userEmail, LocalDate today);
//
//    public interface PreferProjection(){
//
//    }
}
