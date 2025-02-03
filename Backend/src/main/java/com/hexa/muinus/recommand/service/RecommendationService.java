package com.hexa.muinus.recommand.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationService {

    private final DataSource dataSource;

    /**
     * 사용자 기반 추천: 해당 사용자가 선호하는 아이템의 유사 아이템을 추천합니다.
     * @param userId 사용자 ID
     * @param howMany 추천 받을 아이템 수
     * @return 추천된 제품 ID 리스트
     */

    public List<Long> recommendForUser(long userId, int howMany) throws TasteException, SQLException {
        // MySQLJDBCDataModel 생성 (테이블, user_id, product_id, preference, timestamp 컬럼 순서)
        DataModel model = new MySQLJDBCDataModel(dataSource, "preference", "user_no", "item_id", "score", "timestamp");

        // 아이템 간 유사도 계산 (여기서는 Pearson Correlation 사용)
        ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);

        // 아이템 기반 추천기 생성
        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(model, similarity);

        // 해당 사용자의 선호 로그를 가져옴
        PreferenceArray userPrefs = model.getPreferencesFromUser(userId);
        if (userPrefs.length() == 0) {
            return Collections.emptyList();
        }
        // 사용자가 가장 선호하는 아이템을 찾음
        long topItemId = -1;
        float maxPreference = Float.MIN_VALUE;
        for (Preference pref : userPrefs) {
            if (pref.getValue() > maxPreference) {
                maxPreference = pref.getValue();
                topItemId = pref.getItemID();
            }
        }
        // 해당 아이템과 유사한 아이템을 찾음
        List<RecommendedItem> recommendedItems = recommender.mostSimilarItems(topItemId, howMany);
        List<Long> result = new ArrayList<>();
        for (RecommendedItem rec : recommendedItems) {
            result.add(rec.getItemID());
        }
        return result;
    }

    /**
     * 찾으려는 물품이 없을 때, 해당 물품과 가장 연관된 물품 추천
     * @param userId
     * @param howMany
     * @return
     * @throws TasteException
     * @throws SQLException
     */
    public List<Long> recommendForUserWithItem(long userId, int itemId, int howMany) throws TasteException, SQLException {
        // MySQLJDBCDataModel 생성 (테이블, user_id, product_id, preference, timestamp 컬럼 순서)
        DataModel model = new MySQLJDBCDataModel(dataSource, "preference", "user_no", "item_id", "score", "timestamp");

        // 아이템 간 유사도 계산 (여기서는 Pearson Correlation 사용)
        ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);

        // 아이템 기반 추천기 생성
        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(model, similarity);

        // 해당 사용자의 선호 로그를 가져옴
        PreferenceArray userPrefs = model.getPreferencesFromUser(userId);
        if (userPrefs.length() == 0) {
            return Collections.emptyList();
        }

        // 해당 아이템과 유사한 아이템을 찾음
        List<RecommendedItem> recommendedItems = recommender.mostSimilarItems(itemId, howMany);
        List<Long> result = new ArrayList<>();
        for (RecommendedItem rec : recommendedItems) {
            result.add(rec.getItemID());
        }
        return result;
    }

    /**
     * Trending 추천: 전체 로그에서 각 제품의 총 선호도 합계가 높은 순으로 추천합니다.
     * @param howMany 추천 받을 아이템 수
     * @return 추천된 제품 ID 리스트
     */
    public List<Long> getTrendingItems(int howMany) throws SQLException {
        String query = "SELECT product_id, SUM(preference) as total_preference " +
                "FROM user_log GROUP BY product_id " +
                "ORDER BY total_preference DESC LIMIT ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, howMany);
            try (ResultSet rs = ps.executeQuery()) {
                List<Long> trendingItems = new ArrayList<>();
                while (rs.next()) {
                    trendingItems.add(rs.getLong("product_id"));
                }
                return trendingItems;
            }
        }
    }
}

