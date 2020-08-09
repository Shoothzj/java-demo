package com.github.shoothzj.demo.db.singlefield;

import com.github.shoothzj.demo.db.annotation.Table;

import java.util.List;

/**
 * @author hezhangjian
 */
@Table(name = "test_list_string")
public class TestListString {

    private List<String> listStringField;

}
