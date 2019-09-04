package com.yqh.dubbo;

import com.yqh.dubbo.api.service.HelloDubboService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DubboSampleConsumerApplicationTests {

    @Autowired
    private HelloDubboService helloDubboService;

/*    @Test
    public void contextLoads() {
        helloDubboService.sayHello();
    }*/

}
