package com.hollly.mockito;

import com.hollly.example.SwaggerMainApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * 测试mockito的注解
 *
 *
 * 结论：
 *
 * {@link MockBean} 和 {@link org.springframework.boot.test.mock.mockito.SpyBean} 能够将mockito生成的对象注入到spring容器中，
 * 并且能够依赖注入到其他bean中。
 *
 * {@link Mock} 和 {@link org.mockito.Spy} 能够获得mockito代理过的对象，是mockito原生的功能
 *
 * {@link InjectMocks} 会创建对象，然后将使用 @Mock 和 @Spy注解修饰 的成员变量注入
 *
 *
 * @author hollly
 * @date 2022/8/27 23:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SwaggerMainApplication.class, TestConfiguration.class} , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringMainTest {

    @MockBean
    private Monney monney;

//    @InjectMocks
//    private Monney monney4;

    @Mock
    private Monney monney2;

//    @Spy
//    private Monney monney3;


    @InjectMocks
//    @Autowired
    private Sku sku;

    @Autowired
    private Sku sku2;

    @Autowired
    private Good good;

    @Test
    public void simpleTestOnce() {

        System.out.println("fff");
    }
}
