# Community note

## Day1
1. 机器安装多个版本JDK, 需要在File-->Project Structure中设置对应版本的JDK，否则会启动失败
2. springboot 静态文件映射路径包括: /static, /public, /resources, /META-INFO/resource,
要使用tempplates，thyleaf模板需要添加对应的依赖，这样才能映射到templates模板下的html 
3. Use实体指定有参构造函数之后，必须指定无参构造函数，否则mybatis查询的时候会解析avatarUrl字段出错""Cannot determine value type from string 'http://avatar_url"
