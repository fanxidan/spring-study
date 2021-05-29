import com.kuang.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest1 {
    public static void main(String[] args) {
        // 调用无参构造函数
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // Spring创建容器时默认使用无参构造函数
        //getBean中的值为xml中的bean id
        User user1 = context.getBean("user", User.class);
        user1.show();
        //xml中bean id配置了name属性即别名
        User user2 = context.getBean("other_user", User.class);
        user2.show();
        //输出结果：表明只创建了一个bean对象，只调用了一次无参构造函数
//        User2 无参构造被调用
//        name: fanxidan
//        name: fanxidan
    }
}