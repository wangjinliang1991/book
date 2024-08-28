package com.example.book.service;

import com.example.book.iterator.EsQueryIterator;
import com.example.book.iterator.EsSqlQuery;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author wangjl
 * @date 2024/8/27
 */
@Service
public class EsQueryService {

    public Object queryEsBySql(EsSqlQuery esSqlQuery) {
        EsQueryIterator iterator = esSqlQuery.iterator();
        Stream<Map<String, Object>> resultStream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 0), false);
        return resultStream.collect(Collectors.toList());
    }
}
