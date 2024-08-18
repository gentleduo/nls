# DDL

```sql
create database if not exists nls_db default character set utf8mb4 collate utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `EMPNO` int(4) NOT NULL AUTO_INCREMENT,
  `ENAME` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `JOB` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `MGR` int(4) NULL DEFAULT NULL,
  `HIREDATE` date NULL DEFAULT NULL,
  `SAL` double(7, 2) NULL DEFAULT NULL,
  `COMM` double(7, 2) NULL DEFAULT NULL,
  `DEPTNO` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`EMPNO`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


INSERT INTO `emp` VALUES (7369, 'SMITH', 'CLERK', 7902, '1980-12-17', 800.00, NULL, 20);
INSERT INTO `emp` VALUES (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600.00, 300.00, 30);
INSERT INTO `emp` VALUES (7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', 1250.00, 500.00, 30);
INSERT INTO `emp` VALUES (7566, 'JONES', 'MANAGER', 7839, '1981-04-02', 2975.00, NULL, 20);
INSERT INTO `emp` VALUES (7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', 1250.00, 1400.00, 30);
INSERT INTO `emp` VALUES (7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', 2850.00, NULL, 30);
INSERT INTO `emp` VALUES (7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', 2450.00, NULL, 10);
INSERT INTO `emp` VALUES (7788, 'SCOTT', 'ANALYST', 7566, '1987-04-19', 3000.00, NULL, 20);
INSERT INTO `emp` VALUES (7839, 'KING', 'PRESIDENT', NULL, '1981-11-17', 5000.00, NULL, 10);
INSERT INTO `emp` VALUES (7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', 1500.00, 0.00, 30);
INSERT INTO `emp` VALUES (7876, 'ADAMS', 'CLERK', 7788, '1987-05-23', 1100.00, NULL, 20);
INSERT INTO `emp` VALUES (7900, 'JAMES', 'CLERK', 7698, '1981-12-03', 950.00, NULL, 30);
INSERT INTO `emp` VALUES (7902, 'FORD', 'ANALYST', 7566, '1981-12-03', 3000.00, NULL, 20);
INSERT INTO `emp` VALUES (7934, 'MILLER', 'CLERK', 7782, '1982-01-23', 1300.00, NULL, 10);
```

# 雪花算法

![image](doc\SnowFlake.png)

正常的时间戳=当前时间-基准时间1970年，这里的时间戳可以设置基准时间为任意年，让时间戳变小些

时间范围

单位：毫秒；365 * 24 * 60 * 60 * 1000L表示一年的毫秒数，整个公式表示：69.73年后会出现重复ID，

工作进程数

不同的机器（或者进程）；一般分成2部分，前5位表示数据中心占用的位数，后5为表示机器标识占用的位数；例如：如果只有一个数据中心的话，做多可以支持1024台机器，

序列号：

自增；

整个ID可以理解为：同一毫秒，同一台机器，可以生成2的12次方个不同的ID，所以每毫秒可以生成4096个不重复的ID，如果换算成TPS的话那就是每秒可生成409.6万个不重复的ID

# web

```cmd
 D:\Java-Workspace\nls> npm create vite@5.0.0 web --template vue
Need to install the following packages:
  create-vite@5.0.0
Ok to proceed? (y) y
? Select a framework: » - Use arrow-keys. Return to submit.
>   Vanilla                                                
? Select a framework: » - Use arrow-keys. Return to submit.
    Vanilla                                                
? Select a framework: » - Use arrow-keys. Return to submit.
    Vanilla                                                
? Select a framework: » - Use arrow-keys. Return to submit.
    Vanilla                                                
? Select a framework: » - Use arrow-keys. Return to submit.
    Vanilla                                                
? Select a framework: » - Use arrow-keys. Return to submit.
    Vanilla                                                
? Select a framework: » - Use arrow-keys. Return to submit.
    Vanilla                                                
? Select a framework: » - Use arrow-keys. Return to submit.
    Vanilla                                                
? Select a framework: » - Use arrow-keys. Return to submit.
    Vanilla                                                
√ Select a framework: » Vue                              
√ Select a variant: » JavaScript                   
                                                   
Scaffolding project in D:\Java-Workspace\nls\web...

Done. Now run:

  cd web     
  npm install
  npm run dev
  
D:\Java-Workspace\nls\web> npm install --save ant-design-vue@4.1.0

D:\Java-Workspace\nls\web> npm install --save @ant-design/icons-vue

# study版本： 4.2.5
D:\Java-Workspace\nls\web> npm install vue-router

D:\Java-Workspace\nls\web> npm install axios
```

> **npm的package.json，就相当于maven的pom.xml**
>
> package-lock.json：表示锁定小版本
>
> --save：表示下载依赖的同时，更新package.json文件
