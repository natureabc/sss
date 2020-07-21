package tk.mybatis.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*@RequestMapping("/")
    String home() {
        return "redirect:countries";
    }*/
}
