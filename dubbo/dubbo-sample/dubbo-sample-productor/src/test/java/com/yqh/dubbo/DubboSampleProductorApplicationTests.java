package com.yqh.dubbo;

import com.google.gson.Gson;
import com.yqh.dubbo.common.ExcelUtil;
import com.yqh.dubbo.dto.SimpleExcelDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DubboSampleProductorApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Test
    public void testExcel() throws IOException {

        FileInputStream stream = new FileInputStream(new File(getClass().getClassLoader().getResource("a.xlsx").getFile()));
        ByteArrayInputStream bi = new ByteArrayInputStream(IOUtils.toByteArray(stream));
        List<SimpleExcelDto> dtos = ExcelUtil.buildObjectFromExcel(bi, SimpleExcelDto.class, 0);
        log.info(new Gson().toJson(dtos));
    }
}
