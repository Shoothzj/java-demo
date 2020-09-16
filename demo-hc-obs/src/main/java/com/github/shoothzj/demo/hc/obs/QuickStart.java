package com.github.shoothzj.demo.hc.obs;

import com.github.shoothzj.demo.base.config.HcSecretConfig;
import com.github.shoothzj.javatool.util.IOUtil;
import com.github.shoothzj.javatool.util.YamlUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author hezhangjian
 */
@Slf4j
public class QuickStart {

    public static void main(String[] args) throws FileNotFoundException {
        final String property = System.getProperty("user.dir");
        log.info(property);
        final File file = new File(property + "/secret/hc.yaml");
        final HcSecretConfig hcSecretConfig = YamlUtil.toObject(IOUtil.read2String(new FileInputStream(file)), HcSecretConfig.class);
        log.info("[{}]", hcSecretConfig);
    }

}
