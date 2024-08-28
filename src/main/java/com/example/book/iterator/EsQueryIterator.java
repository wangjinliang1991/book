package com.example.book.iterator;

import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wangjl
 * @date 2024/8/27
 */
public class EsQueryIterator implements Iterator<Map<String,Object>> {
    private String cursor;

    private List<String> columns;
    Iterator<Map<String, Object>> iterator;
    RestTemplate restTemplate = new RestTemplate();

    public EsQueryIterator(String query, Long fetchSize) {
        EsResponseData esResponseData = restTemplate.postForObject("http://localhost:9200/_sql?format=json",
                new EsSqlQuery(query, fetchSize),EsResponseData.class);
        this.columns = esResponseData.getColumns().stream().map(x -> x.get("name")).collect(Collectors.toList());
        this.iterator = convert(columns, esResponseData).iterator();
    }

    private List<Map<String, Object>> convert(List<String> columns, EsResponseData esResponseData) {
        List<Map<String, Object>> results = new ArrayList<>();
        for (List<Object> row : esResponseData.getRows()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < columns.size(); i++) {
                map.put(columns.get(i), row.get(i));
            }
            results.add(map);
        }
        return results;
    }
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Map<String, Object> next() {
        return null;
    }
}
