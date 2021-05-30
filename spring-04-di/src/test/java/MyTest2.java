import com.kuang.pojo.Student;
import com.kuang.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest2 {
    @Test
    public void test1(){
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans2.xml");
        User user = classPathXmlApplicationContext.getBean("user",User.class);
        System.out.println(user);
    }
    //实际上spring默认通过bean创建对象是单例的
    @Test
    public void test2(){
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans2.xml");
        User user1 = classPathXmlApplicationContext.getBean("user",User.class);
        User user2 = classPathXmlApplicationContext.getBean("user",User.class);
        //true
        System.out.println(user1==user2);
    }
}
