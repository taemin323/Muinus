package com.hexa.muinus.elasticsearch.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;

@Configuration
public class ElasticsearchConfig {

    // application.yml에 정의한 ES 관련 설정 값 주입
    @Value("${spring.elasticsearch.rest.uris}")
    private String elasticsearchUris;

    @Value("${spring.elasticsearch.rest.username:}")
    private String elasticsearchUsername;

    @Value("${spring.elasticsearch.rest.password:}")
    private String elasticsearchPassword;

    /**
     * RestHighLevelClient 빈 생성
     * - ES 서버 주소는 application.yml의 spring.elasticsearch.rest.uris 값을 사용합니다.
     * - username, password가 설정되어 있으면 Basic 인증을 적용합니다.
     */
    @Bean
    public RestHighLevelClient client() {
        RestClientBuilder builder = RestClient.builder(HttpHost.create(elasticsearchUris));

        // 인증 정보가 있으면 BasicCredentialsProvider 설정
        if (elasticsearchUsername != null && !elasticsearchUsername.isEmpty()) {
            BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(
                    AuthScope.ANY,
                    new UsernamePasswordCredentials(elasticsearchUsername, elasticsearchPassword)
            );
            builder.setHttpClientConfigCallback(httpClientBuilder ->
                    httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
            );
        }
        return new RestHighLevelClient(builder);
    }

    /**
     * ElasticsearchConverter 빈 생성
     * - MappingElasticsearchConverter를 사용해 도메인 객체와 Elasticsearch 문서 간의 매핑을 지원합니다.
     */
    @Bean
    public ElasticsearchConverter elasticsearchConverter() {
        return new MappingElasticsearchConverter(elasticsearchMappingContext());
    }

    /**
     * SimpleElasticsearchMappingContext 빈 생성
     * - 도메인 객체와 Elasticsearch 인덱스 간의 매핑 정보를 관리합니다.
     */
    @Bean
    public SimpleElasticsearchMappingContext elasticsearchMappingContext() {
        return new SimpleElasticsearchMappingContext();
    }

    /**
     * ElasticsearchRestTemplate 빈 생성
     * - Spring Data Elasticsearch 4.x에서는 ElasticsearchTemplate 대신 ElasticsearchRestTemplate을 사용합니다.
     */
    @Bean("elasticsearchTemplate")
    public ElasticsearchRestTemplate elasticsearchRestTemplate(RestHighLevelClient client, ElasticsearchConverter converter) {
        return new ElasticsearchRestTemplate(client, converter);
    }
}
