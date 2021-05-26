import com.kuang.pojo.Student;
import com.kuang.pojo.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test1(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("userbeans.xml");
        User user = classPathXmlApplicationContext.getBean("user",User.class);
        System.out.println(user);
    }

    @Test
    public void test2(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("userbeans.xml");
        User user = classPathXmlApplicationContext.getBean("user2",User.class);
        User user2 = classPathXmlApplicationContext.getBean("user2",User.class);
        System.out.println(user==user2);
    }
}
