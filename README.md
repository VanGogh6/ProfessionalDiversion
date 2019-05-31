---
typora-root-url: image
---

# 学校专业分流系统
学校专业分流系统，采用技术maven+lombok+springBoot+jpa+druid+thymeleaf+bootstrap4。

- 该系统支持管理员从excel导入学生，导出excel，批量删除学生，单个学生删除，给学生修改学院，修改专业，和一键分配专业，日志信息等。
- 学生用户登录可以填写志愿，查看分配志愿信息。

### 登录界面

![TIM截图20190531230958](tree/master/image/TIM截图20190531230958.png)

### 输入账号admin，密码1登录管理界面，输入学生账号进入学界面

- 管理员界面

![TIM截图20190531231423](https://github.com/VanGogh6/kesheweb/edit/master/TIM截图20190531231423.png)

-  学生界面

![TIM截图20190531231718](/TIM截图20190531231718.png)

### 运行方式

- 下载到代码本地，装载到eclipse或idea（我采用idea），记得安装lombok插件，如果不安装则需要你手动补上实体类中所有get,set,无参构造，导入项目data的下的keshe2019.sql，运行项目即可测试
