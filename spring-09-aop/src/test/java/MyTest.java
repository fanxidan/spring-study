import com.kuang.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext1.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.add();
    }
}