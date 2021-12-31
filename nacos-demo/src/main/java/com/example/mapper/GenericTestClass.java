package com.example.mapper;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author hollly
 * @date 2021/2/11 0:32
 */
public class GenericTestClass<T> extends GenericTestClassTwo<Integer, Integer> {

    T fieldOne;

    public static void main(String[] args) {
        GenericTestClass<String> genericTestClass = new GenericTestClass<>();
        Class<? extends GenericTestClass> aClass = genericTestClass.getClass();
        Type[] genericInterfaces = aClass.getGenericInterfaces();
        Type genericSuperclass = aClass.getGenericSuperclass();
        System.out.println(Arrays.toString(genericInterfaces));
        System.out.println(genericSuperclass);
        if (genericSuperclass instanceof  ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            System.out.println(Arrays.toString(actualTypeArguments));

        }
        Class<?> componentType = aClass.getComponentType();
        System.out.println(componentType);
        try {
            Field fieldOne = aClass.getDeclaredField("fieldOne");
            Constructor<? extends GenericTestClass> constructor = aClass.getConstructor();
            Method method = aClass.getMethod("");
            PriorityQueue heap = new PriorityQueue();
        } catch (NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
