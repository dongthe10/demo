package com.example.config;

import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.URL;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author hollly
 * @date 2020/12/20 0:00
 */
public class Test {

    public static void main(String[] args) {

//        CustomBooleanEditor  customBooleanEditor = new CustomBooleanEditor(false);
//        customBooleanEditor.setAsText("true");
//        Object source = customBooleanEditor.getSource();
//        ;
//        System.out.println(customBooleanEditor.getValue());

//        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
//        String format = LocalDateTime.now().format(formatter);
//        System.out.println(format);
//
//        DateFormat dateInstance = DateFormat.getDateInstance(2, Locale.CHINA);
//        String format = dateInstance.format(new Date());
//        System.out.println(format);
//
//
//        String template = "yearbillpre:2020:{0}:{1}";
//        String initUV = MessageFormat.format(template, "2020-12-30", "initUV");
//        System.out.println(initUV);
//
//
//        String pkgPath = "com.example.demo.controller";
//        String name = pkgPath.replace(".", "/");
//        URL file = Thread.currentThread().getContextClassLoader().getResource("");
//        URL resource = Test.class.getResource("/");
//        URL resource1 = Test.class.getResource("");
//        System.out.println(file);
//        System.out.println(resource);
//        System.out.println(resource1);
//
//        int[] nums = {4, 42, 4};
//        Map<Integer, Integer> counter = IntStream.of(nums).boxed().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
//        // 定义小根堆，根据数字频率自小到大排序
//
//        Integer i1 = 100;
//        Integer i2 = 100;
//        Integer i3 = 1000;
//        Integer i4 = 1000;
//        System.out.println(4%2 ==0);
//        System.out.println(1%2 == 1);


//        ResolvableType resolvableType = ResolvableType.forClass(CustomConfig.class);
//        Type type = resolvableType.getType();
//        resolvableType.resolve();
//
//        ResolvableType[] generics = resolvableType.getGenerics();
//        Type type1 = generics[0].getType();
//        System.out.println(type1 instanceof ParameterizedType);
//        System.out.println(type1 instanceof TypeVariable);
//        System.out.println(generics);
//        System.out.println(type);

//        int ringSecond = (int)((System.currentTimeMillis()/1000)%60);
//        System.out.println(ringSecond);
//        System.out.println(System.currentTimeMillis()/1000);

        int a = 1;
        int b = (a++)+1;

    }
}
