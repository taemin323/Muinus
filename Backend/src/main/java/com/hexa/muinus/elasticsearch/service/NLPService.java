package com.hexa.muinus.elasticsearch.service;

import lombok.extern.slf4j.Slf4j;
import org.openkoreantext.processor.OpenKoreanTextProcessor;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import org.springframework.stereotype.Service;
import scala.collection.Iterator;
import scala.collection.Seq;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class NLPService {

    public List<String> extractKeywords(String text) {
        // 텍스트를 정규화하여 CharSequence로 반환
        CharSequence normalizedText = OpenKoreanTextProcessor.normalize(text);

        log.info("Normalized text: {}", normalizedText);
        // 형태소 분석 결과: Seq<KoreanToken> 반환
        Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessor.tokenize(normalizedText.toString());
        log.debug("Tokenized text: {}", tokens);


        // 명사 추출
        List<String> nouns = new ArrayList<>();

        // Seq를 Iterator로 변환하여 순회
        Iterator<KoreanTokenizer.KoreanToken> tokenIterator = tokens.iterator();

        while (tokenIterator.hasNext()) {
            KoreanTokenizer.KoreanToken token = tokenIterator.next();
            log.debug("Token pos: {}", token.pos());
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

}

