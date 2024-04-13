package com.jacky;

// import com.example.HeaderGenerator;
// import com.example.HeaderParser;
// import com.example.TokenParser;
import com.jacky.service.EmpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class AutoConfigurationTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testTokenParser() {
        // applicationContext.getBean(TokenParser.class);
    }

    @Test
    public void testHeaderParser() {
        // applicationContext.getBean(HeaderParser.class);
    }

    @Test
    public void testHeaderGenerator() {
        // applicationContext.getBean(HeaderGenerator.class);
    }

    @Test
    public void testMyBean() {
        applicationContext.getBean(EmpService.class);
    }
}
