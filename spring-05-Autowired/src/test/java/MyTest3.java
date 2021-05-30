import com.kuang.pojo.People5;
import com.kuang.pojo.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest3 {
    @Test
    public void test(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans7.xml");
        User user = classPathXmlApplicationContext.getBean("user", User.class);
        System.out.println(user.name);
    }
}
