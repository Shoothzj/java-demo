package com.github.shoothzj.demo.base.test.module;

import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * @author hezhangjian
 */
@Data
public class DeviceFlinkDto {

    private String deviceName;

    private String deviceId;

    private long timestamp;

    private Set<String> deviceGroup;

    private Map<String, String> tags;

    public DeviceFlinkDto() {

    }

}
