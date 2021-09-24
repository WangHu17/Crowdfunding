import com.example.crowdfunding.bean.Admin;
import com.example.crowdfunding.mapper.AdminMapper;
import com.example.crowdfunding.service.api.AdminService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-08-19 17:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class Test {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @org.junit.Test
    public void test1() {
        Admin admin = new Admin(null, "wanghu1", "5517", "王虎1", "wanghu1@qq.com", null);
        adminService.saveAdmin(admin);
    }

    @org.junit.Test
    public void test() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @org.junit.Test
    public void testSaveAdminMulti() {
        for (int i = 0; i < 352; i++) {
            adminMapper.insert(new Admin(null, "loginAcct" + i, "userPswd" + i, "userName" + i, "email" + i + "@qq.com", null));
        }
    }
}
