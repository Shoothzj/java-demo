package com.github.shoothzj.demo.basic;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author hezhangjian
 */
@Slf4j
public class ValidationDemo {

    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
    }

}