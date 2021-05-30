import com.kuang.pojo.People5;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest2 {
    @Test
    public void test(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans6.xml");
        People5 people = classPathXmlApplicationContext.getBean("people", People5.class);
        people.getCat().shout();
        people.getDog().shout();
        System.out.println(people);
    }
}
