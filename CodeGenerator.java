package com.example.springboot2.codegenerator;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;


/**
 * @Author 李波帆
 * @Version 1.0.2
 * Create: 2023/10/17-14:21
 * Description:
 */
public class CodeGenerator {
    //• 生成器代码（注：下划线"_"标注的地方可以根据需求修改）
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3307/springboot2?useSSL=false&serverTimezone=UTC&characterEncoding=utf-8", "root", "root")
                .globalConfig(builder -> {
                    builder.author("libofan") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\java\\idea_projects\\springboot2\\src\\main\\java"); // 指定输出目录
                            //.outputDir(System.getProperty("user.dir")+"/src/main/java"); // 指定输出目录,使用系统变量user.dir就会直接获取到我们当前项目的路径。
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.springboot2") // 设置父包名（都是在outputDir指向的目录下的子目录）
                            .controller("controller") // 指定控制器包名，默认值controller
                            .service("service") //指定service接口包名，默认值service
                            .serviceImpl("service.impl") //指定service接口实现了包名，默认值service.impl
                            .mapper("mapper") //指定mapper层接口包名，默认值mapper
                            //.moduleName("demo2") // 设置父包模块名,默认值为"" （都是在outputDir指向的目录下的子//目录）
                            //.other("model.dto") // 设置其他包名，默认包名为other
                            .entity("entity") //指定实体包名,默认值为entity
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\java\\idea_projects\\springboot2\\src\\main\\resources\\mapper")); // 设置//mapperXml生成路径

                })
                .strategyConfig(builder -> {
                    //builder.addInclude("admin_auth","admin_user","allocation_ref","auth_role_ref","daily","department","post","project","task","task_allocation","user_department_ref","user_role") // 设置需要生成的表名,支持正则匹配、例如 ^t_.* 所有 t_ 开头的表名
                    builder.addInclude("admin")
                            .addTablePrefix("t_", "c_","tbl_") // 设置过滤表前缀
                            .controllerBuilder()//返回Controller.Builder
                            .enableRestStyle()//开启生成@RestController控制器
                            .serviceBuilder()//返回Service.Builder
                            .formatServiceFileName("%sService")//去掉Service接口前的字母I
                            .superServiceImplClass(ServiceImpl.class)//Service接口实现类继承的父类
                            .superServiceClass(IService.class)//Service接口继承的父接口
                            .mapperBuilder()//返回Mapper.Builder
                            .superClass(BaseMapper.class)//Mapper接口继承的父接口
                            .enableBaseColumnList()//开启baseColumnList
                            .enableBaseResultMap()//开启baseResultMap
                            .enableMapperAnnotation()//开启 @Mapper 注解
                            .entityBuilder()//返回Entity.Builder
                            .addTableFills(
                             new Column("create_time", FieldFill.INSERT),new Column("update_time", FieldFill.INSERT_UPDATE))// 设置自动填充的时间字段
                            .enableLombok()//开启lombok模型
                            .enableTableFieldAnnotation()//开启实体类字段注解
                            .enableRemoveIsPrefix();//开启Boolean类型字段移除is前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是//Velocity引擎模板
                .execute();
    }
}

