package com.jacky;

// import com.example.MyImportSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Import;

// @ServletComponentScan
// @ComponentScan({"com.jacky", "com.example"}) // 这样配置会很繁琐
// @Import({TokenParser.class, HeaderConfig.class})
// @Import(MyImportSelector.class)
@SpringBootApplication
public class TliasSpringStarter {
    public static void main(String[] args) {
        SpringApplication.run(TliasSpringStarter.class, args);
    };
}
