package com.github.shoothzj.demo.base.module;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author hezhangjian
 */
@Data
public class DeviceDto {

    private String deviceName;

    private String deviceId;

    private LocalDateTime createTime;

    private List<String> deviceGroup;

    private Map<String, String> tagMap;

    public DeviceDto() {
    }
}
