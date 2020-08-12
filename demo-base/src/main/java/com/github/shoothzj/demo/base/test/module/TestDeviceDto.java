package com.github.shoothzj.demo.base.test.module;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author hezhangjian
 */
@Data
public class TestDeviceDto {

    /**
     * 设备名称
     */
    String deviceName;

    /**
     * 设备的唯一ID
     */
    String deviceId;

    /**
     * 设备的创建时间
     */
    LocalDateTime createTime;

    /**
     * 设备的Group列表
     */
    Set<DeviceGroupEnum> deviceGroup;

    /**
     * tag的Map 用户可能会给设备上1~10个tag
     */
    ObjectNode tags;

    public TestDeviceDto() {
    }


}
