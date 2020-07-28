package com.github.shoothzj.demo.base.util;

import com.github.shoothzj.demo.base.module.OperationSystem;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
public class OSUtil {

    public static OperationSystem getOS() {
        String osName = EnvUtil.getOsName();
        if (osName.startsWith("Mac")) {
            return OperationSystem.MAC;
        } else if (osName.startsWith("Windows")) {
            return OperationSystem.WINDOWS;
        } else if (osName.startsWith("Linux")) {
            return OperationSystem.LINUX;
        }

        return OperationSystem.UNKNOWN;
    }

    public static String getDocumentPath() {
        OperationSystem operationSystem = getOS();
        if (operationSystem.equals(OperationSystem.MAC) || operationSystem.equals(OperationSystem.WINDOWS)) {
            return EnvUtil.getUserHome() + "/Documents";
        } else {
            return EnvUtil.getUserHome() + "/Documents";
        }
    }

}
