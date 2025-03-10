package com.hexa.muinus.elasticsearch.service;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.util.ObjectBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueryCreator {

    public static Function<ConstantScoreQuery.Builder, ObjectBuilder<ConstantScoreQuery>> buildConstantScoreQuery(String field, String text, float boost) {
        return csq -> csq
                .filter(f -> f.match(m -> m.field(field).query(text)))
                .boost(boost);
    }

    public static Function<RangeQuery.Builder, ObjectBuilder<RangeQuery>> buildRangeFilter(String field, double min, double max) {
        return r -> r.number(rn -> rn.field(field).gte(min).lte(max));
    }


    public static Function<TermsQuery.Builder, ObjectBuilder<TermsQuery>> buildTermsQuery(String field, List<Integer> values) {
        List<FieldValue> fieldValues = values.stream()
                .map(FieldValue::of)
                .toList();

        return termsBuilder -> termsBuilder
                .field(field)
                .terms(t -> t.value(fieldValues));
    }

    public static Function<ConstantScoreQuery.Builder, ObjectBuilder<ConstantScoreQuery>> buildFuzzyMatchQuery(String field, String text, float boost) {
        return csq -> csq
                .filter(f -> f.match(m -> m.field(field).query(text).fuzziness("AUTO").operator(Operator.And).boost(boost)));
    }

    public static Function<ConstantScoreQuery.Builder, ObjectBuilder<ConstantScoreQuery>> buildFuzzySynonymMatchQuery(String field, String text, float boost) {
        return csq -> csq
                .filter(f -> f.match(m -> m.field(field).query(text).fuzziness("AUTO").operator(Operator.Or).boost(boost)));
    }

    public static Function<MatchQuery.Builder, ObjectBuilder<MatchQuery>> buildSynonymMatchQuery(String field, String text, float boost) {
        return match -> match
                .field(field)
                .query(text)
                .operator(Operator.Or)
                .boost(boost);
    }

    public static Sort buildScoreSort() {
        return Sort.by(Sort.Order.desc("_score"));
    }






}
