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







