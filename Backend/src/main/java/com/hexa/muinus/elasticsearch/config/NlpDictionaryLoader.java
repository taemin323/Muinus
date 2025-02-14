package com.hexa.muinus.elasticsearch.config;

import lombok.extern.slf4j.Slf4j;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Component
public class NlpDictionaryLoader implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        // 사용자 사전
        String filePath = "src/main/resources/search-data/dictionary.txt";

        // 파일에서 단어 목록 읽기 (각 줄에 하나의 단어)
        List<String> customWords = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        log.info("Custom words: {}", customWords);
        log.info("Custom words size: {}", customWords.size());
        // 사용자 정의 단어 등록
        OpenKoreanTextProcessorJava.addNounsToDictionary(customWords);
        log.info("Loaded {} dictionary words", customWords.size());
    }
}
