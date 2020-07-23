package com.github.shoothzj.demo.basic;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hezhangjian
 */
@Slf4j
public class ReflectComplexDemo {

    private static List<Integer> auxList = new ArrayList<Integer>() {
        {
            add(1);
            add(3);
            add(5);
            add(7);
        }
    };

    public static void main(String[] args) throws Throwable {
        Class<?> name = Class.forName("com.github.shoothzj.demo.basic.ReflectComplexDemo");
        Method doSth = name.getMethod("doSth", String.class, String.class, long.class, String.class, String.class,
                long.class, long.class, long.class, long.class, List.class, List.class, List.class);
//        doSth.invoke(null, "1", "2", 3, "4", "5", 6, 7, 8, 9, auxList, auxList, auxList);
        doSth.invoke(null, new Object[] {"1", "2", 3, "4", "5", 6, 7, 8, 9, auxList, auxList, auxList});
    }

    public static void doSth(String sa, String sb, long la, String sc, String sd, long lb,
                             long lc, long ld, long le,
                             List<Long> lista, List<Long> listb, List<Long> listc) {
        System.out.println(sa);
        System.out.println(sb);
        System.out.println(sc);
        System.out.println(sd);
        System.out.println(la);
        System.out.println(lb);
        System.out.println(lc);
        System.out.println(ld);
        System.out.println(le);
        System.out.println(lista);
        System.out.println(listb);
        System.out.println(listc);
    }

}
