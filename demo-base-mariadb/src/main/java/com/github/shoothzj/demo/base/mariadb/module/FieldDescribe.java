package com.github.shoothzj.demo.base.mariadb.module;

import lombok.Data;

/**
 * @author hezhangjian
 */
@Data
public class FieldDescribe {

    private int columnType;

    private String columnTypeName;

    private int columnDisplaySize;

    private String columnLabel;

    public FieldDescribe() {
    }

}
