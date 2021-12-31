package com.example.mapper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StopWatch;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author hollly
 * @date 2021/1/5 23:58
 */
public class ReflectTest {

    public static void main(String[] args) throws Throwable {
//        Method method = ReflectionUtils.findMethod(ReflectClass.class, "test1");
//        Method method2 = ReflectionUtils.findMethod(ReflectClass.class, "test2");
//        Method method3 = ReflectionUtils.findMethod(ReflectClass.class, "test3");
//        Method method4 = ReflectionUtils.findMethod(ReflectClass.class, "test4");
//
//        Method method5 = ReflectionUtils.findMethod(ReflectClass.InnerCustomClz.class, "test5");
//        Method method6 = ReflectionUtils.findMethod(ReflectClass.InnerCustomClz.class, "test6");
//        Method method7 = ReflectionUtils.findMethod(ReflectClass.InnerCustomClz.class, "test7");
//        Method method8 = ReflectionUtils.findMethod(ReflectClass.InnerCustomClz.class, "test8");

//        System.out.println(System.getProperty("user.dir"));
//        Path path = Paths.get("E://Save");
//        System.out.println(path);
//        System.out.println(path.getFileName());
//        System.out.println(path.getFileSystem());
//        System.out.println(path.getParent());
//        System.out.println(path.getRoot());
//        Path resolve = path.resolve("abc.txt");
//        System.out.println(resolve);

        // 测试方法句柄
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("methodhandle");
        MethodType type = MethodType.methodType(String.class);
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle test1 = lookup.findSpecial(ReflectClass.class, "test4", type, ReflectClass.class);
        //MethodHandle field = lookup.findGetter(ReflectClass.class, "field", String.class);
        Object invoke = test1.invokeWithArguments(new ReflectClass());
        System.out.println(invoke);
        stopWatch.stop();

        stopWatch.start("innormmal");
        System.out.println(new ReflectClass().test4());
        stopWatch.stop();

        String s = stopWatch.prettyPrint();
        System.out.println(s);


        ClassPathResource classPathResource = new ClassPathResource("");
    }
}
