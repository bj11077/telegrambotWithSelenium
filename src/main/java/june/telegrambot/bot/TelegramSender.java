//package june.telegrambot.bot;
//
//import com.google.gson.Gson;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//
//// 삽질의 흔적  그냥 pollingbot쓰면 되는걸 왜이랬을까
//
////@Service
//@Slf4j
//public class TelegramSender {
//
//    private String token;
//
//    private String chatId= "2";
//
//    public void sendTelegram(String contents){
//
//        log.info("Send Message Start - ",contents);
//
//        String url = "https://api.telegram.org/bot/sendMessage";
//
//        try{
//            TelegramMessage telegramMessage = new TelegramMessage(chatId,contents);
//            String param = new Gson().toJson(telegramMessage);
//
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//
//
//            // post call
//            HttpEntity<String> entity = new HttpEntity<>(param,headers);
//            restTemplate.postForEntity(url,entity,String.class);
//
//
//        }catch (Exception e){
//            log.error("Send Fail telegram",e);
//        }
//
//
//    }
//}
