package com.example.crowdfunding.test;

import com.example.crowdfunding.bean.po.MemberPO;
import com.example.crowdfunding.bean.vo.ProjectDetailVO;
import com.example.crowdfunding.mapper.MemberPOMapper;
import com.example.crowdfunding.mapper.ProjectPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-27 19:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemberPOMapper memberPOMapper;

    @Autowired
    private ProjectPOMapper projectPOMapper;

    private Logger logger = LoggerFactory.getLogger(MybatisTest.class);

    @Test
    public void testProjectDetail(){
        ProjectDetailVO projectDetailVO = projectPOMapper.selectProjectDetailByProjectId(12);
        System.out.println(projectDetailVO.toString());
    }

    @Test
    public void testMybatis() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String source = "123123";
        String encode = passwordEncoder.encode(source);
        MemberPO memberPO = new MemberPO(null, "jack", encode, " 杰 克 ", "jack@qq.com", 1, 1, "杰克", "123123", 2);
        memberPOMapper.insert(memberPO);
    }

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        logger.debug(connection.toString());
    }

}
