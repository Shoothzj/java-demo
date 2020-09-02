package com.github.shoothzj.demo.base.test.util;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.shoothzj.demo.base.test.module.ColorEnum;
import com.github.shoothzj.demo.base.test.module.CompanyEnum;
import com.github.shoothzj.demo.base.test.module.DeviceGroupEnum;
import com.github.shoothzj.demo.base.test.module.FloorEnum;
import com.github.shoothzj.demo.base.test.module.ProtocolEnum;
import com.github.shoothzj.demo.base.test.module.TestDeviceDto;
import com.github.shoothzj.demo.base.test.module.TestElevatorSensorDto;
import com.github.shoothzj.demo.base.test.module.TestUserDto;
import com.github.shoothzj.demo.base.test.module.WeatherEnum;
import com.github.shoothzj.javatool.service.JacksonService;
import com.github.shoothzj.javatool.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

/**
 * @author hezhangjian
 */
@Slf4j
public class TestDataUtil {

    public static TestUserDto generateTestUserDto() {
        final TestUserDto testUserDto = new TestUserDto();
        testUserDto.setUserId(UUID.randomUUID().toString());
        testUserDto.setAge(RandomUtil.randomInt(0, 99));
        return testUserDto;
    }

    public static TestElevatorSensorDto generateTestElevatorSensorDto() {
        TestElevatorSensorDto testElevatorSensorDto = new TestElevatorSensorDto();
        testElevatorSensorDto.setDeviceName(UUID.randomUUID().toString());
        testElevatorSensorDto.setRandomBoolean(RandomUtil.randomBoolean());
        testElevatorSensorDto.setRandomFloat(RandomUtil.randomInt(0, 6));
        testElevatorSensorDto.setRandomString(UUID.randomUUID().toString());
        return testElevatorSensorDto;
    }

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
            if (RandomUtil.randomBoolean()) {
                objectNode.put("color", RandomUtil.randomEnum(ColorEnum.class).toString());
            }
            if (RandomUtil.randomBoolean()) {
                objectNode.put("floor", RandomUtil.randomEnum(FloorEnum.class).toString());
            }
            if (RandomUtil.randomBoolean()) {
                objectNode.put("country", RandomUtil.randomEnum(FloorEnum.class).toString());
            }
            if (RandomUtil.randomBoolean()) {
                objectNode.put("protocol", RandomUtil.randomEnum(ProtocolEnum.class).toString());
            }
            if (RandomUtil.randomBoolean()) {
                objectNode.put("company", RandomUtil.randomEnum(CompanyEnum.class).toString());
            }
            if (RandomUtil.randomBoolean()) {
                objectNode.put("weather", RandomUtil.randomEnum(WeatherEnum.class).toString());
            }
            if (RandomUtil.randomBoolean()) {
                objectNode.put("speed", RandomUtil.randomInt(10, 250));
            }
            if (RandomUtil.randomBoolean()) {
                objectNode.put("milage", RandomUtil.randomInt(5, 50000));
            }
            if (RandomUtil.randomBoolean()) {
                objectNode.put("eventTime", System.currentTimeMillis() + RandomUtil.randomInt(-200, 200));
            }
            if (RandomUtil.randomBoolean()) {
                objectNode.put("humidity", RandomUtil.randomInt(-100, 100));
            }
            testDeviceDto.setTags(objectNode);
        }
        return testDeviceDto;
    }

}
