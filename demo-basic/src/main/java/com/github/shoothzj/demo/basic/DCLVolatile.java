package com.github.shoothzj.demo.basic;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
public class DCLVolatile {

    /**
     * ByteCode: Access Flag 0x004a
     */
    private static volatile DCLVolatile instance;

    private DCLVolatile() {

    }

    /**
     * ByteCode:
     * 0 new #2 <className>
     * 3 dup
     * 4 invokespecial #3 <>
     * 7 astore_0
     * 8 aload_0
     * 9 areturn
     * @return
     */
    public static DCLVolatile getInstance() {
        DCLVolatile instance = new DCLVolatile();
        return instance;
    }

}
