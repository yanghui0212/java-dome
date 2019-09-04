package com.yangqh;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class YangqhProjectApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void before() {
        //获取mockmvc对象实例
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void contextLoads() {
    }

    /**
     * 柯里化。级联表达式
     */
    @Test
    public void curry() {
        Function<Integer, Function<Integer, Integer>> fun = x -> y -> x + y;
        Function<Integer, Integer> f = fun.apply(2);
        System.out.println(f);
        System.out.println(fun.apply(3).apply(4));
        List<String> strs = Lists.newArrayList("a", "b", "c", "1", "2");
        String[] str1 = {"a", "b", "c", "1", "2"};
        List<String> s = Arrays.stream(str1).collect(Collectors.toList());
        Arrays.stream(str1).distinct();
    }

    @Test
    public void redis() {
        Object o = redisTemplate.opsForValue().get("user::1");
        System.out.println(o);
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(status().isOk());
    }

}
