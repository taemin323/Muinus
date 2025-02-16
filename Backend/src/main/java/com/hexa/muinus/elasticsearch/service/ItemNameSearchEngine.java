package com.hexa.muinus.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.hexa.muinus.common.exception.ESErrorCode;
import com.hexa.muinus.common.exception.MuinusException;
import com.hexa.muinus.elasticsearch.domain.ESItem;
import com.hexa.muinus.elasticsearch.dto.SearchNativeDTO;
import com.hexa.muinus.elasticsearch.repository.ESItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemNameSearchEngine {

    private final ElasticsearchClient elasticsearchClient;
    private final OktService nlpService;
    private final ESItemRepository esItemRepository;

    public List<ESItem> searchByQuery(SearchNativeDTO dto) {
        String query = dto.getQuery();
        Integer minSugar = dto.getMinSugar();
        Integer maxSugar = dto.getMaxSugar();
        Integer minCal = dto.getMinCal();
        Integer maxCal = dto.getMaxCal();

        try {
            List<ESItem> results = esItemRepository.searchItemsByQuery(query, minSugar, maxSugar, minCal, maxCal);
            log.debug("results: {}", results);

            return results;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MuinusException(ESErrorCode.ES_QUERY_ERROR, "Elasticsearch 검색 중 오류 발생");
        }
    }

    public List<ESItem> searchTest(String query) {
        try {
            List<ESItem> results = esItemRepository.confirmItems(query);
            log.debug("results: {}", results);

            return results;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MuinusException(ESErrorCode.ES_QUERY_ERROR, "Elasticsearch 검색 중 오류 발생");
        }
    }



    public List<String> search(String queryText) throws IOException {
        // 텍스트에서 키워드(명사) 추출
        List<String> keywords = nlpService.extractKeywords(queryText);

        // 키워드가 하나의 문자열로 결합되도록 한다.
        String keywordQuery = String.join(" ", keywords);

        // Elasticsearch 쿼리 작성
//        SearchRequest searchRequest = new SearchRequest.Builder()
//                .query(q -> q.match(m -> m.field("message").query(keywordQuery))) // "message" 필드에서 키워드 검색
//                .build();


        // 동적으로 쿼리 생성
        SearchRequest.Builder searchRequestBuilder = new SearchRequest.Builder();
        searchRequestBuilder.query(q -> q.bool(b -> {
            keywords.forEach(keyword -> {
//                // 'must' 조건 추가 (모든 키워드가 포함되어야 하는 조건)
//                b.must(m -> m.match(mm -> mm.field("item_name.ngram_shingle")
//                        .query(keyword)
//                        .boost(2.0f)
//                        .fuzziness("AUTO")));

                // 'should' 조건 추가 (각 키워드가 포함되어야 하는 조건, 부스트 낮게)
                b.should(m -> m.match(mm -> mm.field("item_name.ngram_shingle")
                        .query(keyword)
                        .boost(0.5f)
                        .fuzziness("AUTO")));
            });
            return b;
        }));

        // 빌드된 쿼리로 SearchRequest 생성
        SearchRequest searchRequest = searchRequestBuilder.build();




        // 검색 실행
        SearchResponse<Object> searchResponse = elasticsearchClient.search(searchRequest, Object.class);

        // 검색 결과에서 필요한 정보 추출 (여기선 source를 그대로 출력)
        return searchResponse.hits().hits().stream()
                .map(Hit::source).filter(Objects::nonNull)
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}

