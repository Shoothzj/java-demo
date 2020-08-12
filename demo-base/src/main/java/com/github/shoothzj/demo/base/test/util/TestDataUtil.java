package com.github.shoothzj.demo.base.test.util;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.shoothzj.demo.base.service.JacksonService;
import com.github.shoothzj.demo.base.test.module.ColorEnum;
import com.github.shoothzj.demo.base.test.module.CompanyEnum;
import com.github.shoothzj.demo.base.test.module.DeviceGroupEnum;
import com.github.shoothzj.demo.base.test.module.FloorEnum;
import com.github.shoothzj.demo.base.test.module.ProtocolEnum;
import com.github.shoothzj.demo.base.test.module.TestDeviceDto;
import com.github.shoothzj.demo.base.test.module.WeatherEnum;
import com.github.shoothzj.demo.base.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

/**
 * @author hezhangjian
 */
@Slf4j
public class TestDataUtil {

    /**
     * @return
     */
    public static TestDeviceDto generateTestDeviceDto() {
        TestDeviceDto testDeviceDto = new TestDeviceDto();
        testDeviceDto.setDeviceName(UUID.randomUUID().toString());
        testDeviceDto.setDeviceId(UUID.randomUUID().toString());

        testDeviceDto.setCreateTime(LocalDateTime.now());

        {
            //deviceGroup
            HashSet<DeviceGroupEnum> deviceGroup = new HashSet<>();
            if (RandomUtil.nextInt(10) < DeviceGroupEnum.Bleach.probability) {
                deviceGroup.add(DeviceGroupEnum.Bleach);
            }
            if (RandomUtil.nextInt(10) < DeviceGroupEnum.Fate.probability) {
                deviceGroup.add(DeviceGroupEnum.Fate);
            }
            if (RandomUtil.nextInt(10) < DeviceGroupEnum.Naruto.probability) {
                deviceGroup.add(DeviceGroupEnum.Naruto);
            }
            if (RandomUtil.nextInt(10) < DeviceGroupEnum.OnePiece.probability) {
                deviceGroup.add(DeviceGroupEnum.OnePiece);
            }
            if (RandomUtil.nextInt(10) < DeviceGroupEnum.Rezero.probability) {
                deviceGroup.add(DeviceGroupEnum.Rezero);
            }
            testDeviceDto.setDeviceGroup(deviceGroup);
        }

        {
            ObjectNode objectNode = JacksonService.createObjectNode();
            if (RandomUtil.randomIf()) {
                objectNode.put("color", RandomUtil.randomEnum(ColorEnum.class).toString());
            }
            if (RandomUtil.randomIf()) {
                objectNode.put("floor", RandomUtil.randomEnum(FloorEnum.class).toString());
            }
            if (RandomUtil.randomIf()) {
                objectNode.put("country", RandomUtil.randomEnum(FloorEnum.class).toString());
            }
            if (RandomUtil.randomIf()) {
                objectNode.put("protocol", RandomUtil.randomEnum(ProtocolEnum.class).toString());
            }
            if (RandomUtil.randomIf()) {
                objectNode.put("company", RandomUtil.randomEnum(CompanyEnum.class).toString());
            }
            if (RandomUtil.randomIf()) {
                objectNode.put("weather", RandomUtil.randomEnum(WeatherEnum.class).toString());
            }
            if (RandomUtil.randomIf()) {
                objectNode.put("speed", RandomUtil.randomInt(10, 250));
            }
            if (RandomUtil.randomIf()) {
                objectNode.put("milage", RandomUtil.randomInt(5, 50000));
            }
            if (RandomUtil.randomIf()) {
                objectNode.put("eventTime", System.currentTimeMillis() + RandomUtil.randomInt(-200, 200));
            }
            if (RandomUtil.randomIf()) {
                objectNode.put("humidity", RandomUtil.randomInt(-100, 100));
            }
            testDeviceDto.setTags(objectNode);
        }
        return testDeviceDto;
    }

}
