package com.github.shoothzj.demo.jaeger;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * @author hezhangjian
 */
@Slf4j
public class TestJaeger {

    public static void main(String[] args) {
        Configurator.setRootLevel(Level.INFO);
        log.info("hello");

        JaegerTracer tracer = Configuration.fromEnv().getTracer();

    }

}
