# 1.maven/spring 的 profile 机制，都有什么用法？

profile的机制都是为了实现在不同环境下构建的不同需求。

## 1.1 maven的profile使用用法

- 命令行激活： 

  `mvn clean install -Pdev_env,test_evn`

- Settings文件激活

  `<settings>
    ...
    <activeProfiles>
      <activeProfile>dev_evn</activeProfile>
    </activeProfiles>
    ...
  </settings>`

- 系统属性激活

  `<profiles>
    <profile>
      ...
      <activation>
        <property>
          <name>profileProperty</name>
        </property>
      </activation>
    </profile>
  </profiles>`

## 1.2 spring的profile用法

- properties\yml配置文件中配置 常用

  `spring.profiles.active=prod`

- main方法激活profile

  `--spring.profiles.active=prod`

- jar激活profile

  `java -jar -Dspring.profiles.active=prod *.jar`

- 代码中指定环境变量

  `System.setProperty("spring.profiles.active", "test");`

# 2.总结 Hibernate 与 MyBatis 的各方面异同点  

## 2.1 相同点

- Hibernate和MyBatis都是一种ORM的框架，支持JDBC、JTA事务处理

## 2.2 不同点

- Hibernate 功能强大，数据库无关性好，ORM程度高，如果熟悉Hibernate，可以减少开发工作量。但是Hibernate的学习门槛高
- MyBatis属于半自动化的ORM产品，相对来说简单，上手快。但是功能简单，需要手写sql语句，这个是优点也是缺点，缺点就是增加了工作量，优点就是复杂的业务逻辑，可以自己写sql语句，容易控制。

## 2.3 总结

一般情况，要是业务逻辑不复杂，目前springboot中封装的jpa默认就是使用hibernate，可以减轻代码的开发量。业务复杂的逻辑，可以让在DBA进行sql语句编写、审核比较方便，更适合使用MyBaits







