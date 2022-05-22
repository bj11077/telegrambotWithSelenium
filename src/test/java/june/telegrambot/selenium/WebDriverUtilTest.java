package june.telegrambot.selenium;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WebDriverUtilTest {

    @Autowired
    WebDriverUtil webDriverUtil;


    @Test
    public void 정상호출여부(){
        System.out.println(webDriverUtil.useDriver(""));
    }

    @Test
    public void 키워드입력시(){
        String answer = webDriverUtil.useDriver("빤스런");
        Assertions.assertThat(answer).hasSizeGreaterThan(4);
    }

    @Test
    public  void 키워드이외입력(){
        String answer = webDriverUtil.useDriver("f");
        assertEquals("몰라몰라",answer);
    }
}