import com.kuang.config.config1;
import com.kuang.config.config2;
import com.kuang.pojo.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        //若要使用配置类方式来使用，只能通过AnnotationConfig上下文获取容器，通过配置类的class对象加载
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(config1.class);
        User getUser1 = applicationContext.getBean("getUser", User.class);
        System.out.println(getUser1.getName());

        applicationContext = new AnnotationConfigApplicationContext(config2.class);
        User getUser2 = applicationContext.getBean("getUser", User.class);
        System.out.println(getUser2.getName());
    }
}