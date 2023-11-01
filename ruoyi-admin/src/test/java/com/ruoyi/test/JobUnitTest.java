package com.ruoyi.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import static cn.hutool.core.convert.Convert.toInt;
import static cn.hutool.core.convert.Convert.toLong;

/**
 * @author wangzhen  2023/10/31 16:29
 */
@SpringBootTest
@Slf4j
public class JobUnitTest {
    /**
     * 10或13位时间戳转为时间格式
     */
    @Test
    public void testDateFormat(){
        String str_num= "1698742200000";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (str_num.length() == 13) {
            String date = sdf.format(new Date(toLong(str_num)));
            log.info("将13位时间戳:" + str_num + "转化为字符串:"+date);
        } else {
            String date = sdf.format(new Date(toInt(str_num) * 1000L));
            log.info("将10位时间戳:" + str_num + "转化为字符串:"+date);
        }
    }
}
