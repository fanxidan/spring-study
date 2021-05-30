import com.kuang.pojo.User2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest2 {
    public static void main(String[] args) {
        // 调用有参构造函数
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //getBean中的值为xml中的bean id
        User2 user = context.getBean("alias_usesr", User2.class);
        user.show();
    }
}
