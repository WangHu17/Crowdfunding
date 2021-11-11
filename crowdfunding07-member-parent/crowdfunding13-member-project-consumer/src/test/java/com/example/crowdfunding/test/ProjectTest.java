package com.example.crowdfunding.test;


import com.example.crowdfunding.util.CrowdUtil;
import com.example.crowdfunding.util.ResultEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-27 19:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectTest {

    @Test
    public void testUploadFileToOss() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("sooyaaa.jpg");
        ResultEntity<String> resultEntity = CrowdUtil.uploadFileToOss("http://oss-cn-beijing.aliyuncs.com", "LTAI5tLxuTbduh1NLNbPgcz3", "kZdPoFiP7ighWqUYOr7qqGbvvMqhqp", inputStream, "wanghu5517", "http://wanghu5517@1966316916778166.onaliyun.com", "sooyaaa.jpg");
        System.out.println(resultEntity);
    }

}
