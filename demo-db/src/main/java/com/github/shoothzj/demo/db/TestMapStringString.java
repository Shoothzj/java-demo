package com.github.shoothzj.demo.db;

import com.github.shoothzj.demo.db.annotation.Table;

import java.util.Map;

/**
 * @author hezhangjian
 */
@Table(name = "test_map_string_string")
public class TestMapStringString {

    private Map<String, String> mapStringStringField;

}
