package jp.co.sunseer;

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
//import org.testng.annotations.Test;

/**
 * @author
 * @since 2018/12/13
 */
public class CodeGenerator {

    @Test
    public void run() {

        // 1、CodeGeneratorを生成
        AutoGenerator mpg = new AutoGenerator();

        // 2、グローバル配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir("C:\\Users\\2018SUNSEERL3\\Desktop\\デスクトップ\\勉強会\\python\\SpringBoot\\项目源码\\day02\\guli_parent\\service\\service_edu" + "/src/main/java");
        gc.setOutputDir(projectPath + "/src/main/java");

        gc.setAuthor("GroupA");
        gc.setOpen(false); //生成した後で、フォルダを開く
        gc.setFileOverride(false); //生成する際に、古いデータを削除するか

        //UserServie
        gc.setServiceName("%sService");	//UserServie先頭のIを付けない

        gc.setIdType(IdType.ID_WORKER_STR); //主キー設定
        gc.setDateType(DateType.ONLY_DATE);//日付設置
        gc.setSwagger2(true);//Swaggerモード

        mpg.setGlobalConfig(gc);

        // 3、データソースの配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Tokyo");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、パケージ配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("sunseerhht"); //モジュール名

        //パケージ  com.atguigu.eduservice
        pc.setParent("jp.co.sunseer.sunseerservice");

        //パケージ  com.atguigu.eduservice.controller
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、Strategy配置
        StrategyConfig strategy = new StrategyConfig();

        //テーブル名を設定
        strategy.setInclude("t_hiyarihatto");

        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setTablePrefix(pc.getModuleName() + "_");

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true); // lombok
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);

        // 6、実行
        mpg.execute();
    }
}
