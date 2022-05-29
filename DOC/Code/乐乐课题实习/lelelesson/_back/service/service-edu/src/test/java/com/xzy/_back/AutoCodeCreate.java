package com.xzy._back;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import org.junit.Test;

public class AutoCodeCreate {
    @Test
    public void test() {
        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");

        // 代码生成的位置
        gc.setOutputDir("Z:/_back/service/service-edu" + "/src/main/java");
        gc.setAuthor("吴斌");
        gc.setOpen(false);  // 生成后是否打开资源管理器
        gc.setFileOverride(false); // 重新生成时文件是否覆盖

        //UserServie
        gc.setServiceName("%sService");    // 去掉 Service 接口的首字母I
        gc.setIdType(IdType.ID_WORKER_STR); // 主键策略
        gc.setDateType(DateType.ONLY_DATE); // 定义生成的实体类中日期类型
        gc.setSwagger2(true); // 开启 Swagger2 模式
        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/lelelesson");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);

        mpg.setDataSource(dsc);

        // 4、包配置

        PackageConfig pc = new PackageConfig();
        pc.setParent("com.xzy._back");
        pc.setController("controller");
        pc.setEntity("model");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置

        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("edu_chapter","edu_course","edu_course_description","edu_video");  // 数据库中对应的表名
        strategy.setNaming(NamingStrategy.underline_to_camel); // 数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); // 生成实体时去掉表前缀
        strategy.setColumnNaming(NamingStrategy.underline_to_camel); // 数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter 链式操作
        strategy.setRestControllerStyle(true); // restful api 风格控制器
        strategy.setControllerMappingHyphenStyle(true); // url 中驼峰转连字符
        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();

    }

}
