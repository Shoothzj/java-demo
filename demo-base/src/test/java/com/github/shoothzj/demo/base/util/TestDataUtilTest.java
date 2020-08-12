package com.github.shoothzj.demo.base.util;

import com.github.shoothzj.demo.base.test.module.TestDeviceDto;
import com.github.shoothzj.demo.base.test.util.TestDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author hezhangjian
 */
@Slf4j
public class TestDataUtilTest {

    @Test
    public void testData() {
        TestDeviceDto testDeviceDto = TestDataUtil.generateTestDeviceDto();
        System.out.println(testDeviceDto);
    }

}
