package com.example.book.iterator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author wangjl
 * @date 2024/8/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EsResponseData {
    //所有的字段
    private List<Map<String,String>> columns;

    //返回的数据值
    private List<List<Object>> rows;
    //用于分页的cursor值
    private String cursor;
}
