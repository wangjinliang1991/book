package com.example.book.dutychain;

import com.example.book.pojo.BusinessLaunch;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangjl
 * @date 2024/8/25
 */
public class CityHandler extends AbstractBusinessHandler{
    @Override
    public List<BusinessLaunch> processHandler(List<BusinessLaunch> launchList, String targetCity, String targetSex, String targetProduct) {
        //如果launchList中没有数据，直接返回
        if (launchList.isEmpty()) {
            return launchList;
        }
        // 按target进行筛选，只保留符合条件的投放信息
        launchList = launchList.stream().filter(launch -> {
            String city = launch.getTargetCity();
            if (StringUtils.isEmpty(city)) {
                return true;
            }
            List<String> cityList = Arrays.asList(city.split(","));
            return cityList.contains(targetCity);
        }).collect(Collectors.toList());
        //如果还有下一个责任链，则继续调用下一个责任链
        if (hasNextHandler()) {
            return nextHandler.processHandler(launchList, targetCity, targetSex, targetProduct);
        }
        return launchList;
    }
}
