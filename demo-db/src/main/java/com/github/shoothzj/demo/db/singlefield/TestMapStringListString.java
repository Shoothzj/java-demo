package com.github.shoothzj.demo.db.singlefield;

import com.github.shoothzj.demo.db.annotation.Table;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author hezhangjian
 */
@Table(name = "test_map_string_list_string")
public class TestMapStringListString {

    private Map<String, List<String>> mapStringListStringField;

}
