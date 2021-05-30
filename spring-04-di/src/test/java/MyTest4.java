import com.kuang.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest4 {
    @Test
    public void test(){
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans4.xml");
        //单例模式：true
        User user_singleton1 = classPathXmlApplicationContext.getBean("user_singleton",User.class);
        User user_singleton2 = classPathXmlApplicationContext.getBean("user_singleton",User.class);
        System.out.println(user_singleton1 == user_singleton2);

        //原型模式：false
        User user_prototype1 = classPathXmlApplicationContext.getBean("user_prototype",User.class);
        User user_prototype2 = classPathXmlApplicationContext.getBean("user_prototype",User.class);
        System.out.println(user_prototype1 == user_prototype2);
    }
}
