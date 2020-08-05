package com.github.shoothzj.demo.db.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author hezhangjian
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    String name();

}
