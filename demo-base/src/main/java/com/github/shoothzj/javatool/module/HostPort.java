package com.github.shoothzj.javatool.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hezhangjian
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostPort {

    private String host;

    private int port;

}
