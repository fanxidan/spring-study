import com.kuang.config.MyConfig;
import com.kuang.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        //若要使用配置类方式来使用，只能通过AnnotationConfig上下文获取容器，通过配置类的class对象加载
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        User getUser = applicationContext.getBean("getUser", User.class);
        System.out.println(getUser.getName());
    }
}
