import com.kuang.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        //获取spring上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 对象在spring中统一管理，要使用，可以直接取出来
        Hello hello = (Hello)context.getBean("hello");
        System.out.println(hello);
    }
}
