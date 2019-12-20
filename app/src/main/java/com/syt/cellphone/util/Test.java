package com.syt.cellphone.util;

import com.syt.cellphone.pojo.Soc;

/**
 * @author：syt Date: 2019-12-17
 * 作用: 测试java
 */
public class Test {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Soc soc = new Soc();
            soc.setSocName("syt " + i);
            System.out.println("main = " + soc.hashCode());
            hello(soc);
        }
    }

    static void hello(final Soc name) {
        System.out.println("hi = " + name.hashCode());
    }
}
