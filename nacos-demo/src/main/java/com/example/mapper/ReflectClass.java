package com.example.mapper;

/**
 * @author hollly
 * @date 2021/1/5 23:55
 */
public class ReflectClass {

    private String field;

    private String test1() {
        return "test1";
    }
    String test2() {
        return "test2";
    }
    protected String test3() {
        return "test3";
    }
    public String test4() {
        return "test4";
    }

    public static class InnerCustomClz {
        public String test5() {
            return "test5";
        }
        String test6() {
            return "test5";
        }
        protected String test7() {
            return "test5";
        }
        public String test8() {
            return "test5";
        }
    }
}
