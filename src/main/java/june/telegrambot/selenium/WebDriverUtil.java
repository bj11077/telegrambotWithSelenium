package june.telegrambot.selenium;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 TODO
 무한스크롤 제어기능추가 해야됨
 */


@Slf4j
@Component
public class WebDriverUtil {

    private WebDriver driver;


    @Value("${browser.select}")
    private String driverId;

    @Value("${browser.driver-path}")
    private String driverPath;





//    @PostConstruct
    private void chrome(){
        log.info("Create chromeDriver");
        System.setProperty(driverId,driverPath);

        // 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--lang=ko");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.setCapability("ignoreProtectedModeSettings",true);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    public String useDriver(String message){
        try {
            chrome();
            String value = connectText(message);
            return value;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            quitDriver();
        }
            return "none";
        }

        public void quitDriver() {
            driver.quit();
        }

        // 나중에 빼야되는데 일단 분기태움

        public String connectText(String message){

        if(message.contains("빤스런")){
            driver.get("https://www.wanted.co.kr/search?query=%EB%B0%B1%EC%97%94%EB%93%9C");
            // 대기시간
            driver.manage().timeouts().implicitlyWait(3000,TimeUnit.MILLISECONDS);
//            WebElement element = driver.findElement(By.xpath("/html/head/title"));
//            List<WebElement> el1 = driver.findElements(By.className("tit_section"));
//           WebElement el1 = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/section[1]/div[3]/div[1]/div[1]/div[1]/span"));
//            List<WebElement> el22 = driver.findElements(By.className("job-card-position"));
//            for(WebElement e : el22){
//                System.out.println("get id = " + e.getText());
//            }

            List<WebElement> element = driver.findElements(By.xpath("/html/body/div/div[4]/div[2]/div/div/div[2]/ul"));

            List<WebElement> elements = element.get(0).findElements(By.tagName("li"));

            log.info("search size : {}",elements.size());

            StringBuilder sb = new StringBuilder();

            for (WebElement webElement : elements){
                String position = webElement.findElement(By.className("job-card-position")).getText();
                String company = webElement.findElement(By.className("job-card-company-name")).getText();
                String location = webElement.findElement(By.className("job-card-company-location")).getText();
                sb.append(position+"  "+ company +"  " + location + "\n");
            }
            sb.setLength(sb.length()-1);
            String answer = sb.toString();

            return answer;
            }else{
            return "몰라몰라";
        }
    }


    /*
           driver.get("https://www.youtube.com/c/youtubekorea/videos");
            // 대기시간
            driver.manage().timeouts().implicitlyWait(500,TimeUnit.MILLISECONDS);
            WebElement element = driver.findElement(By.id("label-text"));
            log.info("search label : {}",element.getText());
            return element.getText();

     */


}
