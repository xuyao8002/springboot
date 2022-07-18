package com.xuyao.springboot.other;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;

public class CodeGeneratorNew {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/xuyao", "root", "root")
                .globalConfig(builder -> {
                    builder.author("xuyao") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.xuyao.springboot") // 设置父包名
                            .moduleName("generator") // 设置父包模块名
                    ; // 设置mapperXml生成路径
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("article") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .execute();
    }

}