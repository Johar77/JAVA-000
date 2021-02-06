# 																	Spring和ORM框架

![Spring 核心特性](.\pic\Spring 核心特性.png)

![Spring 核心价值](.\pic\Spring 核心价值.png)



Spring的东西太多，自己总结的没有小马哥好，还是在上面把小马哥总结的脑图贴上。Spring Framework是Spring系列技术的基石，Spring Framework里面最为核心的就是IoC 和 Aop。Spring Framework将XML、注解、配置类的对象自动生成Bean，加载到Map，然后通过注入的方式添加各个Bean引用中。这样Spring Framework就可以管理这些Bean。Aop实际上就是通过Jdk的动态代理或者字节码技术，进行切面增强，用的最多就是数据库事务。

ORM的框架目前主要是Hibernate和MyBatis，Hibernate主要的优点是全自动化的ORM框架，配置好之后，不需要写代码，但是Hibernate转化逻辑比较复杂。相反MyBatis是半自动，需要额外写mapper文件，但是SQL语句是原生，复杂的SQL方便DBA审查。

![Spring](.\pic\Spring.png)