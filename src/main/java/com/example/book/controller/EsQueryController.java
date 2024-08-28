package com.example.book.controller;

import com.example.book.iterator.EsSqlQuery;
import com.example.book.service.EsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjl
 * @date 2024/8/27
 */
@RestController
public class EsQueryController {
    @Autowired
    private EsQueryService esQueryService;

    @PostMapping("/queryEsBySql")
    public Object queryEsBySql(@RequestBody EsSqlQuery esSqlQuery) {
        return esQueryService.queryEsBySql(esSqlQuery);
    }
}
