package com.github.shoothzj.demo.base.domain.mongo;

import lombok.Data;

import java.util.Map;

/**
 * 外卖骑手数据
 * @author hezhangjian
 */
@Data
public class RiderDao {

    /**
     * 外送员Id
     */
    private String id;

    /**
     * 外卖员姓名
     */
    private String name;

    /**
     * 外卖员手机号
     */
    private String phoneNum;

    /**
     * 经度
     */
    private double longitude;

    /**
     * 纬度
     */
    private long latitude;

    /**
     * 外卖员标签
     */
    private Map<String, String> tag;

}
