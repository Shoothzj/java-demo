package com.github.shoothzj.demo.sb.kubectl.module;

import lombok.Data;

/**
 * @author hezhangjian
 */
@Data
public class KubectlPodModule {

    private PodType kind;

    private String hostIp;

    private String podIp;

    private String hostname;

}
