package com.hexa.muinus.elasticsearch.service;

import lombok.extern.slf4j.Slf4j;
import org.openkoreantext.processor.OpenKoreanTextProcessor;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import org.springframework.stereotype.Service;
import scala.collection.Iterator;
import scala.collection.Seq;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
@Service
public class OktService {

    // 0kcal, 영어 단어
    private final static Pattern SAVE_PATTERN = Pattern.compile("0(?:칼로리|kcal)|[a-z]+");

    public List<String> extractKeywords(String text) {
        // keywords
        List<String> nouns = new ArrayList<>();

        // 전체 소문자로 변환
        String lowercasedText = text.toLowerCase();
        log.info("Extracting keywords from lowercasedText {}", lowercasedText);

        // 텍스트를 정규화하여 CharSequence로 반환
        CharSequence normalizedText = OpenKoreanTextProcessor.normalize(lowercasedText);
        log.info("Normalized text: {}", normalizedText);

        // 영어, 0kcal 보존
        List<String> savedTokens = extractSaveTokens(lowercasedText);
        nouns.addAll(savedTokens);
        log.debug("Saved text: {}", savedTokens);

        // 형태소 분석 결과
        Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessor.tokenize(normalizedText.toString());
        log.debug("Tokenized text: {}", tokens);

        // Seq를 Iterator로 변환하여 순회
        Iterator<KoreanTokenizer.KoreanToken> tokenIterator = tokens.iterator();
        // 명사만 보존
        while (tokenIterator.hasNext()) {
            KoreanTokenizer.KoreanToken token = tokenIterator.next();
            log.debug("Token {}: {}", token, token.pos());

            if ("Noun".equals(token.pos().toString())) {
                nouns.add(token.text());
            }
        }
        log.info("Nouns : {}", nouns);
        log.debug("Nouns size: {}", nouns.size());
        return nouns;
    }

    public String generateText(List<String> keywords) {
        return String.join(" ", keywords);
    }

    // 한글이 아니라 보존 할 단어 추출
    public static List<String> extractSaveTokens(String text) {
        List<String> tokens = new ArrayList<>();
        Matcher matcher = SAVE_PATTERN.matcher(text);
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }

}

