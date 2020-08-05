package com.github.shoothzj.demo.db;

import com.github.shoothzj.demo.db.annotation.Table;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author hezhangjian
 */
@Table(name = "test_list_list_string")
public class TestListListString {

    private List<List<String>> listListStringField;

}
