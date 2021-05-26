import com.kuang.pojo.People2;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest2 {
    @Test
    public void test1(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans2.xml");
        People2 people = classPathXmlApplicationContext.getBean("people2", People2.class);
        people.getCat().shout();
        people.getDog().shout();
    }
}
