package com.hollly.test;

import com.hollly.example.api.GreetingService;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author hollly
 * @date 2022/7/16 19:26
 */
public class SPITest {


    public static void main(String[] args) {
        ServiceLoader<GreetingService> load = ServiceLoader.load(GreetingService.class);
        Iterator<GreetingService> iterator = load.iterator();

        while (iterator.hasNext()) {
            GreetingService next = iterator.next();
            String say = next.speaking();
            System.out.println(say);
        }

        try {
            Enumeration<URL> resources = ClassLoader.getSystemClassLoader().getResources("META-INF/services");
            resources.nextElement();

            Enumeration<URL> systemResources = ClassLoader.getSystemResources("META-INF/services");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
