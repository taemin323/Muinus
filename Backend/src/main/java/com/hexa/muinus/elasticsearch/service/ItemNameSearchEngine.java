package com.hexa.muinus.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.AnalyzeRequest;
import co.elastic.clients.elasticsearch.indices.AnalyzeResponse;
import co.elastic.clients.elasticsearch.indices.analyze.AnalyzeToken;
import com.hexa.muinus.common.exception.ESErrorCode;
import com.hexa.muinus.common.exception.MuinusException;
import com.hexa.muinus.elasticsearch.domain.ESItem;
import com.hexa.muinus.elasticsearch.dto.SearchNativeDTO;
import com.hexa.muinus.elasticsearch.repository.ESItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemNameSearchEngine {

    private final ElasticsearchClient elasticsearchClient;
    private final OktService nlpService;
    private final ESItemRepository esItemRepository;

    private final static Set<String> subKeywords = Set.of("케이크", "케익", "콘", "바");

    public List<ESItem> searchByQuery(SearchNativeDTO dto) {
        String query = dto.getQuery();

        List<String> tokens = getTokens(query, "item_name", "custom_search_analyzer");

        if(tokens.isEmpty()){
            return List.of();
        }

        List<String> mainTokens = new ArrayList<>();
        List<String> subTokens = new ArrayList<>();

        for (String token : tokens) {
            if(subTokens.contains(token)) {
                subTokens.add(token);
            }else{
                mainTokens.add(token);
            }
        }


        try { 
            List<ESItem> items = searchNoriOperation(mainTokens, subTokens, "item_name.nori", dto);
            if(items.isEmpty()){
                items = searchNoriOperation(mainTokens, subTokens, "item_name.nori_shingle", dto);
            }
            return items;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MuinusException(ESErrorCode.ES_QUERY_ERROR, "Elasticsearch 검색 중 오류 발생");
        }
    }

    private List<ESItem> searchNoriOperation(List<String> mainTokens, List<String> subTokens, String analyzer, SearchNativeDTO condition) throws IOException {
        SearchRequest.Builder searchRequestBuilder = new SearchRequest.Builder();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        // add main, sub tokens
        addMatchQuery(boolQuery, mainTokens, analyzer, 5f);
        addMatchQuery(boolQuery, subTokens, analyzer, 2f);

        // 당, 칼로리
        addRangeFilter(boolQuery, "sugars", condition.getMinSugar(), condition.getMaxSugar());
        addRangeFilter(boolQuery, "calories", condition.getMinCal(), condition.getMaxCal());

        // 최소 하나는 매칭 (전체 검색 방지)
        boolQuery.minimumShouldMatch(1);

        // 검색
        SearchRequest searchRequest = searchRequestBuilder.build();
        SearchResponse<ESItem> searchResponse = elasticsearchClient.search(searchRequest, ESItem.class);

        return searchResponse.hits().hits().stream()
                .map(Hit::source)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private void addMatchQuery(BoolQueryBuilder boolQuery, List<String> tokens, String field ,float boost) {
        for(String token : tokens){
            boolQuery.should(QueryBuilders.constantScoreQuery(QueryBuilders.matchQuery(field, token)).boost(boost));
        }
    }

    // 당, 칼로리
    private void addRangeFilter(BoolQueryBuilder boolQuery, String field, int min, int max) {
        boolQuery.filter(QueryBuilders.rangeQuery(field).gte(min).lte(max));
    }

    /**
     * 검색쿼리 -> 토큰 가져오기
     * @param query
     * @param index
     * @param analyzer
     * @return
     */
    public List<String> getTokens(String query, String index, String analyzer) {
        try {
            // AnalyzeRequest 빌더를 사용해 요청 생성
            AnalyzeRequest request = AnalyzeRequest.of(a -> a
                    .index(index)                           // 인덱스 지정
                    .analyzer(analyzer)       // 사용할 analyzer 지정
                    .text(query)
            );

            // Analyze API 호출
            AnalyzeResponse response = elasticsearchClient.indices().analyze(request);
            List<AnalyzeToken> analyzeTokens = response.tokens();

            List<String> tokens = analyzeTokens.stream().map(AnalyzeToken::token).toList();
            return tokens;

        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}

