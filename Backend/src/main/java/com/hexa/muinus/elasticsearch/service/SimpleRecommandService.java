package com.hexa.muinus.elasticsearch.service;

import com.hexa.muinus.elasticsearch.dto.PreferTrends;
import com.hexa.muinus.store.domain.item.Item;
import com.hexa.muinus.store.dto.store.StoreMapDTO;
import com.hexa.muinus.store.dto.store.StoreSearchDTO;
import com.hexa.muinus.users.domain.preference.repository.PreferenceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SimpleRecommandService {
    private ItemAnalyzer itemAnalyzer;
    private PreferenceRepository preferenceRepository;

    private static final String INDEX = "items";
    private static final String ANALYZER = "custom_analyzer";

    public List<PreferTrends> getRecommendedItems(String userEmail) {
        log.info("getRecommendedItems userEmail:{}", userEmail);

        List<PreferTrends> preferTrends =  preferenceRepository.findItemsByScore(userEmail, LocalDate.now())
                                    .stream()
                                    .map(PreferTrends::new)
                                    .toList();





        return null;
    }



    /**
     * 각 아이템마다 키워드(토큰) 추출 -> 전체 아이템에서 각 키워드가 얼마나 등장하는지 카운팅 * 점수
     * @param preferItems
     * @return HashMap<String, Double> 키워드(토큰)별 점수
     */
    public HashMap<String, Double> extractKeywordScores(List<PreferTrends> preferItems) {
        HashMap<String, Double> tokenScore = new HashMap<>();

        for (PreferTrends item : preferItems) {
            List<String> tokens = itemAnalyzer.getAnalyzedTokens(
                    item.getItemName(), "items", "custom_analyzer");

            double itemScore = item.getTrendRating() * item.getPurchaseCount();
            for (String token : tokens) {
                tokenScore.merge(token, itemScore, Double::sum);
            }
        }
        return tokenScore;
    }
}
