# Spring

* 简介
  * 2002年，首次推出了Spring的雏形：interface21框架
  * 理念：使现有的技术更加容易使用，本身是一个大杂烩，整合了现有的技术框架
  * SSM：SpringMvc+Spring+Mybatis

* 优点
  * 是一个开源免费的框架
  * 是一个轻量级、非入侵式的框架
  * 控制反转（IOC）、面向切面编程（AOP）
  * 支持事务、对框架整合的支持

* Spring就是一个轻量级的控制反转和面向切面编程的框架
* 组成

![img](C:\Users\王金龙\Desktop\Spring\images\Spring Core.jpg)
<img src=".\images\Spring Core.jpg">

* 扩展

  现代化的java开发：基于spring的开发

  * spring boot
    * 构建一切
    * 快速开发的脚手架
    * 基于spring boot可以快速开发单个微服务
    * 约定大于配置
  * spring cloud
    * 协调一切
    * 基于spring boot实现
  * spring cloud data flow:连接一切

# IOC理论

框架：

* UserDao接口
* UserDaoImpl接口
* UserService业务接口
* UserServiceImpl业务实现类

**思想转变**

* 之前程序是主动创建对象，控制权在程序员上
* 使用set注入，变成被动接收对象
* 本质上程序员不需要管理对象的创建，系统的耦合性降低，可以更加专注在业务的实现上，这就是IOC原型
* 控制反转（Inversion of Control）是一种设计思想，DI(依赖注入)是实现IoC的方法
* Spring容器在初始化时先读取配置文件，根据配置文件或元数据创建与组织对象存入容器，程序使用时再从IoC容器中取出需要的对象
* 控制反转是通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式，在Spring中实现控制反转的是IoC容器，实现方法是依赖注入

## HelloSpring

<img src="C:\Users\王金龙\Desktop\Spring\images\HelloSpring.jpg" alt="image-20210511210455165" style="zoom: 80%;" />

* new project-maven-name: spring-study

* new module-spring-02-hellospring

* new package-com.kuang.pojo-Hello.java

  ```java
  //Hello.java
  package com.kuang.pojo;
  
  public class Hello {
      private String str;
  
      @Override
      public String toString() {
          return "hello{" +
                  "str='" + str + '\'' +
                  '}';
      }
  
      public String getStr() {
          return str;
      }
  
      public void setStr(String str) {
          this.str = str;
      }
  }
  
  ```

* resources-new beans.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd">
      <!--使用spring创建对象，在spring这些都为bean
          bean = 对象  new Hello();
          id = 变量名
          class = new的对象
      -->
      <bean id="hello" class="com.kuang.pojo.Hello">
          <property name="str" value="Spring" />
      </bean>
  
  </beans>
  ```

* test-new MyTest.java

  hello对象是由Spring创建

  hello对象的属性是由Spring容器设置

  ```java
  //MyTest.java
  import com.kuang.pojo.Hello;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest {
      public static void main(String[] args) {
          //获取spring上下文对象
          ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
          // 对象在spring中统一管理，要使用，可以直接取出来
          // 传入的参数是bean的id
          Hello hello = (Hello)context.getBean("hello");
          System.out.println(hello.toString());
      }
  }
  
  ```

* pom.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <parent>
          <artifactId>spring-study</artifactId>
          <groupId>org.example</groupId>
          <version>1.0-SNAPSHOT</version>
      </parent>
      <modelVersion>4.0.0</modelVersion>
  
      <artifactId>spring-02-hellospring</artifactId>
  
      <properties>
          <maven.compiler.source>15</maven.compiler.source>
          <maven.compiler.target>15</maven.compiler.target>
      </properties>
  
  </project>
  ```

**总结：**

* 控制：使用spring后，对象全部由spring来创建
* 反转：程序本身不创建对象，变成被动接收对象
* 依赖注入：利用set方法来进行对象的注入
* 主程序不再需要new对象

## 控制反转入门项目

* new project-maven-spring_study

* new module-spring_01

  <img src="C:\Users\王金龙\Desktop\Spring\images\控制反转入门项目.jpg" alt="image-20210511215625289" style="zoom:67%;" />
  
* new package-com.kuang.dao-UserDao.class

  ```java
  package com.kuang.dao;
  
  public interface UserDao {
      void getUser();
  }
  ```

* new package-com.kuang.dao-UserDaoOracleImpl.class

  ```java
  package com.kuang.dao;
  
  public class UserDaoOracleImpl implements UserDao{
      @Override
      public void getUser() {
          System.out.println("Oracle获取用户的数据");
      }
  }		
  ```

* new package-com.kuang.dao-UserDaoMysqlImpl.class

  ```java
  package com.kuang.dao;
  
  public class UserDaoMysqlImpl implements UserDao{
      @Override
      public void getUser() {
          System.out.println("MySQL获取用户数据");
      }
  }
  ```

* new package-com.kuang.service-UserService.class

  ```java
  package com.kuang.service;
  
  public interface UserService {
      void getUser();
  }
  ```

* new package-com.kuang.service-UserServiceImpl.class

  ```java
  package com.kuang.service;
  import com.kuang.dao.UserDao;
  
  public class UserServiceImpl implements UserService{
      private UserDao userDao;
      // 利用set进行动态实现值的注入
      public void setUserDao(UserDao userDao) {
          this.userDao=userDao;
      }
      @Override
      public void getUser() {
          userDao.getUser();
      }
  }
  ```
  
* MyTest

  ```java
  import com.kuang.service.UserServiceImpl;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest {
      public static void main(String[] args) {
          //获取spring上下文对象
          ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
          UserServiceImpl serviceImpl = (UserServiceImpl)context.getBean("UserServiceImpl");
          serviceImpl.getUser();
      }
  }
  ```
  
* pom.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
  
      <groupId>org.example</groupId>
      <artifactId>spring-study</artifactId>
      <packaging>pom</packaging>
      <version>1.0-SNAPSHOT</version>
      <modules>
          <module>spring-01-IOC1</module>
      </modules>
  
      <properties>
          <maven.compiler.source>15</maven.compiler.source>
          <maven.compiler.target>15</maven.compiler.target>
      </properties>
  
      <dependencies>
          <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-webmvc</artifactId>
              <version>5.2.14.RELEASE</version>
          </dependency>
      </dependencies>
  </project>
  ```

* resources-new files-beans.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <bean id="mysqlImpl" class="com.kuang.dao.UserDaoMysqlImpl"/>
      <bean id="oracleImpl" class="com.kuang.dao.UserDaoOracleImpl"/>
  	
      <!-- bean id最好与类名一样，但首字母要小写-->
      <bean id="userServiceImpl" class="com.kuang.service.UserServiceImpl">
          <!-- ref: 引用spring容器中创建好的对象       -->
          <!-- value：具体的值，基本数据类型       -->
          <property name="userDao" ref="oracleImpl"/>
      </bean>
  </beans>
  ```


## IoC创建对象的方式

### 无参构造创建对象

* new module-spring-03-IOC2

* new package-com.kuang.pojo-new class-User.class

  ```java
  package com.kuang.pojo;
  
  public class User {
      private String name;
  
      public void setName(String name) {
          this.name = name;
      }
  
      public User() {
          System.out.println("User2 无参构造被调用");
      }
  
      public void show(){
          System.out.println("name: " + name);
      }
  }
  ```

* resources-new XML configuration file-applicationContext.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd">
  <!--总的配置文件，可以分别管理下面的配置-->
    <import resource="beans1.xml"/>
  <!--  <import resource="beans2.xml"/>-->
  <!--  <import resource="beans3.xml"/>-->
  </beans>
  ```

  * beans1.xml

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
        <!--  id: bean的唯一标识，相当于对象名
           class：bean对象对应的全限定名：包名+类型
           name: 也是别名,可以同时取多个别名-->
        <bean id="user" class="com.kuang.pojo.User" name="other_user,other_user2 other_user3">
            <!--  直接通过参数名创建对象      -->
            <!--  通过property注入必须要有set方法      -->
            <property name="name" value="fanxidan" />
        </bean>
    </beans>
    ```

* new class-MyTest1.class

  ```java
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
  ```

### 有参构造创建对象

#### 使用下标方式赋值

* new package-com.kuang.pojo-new class-User2.class

  ```java
  package com.kuang.pojo;
  
  public class User2 {
      private String name;
      private int age;
  
      public User2(String name, int age) {
          this.name = name;
          this.age = age;
      }
  
      public void show(){
          System.out.println("name: " + name + " age: " + age);
      }
  }
  ```

* resources-new XML configuration file-beans2.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <bean id="user2" class="com.kuang.pojo.User2">
          <!--  使用下标代表传入的参数  -->
          <constructor-arg index="0" value="a"/>
          <constructor-arg index="1" value="1"/>
      </bean>
  </beans>
  ```

* new class-MyTest2.class

  ```java
  import com.kuang.pojo.User2;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest2 {
      public static void main(String[] args) {
          // 调用有参构造函数
          ApplicationContext context = new ClassPathXmlApplicationContext("beans2.xml");
          //getBean中的值为xml中的bean id
          User2 user = context.getBean("user2", User2.class);
          user.show();
      }
  }
  ```

#### 通过参数名传值

* resources-new XML configuration file-beans3.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <!--  id: bean的唯一标识，相当于对象名，不一定和类名一致
         class：bean对象对应的全限定名：包名+类型
         name: 也是别名,可以同时取多个别名-->
      <bean id="user3" class="com.kuang.pojo.User2" name="other_user,other_user2 other_user3">
          <!--  直接通过参数名创建对象      -->
          <constructor-arg name="name" value="b"/>
          <constructor-arg name="age" value="2"/>
      </bean>
  </beans>
  ```

* new class-MyTest2.class

  ```java
  import com.kuang.pojo.User2;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest2 {
      public static void main(String[] args) {
          // 调用有参构造函数
          ApplicationContext context = new ClassPathXmlApplicationContext("beans3.xml");
          //getBean中的值为xml中的bean id
          User2 user = context.getBean("user3", User2.class);
          user.show();
      }
  }
  ```

#### 通过参数类型传值

* resources-new XML configuration file-beans4.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <!--id: bean的唯一标识，相当于对象名
         class：bean对象对应的全限定名：包名+类型
         name: 也是别名,可以同时取多个别名-->
      <bean id="user4" class="com.kuang.pojo.User2" name="other_user,other_user2 other_user3">
  		<!-- 通过参数类型传值,由于是包装类型，所以User类中的int需要改为Integer-->
          <constructor-arg type="java.lang.String" value="c"/>
          <constructor-arg type="java.lang.Integer" value="3"/>
      </bean>
  </beans>
  ```

* new class-MyTest2.class

  ```java
  import com.kuang.pojo.User2;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest2 {
      public static void main(String[] args) {
          // 调用有参构造函数
          ApplicationContext context = new ClassPathXmlApplicationContext("beans4.xml");
          //getBean中的值为xml中的bean id
          User2 user = context.getBean("user4", User2.class);
          user.show();
      }
  }
  ```

小结：

* 当xml配置文件中配置了多个bean的时候，每写一个bean标签，相当于实例化了一个对象，该对象自动调用该类的无参构造函数
* 配置文件加载中，容器已经初始化

# Spring配置

## 别名

使用alias可以给bean id取别名，与上面的`name="other_user,other_user2 other_user3`等效

* resources-new XML configuration file-beans5.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <bean id="user5" class="com.kuang.pojo.User2">
          <!--通过类型和名称传值-->
          <constructor-arg type="java.lang.String" value="d"/>
          <constructor-arg name="age" value="4"/>
      </bean>
      <!--使用alias为bean id取别名-->
      <alias name="user5" alias="alias_usesr"/>
  </beans>
  ```

* new class-MyTest2.class

  ```java
  import com.kuang.pojo.User2;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest2 {
      public static void main(String[] args) {
          // 调用有参构造函数
          ApplicationContext context = new ClassPathXmlApplicationContext("beans5.xml");
          //getBean中的值为xml中的bean id
          User2 user = context.getBean("alias_usesr", User2.class);
          user.show();
      }
  }
  ```

## 导入配置

一般用于团队开发使用，可以将多个配置文件导入合并为一个

* applicationContext.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd">
  <!--总的配置文件，可以分别管理下面的配置-->
    <import resource="beans1.xml"/>
    <import resource="beans2.xml"/>
    <import resource="beans3.xml"/>
    <import resource="beans4.xml"/>
    <import resource="beans5.xml"/>
  </beans>
  ```

* new class-MyTest2.class

  ```java
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
  ```

# 依赖注入

(Dependency Injection)

依赖：bean对象的创建依赖于容器

注入：bean对象中的所有属性都由容器来注入(即通过xml进行配置)

ref：用于引入spring容器已经创建好的对象

```xml
<bean id="Stud" class="com.kuang.pojo.xxx">
    <property name="address" ref="Address"/>
</bean>
```

​		spring根据xml中配置的bean对象中property定义的name去寻找class中的setter方法，因此类中所有数据成员都应该需要有setter方法，即set注入

​		而有参构造对象是通过`constructor-arg`注入，可以没有set方法

## 常用数据类型的注入

* new module-spring-04-di

* new package-com.kuang.pojo-Address.class

  ```java
  package com.kuang.pojo;
  
  public class Address {
      private String address;
  
      @Override
      public String toString() {
          return "Address{" +
                  "address='" + address + '\'' +
                  '}';
      }
  
      public String getAddress() {
          return address;
      }
  
      public void setAddress(String address) {
          this.address = address;
      }
  }
  ```

* new package-com.kuang.pojo-Student.class

  ```java
  package com.kuang.pojo;
  
  import java.util.*;
  
  public class Student {
      private String name;
      private Address address;
      private String[] books;
      private List<String> hobbies;
      private Map<String,String> card;
      private Set<String> games;
      private Properties info;
      private String haswife;
  
      @Override
      public String toString() {
          return "Student{" +
                  "name='" + name + '\'' +
                  "\naddress=" + address +
                  "\nbooks=" + Arrays.toString(books) +
                  "\nhobbies=" + hobbies +
                  "\ncard=" + card +
                  "\ngames=" + games +
                  "\ninfo=" + info +
                  "\nhaswife='" + haswife + '\'' +
                  '}';
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public void setAddress(Address address) {
          this.address = address;
      }
  
      public void setBooks(String[] books) {
          this.books = books;
      }
  
      public void setHobbies(List<String> hobbies) {
          this.hobbies = hobbies;
      }
  
      public void setCard(Map<String, String> card) {
          this.card = card;
      }
  
      public void setGames(Set<String> games) {
          this.games = games;
      }
  
      public void setInfo(Properties info) {
          this.info = info;
      }
  
      public void setHaswife(String haswife) {
          this.haswife = haswife;
      }
  }
  ```

* resources-new XML configuration file-beans1.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd">
      
      <bean id="address" class="com.kuang.pojo.Address">
          <property name="address" value="erhuan roads"></property>
      </bean>
  
      <bean id="student" class="com.kuang.pojo.Student">
          <!--    普通值注入-->
          <property name="name" value="fanxd"/>
          <!--    bean注入-->
          <property name="address" ref="address"/>
          <!--    数组注入-->
          <property name="books">
              <array>
                  <value>红楼梦</value>
                  <value>西游记</value>
                  <value>水浒传</value>
                  <value>三国演义</value>
              </array>
          </property>
          <!--    list注入-->
          <property name="hobbies">
              <list>
                  <value>listen song</value>
                  <value>watch tv</value>
                  <value>ride bikes</value>
              </list>
          </property>
          <!--    map注入-->
          <property name="card">
              <map>
                  <entry key="身份证" value="123456"/>
                  <entry key="银行卡" value="656433"/>
              </map>
          </property>
          <!--    set注入-->
          <property name="games">
              <set>
                  <value>LOL</value>
                  <value>COC</value>
                  <value>BOB</value>
              </set>
          </property>
          <!--    NULL注入-->
          <property name="haswife">
              <null></null>
          </property>
          <!--    Properties注入-->
          <property name="info">
              <props>
                  <prop key="driver">12055627</prop>
                  <prop key="url">www.baidu.com</prop>
                  <prop key="username">小明</prop>
                  <prop key="password">12345</prop>
              </props>
          </property>
      </bean>
  </beans>
  ```

* MyTest1.class

  ```java
  import com.kuang.pojo.Student;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest1 {
      public static void main(String[] args) {
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans1.xml");
          Student student = (Student) applicationContext.getBean("student");
          System.out.println(student);
      }
  }
  ```


## p命名空间注入

必须要有无参构造器

* new package-com.kuang.pojo-User.class

  ```java
  package com.kuang.pojo;
  
  public class User {
      private String name;
      private int age;
  
      public User() {
      }
  
      @Override
      public String toString() {
          return "User{" +
                  "name='" + name + '\'' +
                  ", age=" + age +
                  '}';
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public void setAge(int age) {
          this.age = age;
      }
  }
  ```
  
* resources-new XML configuration file-beans2.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <!--通过p命名空间注入,必须要有无参构造器-->
      <bean id="user" class="com.kuang.pojo.User" p:name="fxd" p:age="19"/>
  </beans>
  ```
  
* MyTest2.class

  ```java
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
  ```

* pom引入junit

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <parent>
          <artifactId>spring-study</artifactId>
          <groupId>org.example</groupId>
          <version>1.0-SNAPSHOT</version>
      </parent>
      <modelVersion>4.0.0</modelVersion>
  
      <artifactId>spring-04-di</artifactId>
      <dependencies>
          <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>RELEASE</version>
              <scope>test</scope>
          </dependency>
          <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-context</artifactId>
              <version>5.2.14.RELEASE</version>
              <scope>test</scope>
          </dependency>
      </dependencies>
  
      <properties>
          <maven.compiler.source>15</maven.compiler.source>
          <maven.compiler.target>15</maven.compiler.target>
      </properties>
  
  </project>
  ```

## c命名空间注入

必须要有有参构造器

* new package-com.kuang.pojo-User.class

  ```java
  package com.kuang.pojo;
  
  public class User {
      private String name;
      private int age;
  
      public User(String name, int age) {
          this.name = name;
          this.age = age;
      }
  
      @Override
      public String toString() {
          return "User{" +
                  "name='" + name + '\'' +
                  ", age=" + age +
                  '}';
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public void setAge(int age) {
          this.age = age;
      }
  }
  ```

* resources-new XML configuration file-beans3.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:c="http://www.springframework.org/schema/c"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <!--通过c命名空间注入，必须要有有参构造器-->
      <bean id="user1" class="com.kuang.pojo.User" c:name="a" c:age="1"/>
      <!--通过c命名空间注入，c:_0等效于c:name c:_1等效于c:age-->
      <bean id="user2" class="com.kuang.pojo.User" c:_0="b" c:_1="2"/>
  </beans>
  ```
  
* MyTest3.class

  ```java
  import com.kuang.pojo.User;
  import org.junit.Test;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest3 {
      @Test
      public void test(){
          ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans3.xml");
          User user = classPathXmlApplicationContext.getBean("user1",User.class);
          System.out.println(user);
          user = classPathXmlApplicationContext.getBean("user2",User.class);
          System.out.println(user);
      }
  }
  ```

## bean的作用域

* 单例模式
  * 全局共享一个对象，默认为单例
* 原型模式
  * 每次从容器中get的时候，都会产生一个新的对象
* 其他模式
  * request、session、application都只能在web中才会使用到

------

* resources-new XML configuration file-beans4.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:c="http://www.springframework.org/schema/c"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <!--Spring默认创建对象是单例模式-->
      <bean id="user_singleton" class="com.kuang.pojo.User" c:age="20" c:_0="fxd2" scope="singleton"/>
      <!--原型模式-->
      <bean id="user_prototype" class="com.kuang.pojo.User" c:age="20" c:_0="fxd2" scope="prototype"/>
  </beans>
  ```

* MyTest4.class

  ```java
  import com.kuang.pojo.User;
  import org.junit.Test;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest4 {
      @Test
      public void test(){
          ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans4.xml");
          //单例模式：true
          User user_singleton1 = classPathXmlApplicationContext.getBean("user_singleton",User.class);
          User user_singleton2 = classPathXmlApplicationContext.getBean("user_singleton",User.class);
          System.out.println(user_singleton1 == user_singleton2);
  
          //原型模式：false
          User user_prototype1 = classPathXmlApplicationContext.getBean("user_prototype",User.class);
          User user_prototype2 = classPathXmlApplicationContext.getBean("user_prototype",User.class);
          System.out.println(user_prototype1 == user_prototype2);
      }
  }
  ```

# bean的自动装配

* 自动装配是Spring满足bean依赖的一种方式
* Spring会在上下文自动寻找，自动给bean装配属性
* 三种装配方式
  * xml中显式配置
  * java中显式配置
  * 隐式自动装配bean

## byName

当一个类里面的成员变量为另外一个类类型时，可以通过自动装配的方式装配属性

* new module-spring-05-Autowired

* new package-com.kuang.pojo-Cat.class

  ```java
  package com.kuang.pojo;
  
  public class Cat {
      public void shout(){
          System.out.println("猫叫");
      }
  }
  ```

* new package-com.kuang.pojo-Dog.class

  ```java
  package com.kuang.pojo;
  
  public class Dog {
      public void shout(){
          System.out.println("狗叫");
      }
  }
  ```

* new package-com.kuang.pojo-People.class

  ```java
  package com.kuang.pojo;
  
  public class People {
      private Cat cat;
      private Dog dog;
      private String name;
  
      public void setName(String name) {
          this.name = name;
      }
  
      @Override
      public String toString() {
          return "People{" +
                  "cat=" + cat +
                  ", dog=" + dog +
                  ", name='" + name + '\'' +
                  '}';
      }
  
      public Cat getCat() {
          return cat;
      }
  
      public Dog getDog() {
          return dog;
      }
  
      public void setCat(Cat cat) {
          this.cat = cat;
      }
  
      public void setDog(Dog dog) {
          this.dog = dog;
      }
  }
  ```

* resources-new XML configuration file-beans1.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <bean id="cat" class="com.kuang.pojo.Cat"/>
      <bean id="dog" class="com.kuang.pojo.Dog"/>
      <!--自动在容器上下文中查找，和自己对象set方法后面的值对应的beanid，如setDog找的就是Dog-->
      <bean id="people" class="com.kuang.pojo.People" autowire="byName">
          <property name="name" value="fan"/>
      </bean>
  </beans>
  ```

* MyTest1.class

  ```java
  import com.kuang.pojo.People;
  import org.junit.Test;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest1 {
      @Test
      public void test(){
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans1.xml");
          People people = applicationContext.getBean("people", People.class);
          people.getCat().shout();
          people.getDog().shout();
          System.out.println(people);
      }
  }
  ```

​        xml中根据autowire的值为byName来自动装配相应的类id，bean id的内容必须和set方法名后面的一样，且set方法后面的字段自动小写

## byType

* resources-new XML configuration file-beans2.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <!--必须保证class全局唯一，即只能有一个Cat,一个Dog类对象-->
      <bean id="a" class="com.kuang.pojo.Cat"/>
      <bean id="b" class="com.kuang.pojo.Dog"/>
      <!--自动在容器上下文中查找，和自己对象属性类型相同的bean    -->
      <bean id="people" class="com.kuang.pojo.People" autowire="byType">
          <property name="name" value="fanxidan"/>
      </bean>
  </beans>
  ```

* MyTest1.class

  ```java
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
  ```

byType必须保证全局的类型唯一，否则不知道要装配哪一个id

## 小结

* byName：保证所有bean的id唯一，并且bean的id要和自动注入的属性的set方法的值一致
* byType：保证所有bean的class唯一，并且bean的id要和自动注入的属性的类型一致

## 注解实现自动装配

使用注解须知：

* 导入约束

* 配置注解的支持

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd
                          http://www.springframework.org/schema/context
                          http://www.springframework.org/schema/context/spring-context.xsd">
      <context:annotation-config></context:annotation-config>
  </beans>
  ```

### @Autowired

* 使用Autowired注解用于实现引用类型的赋值

* 可以直接在变量或方法上面使用
* 使用Autowired不再需要set方法，前提是自动装配的属性在IOC容器中存在，且符合名字byName

------

* new package-com.kuang.pojo-People2.class

  ```java
  package com.kuang.pojo;
  
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.beans.factory.annotation.Value;
  
  public class People2 {
  
      @Autowired
      private Cat cat;
      @Autowired
      private Dog dog;
      //使用value注入值，不需要在xml中通过构造方式或set注入
      @Value("fxd")
      private String name;
  
      @Override
      public String toString() {
          return "People{" +
                  "cat=" + cat +
                  ", dog=" + dog +
                  ", name='" + name + '\'' +
                  '}';
      }
  
      public Cat getCat() {
          return cat;
      }
  
      public Dog getDog() {
          return dog;
      }
  }
  ```
  
* resources-new XML configuration file-beans3.xml

  ```xml
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd
                          http://www.springframework.org/schema/context
                          http://www.springframework.org/schema/context/spring-context.xsd">
  
      <!--使用注解实现自动装配-->
      <context:annotation-config></context:annotation-config>
      <bean id="cat2" class="com.kuang.pojo.Cat"/>
      <bean id="dog2" class="com.kuang.pojo.Dog"/>
      <bean id="people" class="com.kuang.pojo.People2"/>
  </beans>
  ```
  
* MyTest2.class

  ```java
  import com.kuang.pojo.People2;
  import org.junit.Test;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest2 {
      @Test
      public void test(){
          ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans3.xml");
          People2 people = classPathXmlApplicationContext.getBean("people", People2.class);
          people.getCat().shout();
          people.getDog().shout();
          System.out.println(people);
      }
  }
  ```

其他参数：

@Autowired(required=true)：当使用@Autowired注解的时候，其实默认就是@Autowired(required=true)，表示注入的时候，该bean必须存在，否则就会注入失败。

@Autowired(required=false)：表示忽略当前要注入的bean，如果有直接注入，没有跳过，不会报错

* new package-com.kuang.pojo-People3.class

  ```java
  package com.kuang.pojo;
  
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.lang.Nullable;
  
  public class People3 {
      //@Autowired(required=false)：表示忽略当前要注入的bean，如果有直接注入，没有跳过，不会报错。
      @Autowired(required = false)
      private Cat cat;
      @Autowired
      private Dog dog;
      private String name;
  
      public People3( String s) {
          this.name = s;
      }
  
      @Override
      public String toString() {
          return "People{" +
                  "cat=" + cat +
                  ", dog=" + dog +
                  ", name='" + name + '\'' +
                  '}';
      }
  
      public Cat getCat() {
          return cat;
      }
  
      public Dog getDog() {
          return dog;
      }
  }
  ```

* resources-new XML configuration file-beans4.xml

  ```xml
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd
                          http://www.springframework.org/schema/context
                          http://www.springframework.org/schema/context/spring-context.xsd">
  
      <!--可以不配置cat的bean-->
      <context:annotation-config></context:annotation-config>
      <bean id="dog2" class="com.kuang.pojo.Dog"/>
      <bean id="people" class="com.kuang.pojo.People3">
          <constructor-arg value="null"/>
      </bean>
  </beans>
  ```

* MyTest2.class

  ```java
  import com.kuang.pojo.People3;
  import org.junit.Test;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest2 {
      @Test
      public void test(){
          ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans4.xml");
          People3 people = classPathXmlApplicationContext.getBean("people", People3.class);
          people.getDog().shout();
          System.out.println(people);
      }
  }
  ```

### @Qualifier

  	指定要使用的对象,当使用@Autowired自动装配的环境比较复杂的时候，可以使用@Qualifier配置，指定唯一的bean对象

* new package-com.kuang.pojo-People4.class

  ```java
  package com.kuang.pojo;
  
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.beans.factory.annotation.Qualifier;
  import org.springframework.beans.factory.annotation.Value;
  
  public class People4 {
      @Autowired
      private Cat cat;
      @Autowired
      @Qualifier(value = "dog2")  //指定要使用的bean id
      private Dog dog;
      @Value("fxd")
      private String name;
  
      @Override
      public String toString() {
          return "People{" +
                  "cat=" + cat +
                  ", dog=" + dog +
                  ", name='" + name + '\'' +
                  '}';
      }
  
      public Cat getCat() {
          return cat;
      }
  
      public Dog getDog() {
          return dog;
      }
  }
  ```

* resources-new XML configuration file-beans5.xml

  ```xml
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd
                          http://www.springframework.org/schema/context
                          http://www.springframework.org/schema/context/spring-context.xsd">
      <context:annotation-config></context:annotation-config>
      <bean id="cat" class="com.kuang.pojo.Cat"/>
      <bean id="dog" class="com.kuang.pojo.Dog"/>
      <bean id="dog2" class="com.kuang.pojo.Dog"/>
      <bean id="people" class="com.kuang.pojo.People4"/>
  </beans>
  ```

* MyTest2.class

  ```java
  import com.kuang.pojo.People4;
  import org.junit.Test;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest2 {
      @Test
      public void test(){
          ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans5.xml");
          People4 people = classPathXmlApplicationContext.getBean("people", People4.class);
          people.getCat().shout();
          people.getDog().shout();
          System.out.println(people);
      }
  }
  ```

### @Resource

使用@Resource也可以实现自动装配，直接在注解上指定要装备的bean id

* new package-com.kuang.pojo-People5.class

  ```java
  package com.kuang.pojo;
  
  import org.springframework.beans.factory.annotation.Value;
  
  import javax.annotation.Resource;
  
  public class People5 {
      @Resource(name = "cat2")
      private Cat cat;
      @Resource(name = "dog2")
      private Dog dog;
      @Value("fxd")
      private String name;
  
      @Override
      public String toString() {
          return "People{" +
                  "cat=" + cat +
                  ", dog=" + dog +
                  ", name='" + name + '\'' +
                  '}';
      }
  
      public Cat getCat() {
          return cat;
      }
  
      public Dog getDog() {
          return dog;
      }
  }
  ```

* resources-new XML configuration file-beans6.xml

  ```xml
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd
                          http://www.springframework.org/schema/context
                          http://www.springframework.org/schema/context/spring-context.xsd">
      
      <context:annotation-config></context:annotation-config>
      <bean id="cat" class="com.kuang.pojo.Cat"/>
      <bean id="cat2" class="com.kuang.pojo.Cat"/>
      <bean id="dog" class="com.kuang.pojo.Dog"/>
      <bean id="dog2" class="com.kuang.pojo.Dog"/>
      <bean id="people" class="com.kuang.pojo.People5"/>
  </beans>
  ```

@Autowired与@Resource的区别：

* 都是用来自动装配，都可以放在属性字段上
* @Autowired通过byType方式实现，必须要求对象存在
* @Resource通过byName方式实现如果找不到名字，则通过byType实现

### @Component

把普通pojo实例化到spring容器中，相当于配置文件中的

* new package-com.kuang.pojo-User.class

  ```java
  package com.kuang.pojo;
  
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.stereotype.Component;
  
  @Component
  public class User {
      @Value("a")
      public String name;
  }
  ```

* resources-new XML configuration file-beans7.xml

  ```xml
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd
                          http://www.springframework.org/schema/context
                          http://www.springframework.org/schema/context/spring-context.xsd">
  
      <context:component-scan base-package="com.kuang.pojo"/>
      <context:annotation-config></context:annotation-config>
  </beans>
  ```
  
* MyTest3.class

  ```java
  import com.kuang.pojo.People5;
  import com.kuang.pojo.User;
  import org.junit.Test;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest3 {
      @Test
      public void test(){
          ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans7.xml");
          User user = classPathXmlApplicationContext.getBean("user", User.class);
          System.out.println(user.name);
      }
  }
  ```

# 使用注解开发

* 在Spring4之后，使用注解开发，必须保证AOP的包导入

  <img src="C:\Users\王金龙\Desktop\Spring\images\AOP包导入.jpg" alt="image-20210519195840931" style="zoom:67%;" />

* 使用注解需要导入context约束，增加对注解的支持

  ```xml
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd
                          http://www.springframework.org/schema/context
                          http://www.springframework.org/schema/context/spring-context.xsd">
      <!--   组件的扫描范围 -->
      <context:component-scan base-package="com.kuang.pojo"/>
      <context:annotation-config></context:annotation-config>
  </beans>
  ```

------

* new module-spring-06-anno

* new package-com.kuang.pojo-User.class

  ```java
  package com.kuang.pojo;
  
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.context.annotation.Scope;
  import org.springframework.stereotype.Component;
  
  //<bean id="user" class="com.kuang.pojo.User"/>
  @Component
  //作用域
  @Scope("singleton")
  public class User {
      @Value("fanxidan")
      public String name;
  }
  ```

* resources-new XML configuration file-applicationContext.xml
  ```xml
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd
                          http://www.springframework.org/schema/context
                          http://www.springframework.org/schema/context/spring-context.xsd">
      <!--   组件的扫描范围 -->
      <context:component-scan base-package="com.kuang"/>
      <context:annotation-config></context:annotation-config>
  
  </beans>
  ```

* MyTest.class

  ```java
  import com.kuang.pojo.User;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest {
      public static void main(String[] args) {
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
          User user = applicationContext.getBean("user", User.class);
          System.out.println(user.name);
      }
  }
  ```

------

* @Component有几个衍生注解，在web开发中 ，按照MVC三层架构分层

  * dao层（@Repository）

    * new package-com.kuang.dao-UserDao.class

    ```java
    package com.kuang.dao;
    
    import org.springframework.stereotype.Repository;
    
    @Repository
    public class UserDao {
    }
    ```

  * service层（@Service）

    * new package-com.kuang.Service-UserService.class

    ```java
    package com.kuang.service;
    
    import org.springframework.stereotype.Service;
    
    @Service
    public class UserService {
    }
    ```

  * controller层（@Controller）

    * new package-com.kuang.Controller-UserController.class

    ```java
    package com.kuang.controller;
    
    import org.springframework.stereotype.Controller;
    
    @Controller
    public class UserController {
    }
    ```

  这四个注解功能是一样的，代表将某个类注册到spring中，装备bean

  ![image-20210519212631873](C:\Users\王金龙\Desktop\Spring\images\三层mvc.jpg)

  xml与注解的区别：

* xml更加万能，适用于任何场合，维护简单方便

* 注解：不是自己的类是用不了，相当复杂

* xml用来管理bean

* 注解只负责属性的注入

# 使用@Configuration方式配置Spring

完全不使用Spring的xml配置，全权交给Java来实现

* new module-spring-07-appconfig

* new package-com.kuang.pojo-User.class

  ```java
  package com.kuang.pojo;
  
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.stereotype.Component;
  
  //类被Spring接管
  @Component
  public class User {
      private String name;
  
      public String getName() {
          return name;
      }
  
      @Value("fxd2")
      public void setName(String name) {
          this.name = name;
      }
  }
  ```
  
* new package--com.kuang.config-config1.class

  ```java
  package com.kuang.config;
  
  import com.kuang.pojo.User;
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.ComponentScan;
  import org.springframework.context.annotation.Configuration;
  
  //被spring托管，注册到容器中，本省歧视是@Component
  //这是配置类，等价于beans.xml
  @Configuration
  @ComponentScan("com.kuang.pojo")
  public class config1 {
      //注册一个bean，相当于之前的bean标签，方法名字==bean标签中的id
      //返回值相当于bean标签中的class属性
      @Bean
      public User getUser(){
          return new User(); // 返回要注入到bean的对象
      }
  }
  ```

* new package--com.kuang.config-config2.class

  ```java
  package com.kuang.config;
  
  import org.springframework.context.annotation.Configuration;
  import org.springframework.context.annotation.Import;
  
  @Configuration
  @Import(config1.class)
  public class config2 {
  //直接使用config1的配置
  }
  ```

* MyTest.class

  ```java
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
  ```

# 代理模式

SpringAOP的底层

<img src="C:\Users\王金龙\Desktop\Spring\images\代理模式.jpg" alt="image-20210522101024325" style="zoom:67%;" />

## 静态代理

角色分析：

* 抽象角色：一般使用接口或抽象类来解决
* 真实角色：被代理的角色
* 代理角色：代理真实角色，代理真实角色后，一般会做一些附属操作
* 客户：访问代理对象的人

代理的步骤：

* 接口：RentInterace.class
* 真实角色：Landlord.class
* 代理角色：Proxy.class
* 客户端访问代理：Client.class

------

房东出租房子，租客通过中介租房子

* new module-spring-08-proxy
* new package-com.kuang.demo1

* new class-RentInterace.class

  ```java
  package com.kuang.demo01;
  
  public interface RentInterface {
      void rent();
  }
  ```

* new class-Landlord.class

  ```java
  package com.kuang.demo01;
  
  public class Landlord implements  RentInterface{
      @Override
      public void rent() {
          System.out.println("房东出租房子");
      }
  }
  ```

* new class-Proxy.class

  ```java
  package com.kuang.demo01;
  
  public class Proxy implements RentInterface{
      private Landlord landlord;
  
      public Proxy(Landlord landlord) {
          this.landlord = landlord;
      }
  
      @Override
      public void rent() {
          seeHouse();
          landlord.rent();
          hetong();
          fare();
      }
  
      public void seeHouse(){
          System.out.println("中介带你看房");
      }
  
      public void fare(){
          System.out.println("收中介费");
      }
  
      public void hetong(){
          System.out.println("签合同");
      }
  }
  ```
  
* new class-Client.class

  ```java
  package com.kuang.demo01;
  
  public class Client {
      public static void main(String[] args) {
          Landlord landlord = new Landlord();
          landlord.rent();
          System.out.println("使用代理：");
          //使用代理的方式,通过中介租房子，会有一些附属操作，如看房，签合同等
          Proxy proxy = new Proxy(landlord);
          proxy.rent();
      }
  }
  ```

代理模式的好处:

* 可以使真实角色的操作更加存粹，不用关注公共的业务
* 公关交给了代理角色，实现了业务的分工
* 公共业务发生扩展时，方便集中管理

缺点：

* 一个真实角色就会产生一个代理角色，开发效率变低

------

不改变原有类的前提下新增功能（增加了log方法）：通过代理方式实现

* new package-com.kuang.demo2

* new interface-UserService.class

  ```java
  package com.kuang.demo02;
  
  public interface UserService {
      void add();
  }
  ```

* UserServiceImpl.class

  ```java
  package com.kuang.demo02;
  
  public class UserServiceImpl implements UserService{
  
      @Override
      public void add() {
          System.out.println("增加了一个用户");
      }
  }
  ```

* UserServiceProxy.class

  ```java
  package com.kuang.demo02;
  
  public class UserServiceProxy implements UserService{
      private UserServiceImpl userService;
  
      public void setUserService(UserServiceImpl userService) {
          this.userService = userService;
      }
  
      @Override
      public void add() {
          log("add");
          userService.add();
      }
      //使用代理类，在不改变原有类的代码情况下，增加了log方法
      public void log(String msg) {
          System.out.println("使用了"+msg+"方法");
      }
  }
  ```

* Client.class

  ```java
  package com.kuang.demo02;
  
  public class Client {
      public static void main(String[] args) {
          UserServiceImpl userService = new UserServiceImpl();
          userService.add();
          // 使用代理的方式
          System.out.println("使用代理的方式");
          UserServiceProxy proxy = new UserServiceProxy();
          proxy.setUserService(userService);
          proxy.add();//不改变原有类，增加了方法
      }
  }
  ```

<img src="C:\Users\王金龙\Desktop\Spring\images\AOP的实现机制.jpg" alt="image-20210522110039735" style="zoom:67%;" />

## 动态代理

* 动态代理和静态代理的角色一样
* 动态代理类是动态生成，不是直接写好的
* 动态代理分为两大类：
  * 通过实现接口的方式 -> JDK动态代理
  * 通过继承类的方式 -> CGLIB动态代理
    * JAVAssist基于Java字节码实现

使用InvocationHandler

* new package-com.kuang.demo3

* new class-RentInterface.class

  ```java
  package com.kuang.demo03;
  
  public interface RentInterface {
      void rent();
  }
  ```

* new class-Landlord.class

  ```java
  package com.kuang.demo03;
  
  public class Landlord implements RentInterface {
      @Override
      public void rent() {
          System.out.println("房东出租房子");
      }
  }
  ```

* new class-ProxyInvocationHandler.class

  ```java
  package com.kuang.demo03;
  
  import java.lang.reflect.InvocationHandler;
  import java.lang.reflect.Method;
  import java.lang.reflect.Proxy;
  
  //用于自动生成代理类
  public class ProxyInvocationHandler implements InvocationHandler {
      //被代理的接口
      private RentInterface rent;
  
      public void setRent(RentInterface rent) {
          this.rent = rent;
      }
  
      //生成得到代理类
      public Object getProxy(){
          //创建动态代理类和实例的，是一个静态方法 
          //参数1：代理类的classloader
          //参数2：被代理的类接口
          //参数3：InvocationHandler
          return Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(), this);
      }
      
      //处理代理实例并返回结果
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          //2 调用ProxyInvocationHandler的seeHouse方法
          seeHouse();
          //动态代理的本质是使用反射机制实现
          //3 调用ProxyInvocationHandler的rent方法
          Object invoke = method.invoke(rent, args);
          //4 调用ProxyInvocationHandler的fare方法
          fare();
          return invoke;
      }
      public void seeHouse(){
          System.out.println("中介带看房子");
      }
      public void fare(){
          System.out.println("收中介费");
      }
  }
  ```

* new class-Client.class

  ```java
  package com.kuang.demo03;
  
  public class Client {
      public static void main(String[] args) {
          Landlord landlord = new Landlord();
          //代理角色
          ProxyInvocationHandler pih = new ProxyInvocationHandler();
          //通过调用程序处理角色来处理要调用的接口对象
          pih.setRent(landlord);
          RentInterface proxy = (RentInterface) pih.getProxy();
          //1 调用ProxyInvocationHandler的invoke方法
          proxy.rent();
      }
  }
  ```

### 动态代理模板

* new package-com.kuang.demo4

* new class-UserServiceImpl.class

  ```java
  package com.kuang.demo04;
  
  public class UserServiceImpl{
      public void add() {
          System.out.println("增加了一个用户");
      }
  }
  ```

* new class-ProxyInvocationHandler.class

  ```java
  package com.kuang.demo04;
  
  import java.lang.reflect.InvocationHandler;
  import java.lang.reflect.Method;
  import java.lang.reflect.Proxy;
  
  //用于自动生成代理类
  public class ProxyInvocationHandler implements InvocationHandler {
      //被代理的接口
      private Object target;
  
      public void setTarget(Object target) {
          this.target = target;
      }
  
      //生成得到代理类
      public Object getProxy(){
          //创建动态代理类和实例的，是一个静态方法 
          //参数1：代理类的classloader
          //参数2：被代理的类接口
          //参数3：InvocationHandler
          return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
      }
      //处理代理实例并返回结果
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          log(method.getName());
          //动态代理的本质是使用反射机制实现
          Object invoke = method.invoke(target, args);
          return invoke;
      }
  	//新增的方法
      public void log(String msg){
          System.out.println("执行了"+msg+"方法");
      }
  }
  ```

* new class-Client.class

  ```java
  package com.kuang.demo04;
  
  public class Client {
      public static void main(String[] args) {
          UserServiceImpl userService = new UserServiceImpl();
          ProxyInvocationHandler pih = new ProxyInvocationHandler();
          pih.setTarget(userService);//设置要代理的对象
          //动态生成代理类
          UserService proxy = (UserService) pih.getProxy();
          //ProxyInvocationHandler的invoke方法
          proxy.add();
      }
  }
  ```

动态代理的好处：

* 可以使真实角色的操作更加存粹，不用关注公共的业务
* 公关交给了代理角色，实现了业务的分工
* 公共业务发生扩展时，方便集中管理
* 一个动态代理类代理的是一个接口，一般对应的是一类业务
* 一个动态代理类可以代理多个类，只要是实现了同一个接口即可

# AOP

​		AOP(Aspect Oriented Programming):面向切面编程，通过预编译的方式和运行期动态代理实现程序功能的统一维护的一种技术，AOP是OOP的延续，利用AOP可以对业务逻辑的各个部分进行隔离，使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高开发效率。

![image-20210524194542968](C:\Users\王金龙\Desktop\Spring\images\AOP.jpg)

## AOP在Spring中的作用

提供声明式事务：允许用户自定义切面

横切关注点：跨越应用程序多个模块的方法或功能。即与业务逻辑无关，但是我们需要关注的部分，就是横切关注点，如日志、安全、缓存、事务等

切面（ASPECT）：横切关注点被模块化的特殊对象，它是一个类

通知（Advice）：切面必须要完成的工作，它是一个方法

目标（Target）：被通知对象

代理（Proxy）：向目标对象应用通知之后创建的对象

切入点（PoingtCut）：切面通知执行的地点的定义

连接点（JointPoint）：与切入点匹配的执行点

SpringAOP中，通过Advice定义横切逻辑，Spring中支持5种类型的Advice

|   通知类型   |        连接点        |                    实现接口                     |
| :----------: | :------------------: | :---------------------------------------------: |
|   前置通知   |        方法前        |   org.springframework.aop.MethodBeforeAdvice    |
|   后置通知   |        方法后        |  org.springframework.aop.AfterReturningAdvice   |
|   环绕通知   |       方法前后       |   org.aopalliance.intercept.MethodInterceptor   |
| 异常抛出通知 |     方法抛出异常     |      org.springframework.aop.ThrowsAdvice       |
|   引介通知   | 类中增加新的方法属性 | org.springframework.aop.IntroductionInterceptor |

## 使用Spring实现AOP

使用AOP织入需要导入依赖包

```xml
<dependencies>
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.6</version>
    </dependency>
</dependencies>
```

### 使用Spring的API接口

* new module-spring-09-aop

* new-package-com.kuang-Service

  * UserService.class

  ```java
  package com.kuang.service;
  
  public interface UserService {
      void add();
      void delete();
      void update();
      void select();
  }
  ```

  * UserServiceImpl.class

  ```java
  package com.kuang.service;
  
  public class UserServiceImpl implements UserService{
      @Override
      public void add() {
          System.out.println("增加了一个用户");
      }
  
      @Override
      public void delete() {
          System.out.println("删除了一个用户");
      }
  
      @Override
      public void update() {
          System.out.println("更改了一个用户");
      }
  
      @Override
      public void select() {
          System.out.println("查询了一个用户");
      }
  }
  ```

* new-package-log

  * BeforeLog.class

  ```java
  package com.kuang.log;
  
  import org.springframework.aop.MethodBeforeAdvice;
  
  import java.lang.reflect.Method;
  
  public class BeforeLog implements MethodBeforeAdvice {
      //method:要执行的目标对象的方法
      //objects:参数
      //o:目标对象
      @Override
      public void before(Method method, Object[] objects, Object o) throws Throwable {
          System.out.println("前置通知: " + o.getClass().getName()+"的"+method.getName()+"被执行了");
      }
  }
  ```

  * AfterLog.class

  ```java
  package com.kuang.log;
  
  import org.springframework.aop.AfterReturningAdvice;
  
  import java.lang.reflect.Method;
  
  public class AfterLog implements AfterReturningAdvice {
      //o:返回值
      @Override
      public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
          System.out.println("后置通知: 执行了" + method.getName() + "返回的结果为：" + o);
      }
  }
  ```

* resources-new XML configuration file-applicationContext1.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:aop="http://www.springframework.org/schema/aop"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd
                          http://www.springframework.org/schema/aop
                          http://www.springframework.org/schema/aop/spring-aop.xsd">
  
      <bean id="userService" class="com.kuang.service.UserServiceImpl"/>
      <bean id="beforeLog" class="com.kuang.log.BeforeLog"/>
      <bean id="afterLog" class="com.kuang.log.AfterLog"/>
  
      <!--方式一：使用原生SpringAPI接口-->
      <!--    配置aop,导入aop的约束-->
      <aop:config>
          <!-- 配置切入点，id为取名字，expression为表达式，execution()表达式主体，第一个*表示返回类型，*表示所有的类型，第二个参数是包名-->
          <aop:pointcut id="pointcut" expression="execution(* com.kuang.service.UserServiceImpl.*(..))"/>
          <!--        执行环绕增加-->
          <aop:advisor advice-ref="beforeLog" pointcut-ref="pointcut"/>
          <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
      </aop:config>
  </beans>
  ```

* MyTest.class

  ```java
  import com.kuang.service.UserService;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest {
      public static void main(String[] args) {
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext1.xml");
          UserService userService = applicationContext.getBean("userService", UserService.class);
          userService.add();
      }
  }
  ```

### 自定义类实现AOP

* new-package-com.kuang.diy

* new class-DiyPointCut.class

  ```java
  package com.kuang.diy;
  //自定义的切入类
  public class DiyPointCut {
      public void before(){
          System.out.println("方法执行前");
      }
      public void after(){
          System.out.println("方法执行后");
      }
  }
  ```

* UserServiceImpl.class

  ```java
  package com.kuang.service;
  
  public class UserServiceImpl implements UserService{
      @Override
      public void add() {
          System.out.println("增加了一个用户");
      }
  }
  ```

* applicationContext2.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:aop="http://www.springframework.org/schema/aop"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd
                          http://www.springframework.org/schema/aop
                          http://www.springframework.org/schema/aop/spring-aop.xsd">
      <!--方式二：自定义类-->
      <bean id="userService" class="com.kuang.service.UserServiceImpl"/>
      <bean id="diy" class="com.kuang.diy.DiyPointCut"/>
      <aop:config>
          <!--        自定义切面，ref要引用的类-->
          <aop:aspect ref="diy">
              <!--    切入点        -->
              <aop:pointcut id="point" expression="execution(* com.kuang.service.UserServiceImpl.*(..))"/>
              <!--            通知-->
              <aop:before method="before" pointcut-ref="point"/>
              <aop:after method="after" pointcut-ref="point"/>
          </aop:aspect>
      </aop:config>
  </beans>
  ```

* MyTest2.class

  ```java
  import com.kuang.service.UserService;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest {
      public static void main(String[] args) {
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext2.xml");
          //动态代理代理的是接口
          UserService userService = applicationContext.getBean("userService", UserService.class);
          userService.add();
      }
  }
  ```

### 使用注解方式实现AOP

* new package-com.kuang.diy

* AnnotationPoingCut.class

  ```java
  package com.kuang.diy;
  
  import org.aspectj.lang.ProceedingJoinPoint;
  import org.aspectj.lang.annotation.After;
  import org.aspectj.lang.annotation.Around;
  import org.aspectj.lang.annotation.Aspect;
  import org.aspectj.lang.annotation.Before;
  
  //使用注解方式实现AOP
  @Aspect
  public class AnnotationPointCut {
      @Before("execution(* com.kuang.service.UserServiceImpl.*(..))")
      public void before(){
          System.out.println("方法执行前");
      }
  
      @After("execution(* com.kuang.service.UserServiceImpl.*(..))")
      public void after(){
          System.out.println("方法执行后");
      }
  
      //在环绕增强中，可以给定一个参数，代表要获取处理的切入点
      @Around("execution(* com.kuang.service.UserServiceImpl.*(..))")
      public void arround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
          System.out.println("环绕前");
          System.out.println(proceedingJoinPoint.getSignature());//获取签名:即切入的类名.方法名
          // 执行add方法前后执行前置通知与后置通知
          Object proceed = proceedingJoinPoint.proceed();//执行方法
          System.out.println("环绕后");
      }
  }
  ```

* applicationContext3.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:aop="http://www.springframework.org/schema/aop"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd
                          http://www.springframework.org/schema/aop
                          http://www.springframework.org/schema/aop/spring-aop.xsd">
      <!--    方式三-->
      <bean id="userService" class="com.kuang.service.UserServiceImpl"/>
      <bean id="annotationPointCut" class="com.kuang.diy.AnnotationPointCut"/>
  <!--    开启注解支持：JDK(默认proxy-target-class="false") CGLIB(proxy-target-class="true")-->
      <aop:aspectj-autoproxy proxy-target-class="false"/>
  </beans>
  ```

* MyTest.class

  ```java
  import com.kuang.service.UserService;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest {
      public static void main(String[] args) {
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext3.xml");
          //动态代理代理的是接口
          UserService userService = applicationContext.getBean("userService", UserService.class);
          userService.add();
      }
  }
  ```


# 整合Mybatis

步骤：

* 导入相关jar包
  * junit
  * mybatis
  * mysql
  * aop
  * mybatis-spring
* 编写配置文件
* 测试

------

* 创建数据库

  ```mysql
  create database mybatis;
  create table Stu(id int,name varchar(20));
  insert into Stu(id,name) values(1,'a');
  insert into Stu(id,name) values(2,'b');
  ```

* new-module-spring-10-mybatis

* new package-com.kuang.pojo

* new class-User.class

  ```java
  package com.kuang.pojo;
  
  //要使用这个对象,必须还要写一些getter和setter方法,可能还要写一个构造器、equals方法、或者hash方法.这些方法很冗长而且没有技术含量,我们叫它样板式代码.
  //lombok的主要作用是通过一些注解，消除样板式代码
  import lombok.Data;
  
  @Data
  public class User {
      private int id;
      private String name;
  }

* new package-com.kuang.mapper

* new interface-UserMapper.class

  ```java
  package com.kuang.mapper;
  
  import com.kuang.pojo.User;
  import java.util.List;
  
  public interface UserMapper {
      List<User> selectUser();
  }
  ```
  
* UserMapper.xml

  ```xml
  <?xml version="1.0" encoding="utf-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.kuang.mapper.UserMapper">
      <select id="selectUser" resultType="user">
          select * from mybatis.stu;
      </select>
  </mapper>
  ```

* new resources-mybatis-config1.xml

  ```xml
  <?xml version="1.0" encoding="utf-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  
  <configuration>
      <typeAliases>
          <package name="com.kuang.pojo"/>
      </typeAliases>
      <environments default="development">
          <environment id="development">
              <transactionManager type="JDBC"></transactionManager>
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                  <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=false&amp;serverTimezone=UTC"/>
                  <property name="username" value="root"/>
                  <property name="password" value="admin"/>
              </dataSource>
          </environment>
      </environments>
      <mappers>
          <mapper class="com.kuang.mapper.UserMapper"/>
      </mappers>
  </configuration>
  ```

* pom.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <parent>
          <artifactId>spring-study</artifactId>
          <groupId>org.example</groupId>
          <version>1.0-SNAPSHOT</version>
      </parent>
      <modelVersion>4.0.0</modelVersion>
  
      <artifactId>spring-10-mybatis</artifactId>
  
      <properties>
          <maven.compiler.source>15</maven.compiler.source>
          <maven.compiler.target>15</maven.compiler.target>
      </properties>
  
      <dependencies>
          <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>4.13.2</version>
              <scope>test</scope>
          </dependency>
          <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <version>8.0.24</version>
          </dependency>
          <dependency>
              <groupId>org.mybatis</groupId>
              <artifactId>mybatis</artifactId>
              <version>3.5.2</version>
          </dependency>
          <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-webmvc</artifactId>
              <version>5.2.14.RELEASE</version>
          </dependency>
          <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-jdbc</artifactId>
              <version>5.3.7</version>
          </dependency>
          <dependency>
              <groupId>org.aspectj</groupId>
              <artifactId>aspectjweaver</artifactId>
              <version>1.9.6</version>
          </dependency>
       <!--    mybatis-spring可将MyBatis整合到spring中 >-->  
          <dependency>
              <groupId>org.mybatis</groupId>
              <artifactId>mybatis-spring</artifactId>
              <version>2.0.2</version>
          </dependency>
          <dependency>
              <groupId>org.projectlombok</groupId>
              <artifactId>lombok</artifactId>
              <version>1.18.16</version>
          </dependency>
      </dependencies>
      <build>
          <resources>
              <resource>
                  <directory>src/main/java</directory>
                  <includes>
                      <include>**/*.xml</include>
                  </includes>
                  <filtering>true</filtering>
              </resource>
          </resources>
      </build>
  </project>
  ```

* test-java-new MyTest.class

  ```java
  import com.kuang.mapper.UserMapper;
  import com.kuang.pojo.User;
  import org.apache.ibatis.io.Resources;
  import org.apache.ibatis.session.SqlSession;
  import org.apache.ibatis.session.SqlSessionFactory;
  import org.apache.ibatis.session.SqlSessionFactoryBuilder;
  import org.junit.Test;
  
  import java.io.IOException;
  import java.io.InputStream;
  import java.util.List;
  
  public class MyTest {
      @Test
      public void test() throws IOException {
          String resources = "mybatis-config1.xml";
          InputStream inputStream = Resources.getResourceAsStream(resources);
          SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
          SqlSession sqlSession = sessionFactory.openSession(true);
          UserMapper mapper = sqlSession.getMapper(UserMapper.class);
          List<User> users = mapper.selectUser();
          for (int i = 0; i < users.size(); i++) {
              System.out.println(users.get(i));
          }
      }
  }
  ```

* IDEA右边database-"+"MySQL连接对应的数据库

## MyBatis-Spring

MyBatis-Spring会帮助您将MyBatis代码无缝整合到Spring中，允许MyBatis参与到Spring事务中，通过创建映射器mapper和SqlSession并注入到bean中

pom.xml引入MyBatis-Spring

```xml
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis-spring</artifactId>
  <version>2.0.6</version>
</dependency>
```

要和 Spring 一起使用 MyBatis，需要在 Spring 应用上下文中定义至少两样东西：

* SqlSessionFactory

  * 在 MyBatis-Spring 中，可使用 `SqlSessionFactoryBean`来创建 `SqlSessionFactory`

    ```xml
        <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
            <property name="dataSource" ref="datasource"/>
    <!--        绑定mybatis配置文件-->
            <property name="configLocation" value="classpath:mybatis-config.xml"/>
            <property name="mapperLocations" value="classpath:com/kuang/mapper/*.xml"/>
        </bean>
    ```

  * `SqlSessionFactory` 需要一个 `DataSource`（数据源）。这可以是任意的 `DataSource`，只需要和配置其它 Spring 数据库连接一样配置它就可以了

    ```xml
    <!--    DataSource:使用Spring的数据源替换Mybatis的配置-->
        <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <!--        使用spring管理数据源-->
            <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
            <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=false&amp;serverTimezone=UTC&amp;characterEncoding=UTF-8"/>
            <property name="username" value="root"/>
            <property name="password" value="admin"/>
        </bean>
    ```

* 至少一个数据映射器类

  * 定义mapper接口(所指定的映射器类**必须**是一个接口，而不是具体的实现类)

    ```java
    public interface UserMapper {
        List<User> selectUser();
    }
    ```

  * `MapperFactoryBean` 将接口加入到 Spring 中

    ```xml
        <bean id="userMapper" class="com.kuang.mapper.UserMapperImpl">
            <property name="sqlSession" ref="sqlSession"/>
        </bean>
    ```

* SqlSessionTemplate

  * 是 MyBatis-Spring 的核心。作为 `SqlSession` 的一个实现，这意味着可以使用它无缝代替你代码中已经在使用的 `SqlSession`。 `SqlSessionTemplate` 是线程安全的，可以被多个 DAO 或映射器所共享使用

  * 当调用 SQL 方法时（包括由 `getMapper()` 方法返回的映射器中的方法），`SqlSessionTemplate` 将会保证使用的 `SqlSession` 与当前 Spring 的事务相关。

  * 使用 `SqlSessionFactory` 作为构造方法的参数来创建 `SqlSessionTemplate` 对象

    ```xml
    <!--  SqlSessionTemplate就是使用的sqlsession  -->
        <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
    <!--        只能用构造器注入，因为没有set方法-->
            <constructor-arg index="0" ref="sqlSessionFactory"/>
        </bean>
    ```
  
* SqlSessionDaoSupport

  * `SqlSessionDaoSupport` 是一个抽象的支持类，用来为你提供 `SqlSession`。调用 `getSqlSession()` 方法你会得到一个 `SqlSessionTemplate`，之后可以用于执行 SQL 方法

    ```xml
    <bean id="userMapper" class="com.kuang.mapper.UserMapperImpl2">
        <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
    </bean>
    ```

方式1：

* new package-com.kuang.pojo

* UserMapper.class

  ```java
  package com.kuang.mapper;
  
  import com.kuang.pojo.User;
  import java.util.List;
  
  public interface UserMapper {
      List<User> selectUser();
  }
  ```

* UserMapperImpl.class

  ```java
  package com.kuang.mapper;
  
  import com.kuang.mapper.UserMapper;
  import com.kuang.pojo.User;
  import org.apache.ibatis.session.SqlSession;
  import org.mybatis.spring.support.SqlSessionDaoSupport;
  
  import java.util.List;
  
  public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {
      @Override
      public List<User> selectUser() {
          SqlSession sqlSession = getSqlSession();
          UserMapper mapper = sqlSession.getMapper(UserMapper.class);
          return mapper.selectUser();
      }
  }
  ```

* UserMapper.xml

  ```xml
  <?xml version="1.0" encoding="utf-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.kuang.mapper.UserMapper">
      <select id="selectUser" resultType="user">
          select * from mybatis.stu;
      </select>
  </mapper>
  ```

* resources-new mybatis-config1.xml

  ```xml
  <?xml version="1.0" encoding="utf-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  
  <configuration>
      <typeAliases>
          <package name="com.kuang.pojo"/>
      </typeAliases>
  </configuration>
  ```

* resources-new spring-dao1.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <!--    DataSource:使用Spring的数据源替换Mybatis的配置-->
      <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
          <!--        使用spring管理数据源-->
          <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
          <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=false&amp;serverTimezone=UTC&amp;characterEncoding=UTF-8"/>
          <property name="username" value="root"/>
          <property name="password" value="admin"/>
      </bean>
  
      <!--    sqlSessionFactory-->
      <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
          <property name="dataSource" ref="datasource"/>
          <!--        绑定mybatis配置文件-->
          <property name="configLocation" value="classpath:mybatis-config1.xml"/>
          <property name="mapperLocations" value="classpath:com/kuang/mapper/*.xml"/>
      </bean>
  </beans>
  ```

* resources-new applicationContext.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <import resource="spring-dao1.xml"/>
      <bean id="userMapper" class="com.kuang.mapper.UserMapperImpl">
          <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
      </bean>
  
  </beans>
  ```

* new MyTest3.class

  ```java
  import com.kuang.mapper.UserMapper;
  import com.kuang.pojo.User;
  import org.junit.Test;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest3 {
      @Test
      public void test() {
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
          UserMapper userMapper = applicationContext.getBean("userMapper", UserMapper.class);
          for (User user: userMapper.selectUser()){
              System.out.println(user);
          }
      }
  }
  ```

------

方式二：

* new package-com.kuang.pojo

* User.class

  ```java
  package com.kuang.pojo;
  
  //要使用这个对象,必须还要写一些getter和setter方法,可能还要写一个构造器、equals方法、或者hash方法.这些方法很冗长而且没有技术含量,我们叫它样板式代码.
  //lombok的主要作用是通过一些注解，消除样板式代码
  import lombok.Data;
  
  @Data
  public class User {
      private int id;
      private String name;
  }
  ```

* new package-com.kuang.mapper

* UserMapper.class

  ```java
  package com.kuang.mapper;
  
  import com.kuang.pojo.User;
  import java.util.List;
  
  public interface UserMapper {
      List<User> selectUser();
  }
  ```

* UserMapperImpl2.class

  ```java
  package com.kuang.mapper;
  
  import com.kuang.pojo.User;
  import org.apache.ibatis.session.SqlSession;
  
  import java.util.List;
  
  public class UserMapperImpl2 implements UserMapper{
      private SqlSession sqlSession;
  
      public List<User> selectUser(){
          UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
          return userMapper.selectUser();
      }
  
      public void setSqlSession(SqlSession sqlSession) {
          this.sqlSession = sqlSession;
      }
  }
  ```

* UserMapper.xml

  ```xml
  <?xml version="1.0" encoding="utf-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.kuang.mapper.UserMapper">
      <select id="selectUser" resultType="user">
          select * from mybatis.stu;
      </select>
  </mapper>
  ```

* resources-new mybatis-config2.xml

  ```xml
  <?xml version="1.0" encoding="utf-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  
  <configuration>
      <typeAliases>
          <package name="com.kuang.pojo"/>
      </typeAliases>
  </configuration>
  ```
  
* resources-new spring-dao2.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
                          http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <!--    DataSource:使用Spring的数据源替换Mybatis的配置-->
      <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
          <!--        使用spring管理数据源-->
          <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
          <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=false&amp;serverTimezone=UTC&amp;characterEncoding=UTF-8"/>
          <property name="username" value="root"/>
          <property name="password" value="admin"/>
      </bean>
  
      <!--    sqlSessionFactory-->
      <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
          <property name="dataSource" ref="datasource"/>
          <!--        绑定mybatis配置文件-->
          <property name="configLocation" value="classpath:mybatis-config2.xml"/>
          <property name="mapperLocations" value="classpath:com/kuang/mapper/*.xml"/>
      </bean>
      <!--  SqlSessionTemplate就是使用的sqlsession  -->
      <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
          <!--        只能用构造器注入，因为没有set方法-->
          <constructor-arg index="0" ref="sqlSessionFactory"/>
      </bean>
  
      <bean id="userMapper" class="com.kuang.mapper.UserMapperImpl2">
          <property name="sqlSession" ref="sqlSession"/>
      </bean>
  </beans>
  ```

* new MyTest2.class

  ```java
  import com.kuang.mapper.UserMapper;
  import com.kuang.pojo.User;
  import org.junit.Test;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest2 {
      @Test
      public void test() {
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-dao2.xml");
          UserMapper userMapper = applicationContext.getBean("userMapper", UserMapper.class);
          for (User user: userMapper.selectUser()){
              System.out.println(user);
          }
      }
  }
  ```


# 声明式事务

## 事务

* 一组业务要么都成功，要么都失败
* 确保完整性和一致性
* ACID原则：
  * 原子性
  * 一致性
  * 隔离性
    * 多个业务操作同一个资源，防止数据被损坏
  * 持久性

------

​		使用 MyBatis-Spring 的其中一个主要原因是它允许 MyBatis 参与到 Spring 的事务管理中。而不是给 MyBatis 创建一个新的专用事务管理器，MyBatis-Spring 借助了 Spring 中的 `DataSourceTransactionManager` 来实现事务管理。

​		一旦配置好了 Spring 的事务管理器，你就可以在 Spring 中按你平时的方式来配置事务。并且支持 `@Transactional` 注解和 AOP 风格的配置。在事务处理期间，一个单独的 `SqlSession` 对象将会被创建和使用。当事务完成时，这个 session 会以合适的方式提交或回滚

## Spring中的事务管理

分为两大类：

* 声明式事务
  * 交由容器管理事务
* 编程式事务
  * 在代码中进行事务的管理

要开启 Spring 的事务处理功能，在 Spring 的配置文件中创建一个 `DataSourceTransactionManager` 对象：

```xml
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
  <constructor-arg ref="dataSource" />
</bean>
```

------

例子：先插入一个数据，之后删除一个数据，删除脚本故意错误，保证事务的一致性：

* new-module-spring11-transaction

* new package-com.kuang.mapper

* UserMapper.class

  ```java
  package com.kuang.mapper;
  
  import com.kuang.pojo.User;
  
  import java.util.List;
  
  public interface UserMapper {
      List<User> selectUser();
      //添加一个用户
      int addUser(User user);
      //删除一个用户
      int deleteUser(int id);
  }
  ```


* UserMapperImpl.class

  ```java
  package com.kuang.mapper;
  
  import com.kuang.pojo.User;
  import org.mybatis.spring.support.SqlSessionDaoSupport;
  
  import java.util.List;
  
  public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper{
      @Override
      public List<User> selectUser() {
          User user = new User(3, "c");
          UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
          mapper.addUser(user);
          mapper.deleteUser(3);
          return getSqlSession().getMapper(UserMapper.class).selectUser();
      }
  
      @Override
      public int addUser(User user) {
          return getSqlSession().getMapper(UserMapper.class).addUser(user);
      }
  
      @Override
      public int deleteUser(int id) {
          return getSqlSession().getMapper(UserMapper.class).deleteUser(id);
      }
  }
  ```

* UserMapper.xml

  ```xml
  <?xml version="1.0" encoding="utf-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.kuang.mapper.UserMapper">
      <select id="selectUser" resultType="user">
          select * from mybatis.stu;
      </select>
      <insert id="addUser" parameterType="user">
          insert into mybatis.stu(id, name) values (#{id},#{name});
      </insert>
      <!--    故意写错-->
      <delete id="deleteUser" parameterType="int">
          deletes from mybatis.stu where id=#{id};
      </delete>
  </mapper>
  ```

* new package-com.kuang.pojo-User.class

  ```java
  package com.kuang.pojo;
  
  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.NoArgsConstructor;
  
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class User {
      private int id;
      private String name;
  }
  ```

* new resources

  * applicationContext.xml

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
                            http://www.springframework.org/schema/beans/spring-beans.xsd">
        <import resource="spring-dao.xml"/>
        <bean id="userMapper" class="com.kuang.mapper.UserMapperImpl">
            <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
        </bean>
    </beans>
    ```

  * mybatis-config.xml

    ```xml
    <?xml version="1.0" encoding="utf-8" ?>
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    
    <configuration>
        <typeAliases>
            <package name="com.kuang.pojo"/>
        </typeAliases>
    </configuration>
    ```

  * spring-dao.xml

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:tx="http://www.springframework.org/schema/tx" xmlns:aop="http://www.springframework.org/schema/aop"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
                            http://www.springframework.org/schema/beans/spring-beans.xsd
                            http://www.springframework.org/schema/tx
                            http://www.springframework.org/schema/beans/spring-tx.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">
    
    <!--    DataSource:使用Spring的数据源替换Mybatis的配置-->
        <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <!--        使用spring管理数据源-->
            <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
            <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=false&amp;serverTimezone=UTC&amp;characterEncoding=UTF-8"/>
            <property name="username" value="root"/>
            <property name="password" value="admin"/>
        </bean>
    
    <!--    sqlSessionFactory-->
        <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
            <property name="dataSource" ref="datasource"/>
    <!--        绑定mybatis配置文件-->
            <property name="configLocation" value="classpath:mybatis-config.xml"/>
            <property name="mapperLocations" value="classpath:com/kuang/mapper/*.xml"/>
        </bean>
    
    <!--    配置声明式事务-->
        <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
            <constructor-arg ref="datasource"/>
        </bean>
    <!--    结合AOP实现事务的织入-->
    <!--    配置事务通知-->
        <tx:advice id="txAdvice" transaction-manager="transactionManager">
    <!--        给哪些方法配置事务-->
    <!--        propagation:事务的传播特性
                    REQUIRED：支持当前事务，如果当前没有事务，就新建一个事务，这是最常见的选择-->
            <tx:attributes>
                <tx:method name="add" propagation="REQUIRED"/>
                <tx:method name="delete" propagation="REQUIRED"/>
                <tx:method name="update" propagation="REQUIRED"/>
                <tx:method name="query" read-only="true"/>
                <tx:method name="*" read-only="true"/>
            </tx:attributes>
        </tx:advice>
    <!--    配置事务切入-->
        <aop:config>
            <aop:pointcut id="txPointCut" expression="execution(* com.kuang.mapper.*.*(..))"/>
            <aop:advisor advice-ref="txAdvice"  pointcut-ref="txPointCut"/>
        </aop:config>
    </beans>
    ```

* MyTest.class

  ```java
  import com.kuang.mapper.UserMapper;
  import com.kuang.pojo.User;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  public class MyTest {
      public static void main(String[] args) {
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
          UserMapper userMapper = applicationContext.getBean("userMapper", UserMapper.class);
          for (User user : userMapper.selectUser()) {
              System.out.println(user);
          }
      }
  }
  ```


























































