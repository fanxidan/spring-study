import com.kuang.pojo.People;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test1(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        People people = classPathXmlApplicationContext.getBean("people", People.class);
        people.getCat().shout();
        people.getDog().shout();
    }
}
