import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest2 {
    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-dao2.xml");
        UserMapper userMapper = applicationContext.getBean("userMapper", UserMapper.class);
        for (User user: userMapper.selectUser()){
            System.out.println(user);
        }
    }
}
