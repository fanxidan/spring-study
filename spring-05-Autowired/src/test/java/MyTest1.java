import com.kuang.pojo.People;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest1 {
    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans2.xml");
        People people = applicationContext.getBean("people", People.class);
        people.getCat().shout();
        people.getDog().shout();
        System.out.println(people);
    }
}
