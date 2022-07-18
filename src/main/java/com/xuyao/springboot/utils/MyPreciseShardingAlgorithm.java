package com.xuyao.springboot.utils;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

public class MyPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        for (String tableName : availableTargetNames) {
            if (shardingValue.getValue() == 1) {
                return "article";
            }else{
                return "article_" + shardingValue.getValue() % 2;
            }
        }
        throw new IllegalArgumentException();
    }

}