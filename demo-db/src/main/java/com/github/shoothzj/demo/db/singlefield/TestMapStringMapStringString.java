package com.github.shoothzj.demo.db.singlefield;

import com.github.shoothzj.demo.db.annotation.Table;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author hezhangjian
 */
@Table(name = "test_map_string_map_string_string")
public class TestMapStringMapStringString {

    private Map<String, Map<String, String>> mapStringMapStringStringField;

}
