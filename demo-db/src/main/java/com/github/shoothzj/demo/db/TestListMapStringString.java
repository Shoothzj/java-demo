package com.github.shoothzj.demo.db;

import com.github.shoothzj.demo.db.annotation.Table;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author hezhangjian
 */
@Table(name = "test_list_map_string_string")
public class TestListMapStringString {

    private List<Map<String, String>> listMapStringStringField;

}
