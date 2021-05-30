import com.kuang.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest3 {
    @Test
    public void test(){
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans3.xml");
        User user = classPathXmlApplicationContext.getBean("user1",User.class);
        System.out.println(user);
        user = classPathXmlApplicationContext.getBean("user2",User.class);
        System.out.println(user);
    }
}
