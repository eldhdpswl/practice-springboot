package dev.eldhdpswl.jpa.config;

import com.google.gson.Gson;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;


@Configuration //자신이 bean을 제공하고 여러가지 설정을 담기위한 클래스
public class DemoConfig {
/*


    private static final Logger logger = LoggerFactory.getLogger(DemoConfig.class);

    // application.yml에서 custom.property.single 항목을 찾아서 customProperty 변수에 들어가게 된다.
    @Value("${custom.property.single}")
    private String customProperty;

    @Value("${custom.property.comlist}")
    private List<String> customCommaList;

    @Value("${custom.property.default:default-property}")
    private String propertyDefault;


    // @Value에 들어가는 값들은 기본적으로 bean이 생성되고 나서 들어가기 때문에 constructor에서 log를 찍어서 보는 거는 힘들다.
    // 그래서 @PostConstruct 를 사용해서 확인한다.
    @PostConstruct
    public void init(){
        //logger는 {} 안에 변수값이 들어간다. String Format이 적용되었다.
        logger.info("custom property: {}", customProperty);

        for(String commaListItem: customCommaList){
            logger.info("comma list item: {}", commaListItem);
        }

        logger.info("default property: {}", propertyDefault);
    }

    @Bean
    public Gson gson(){
        // 이 함수의 결과 Gson의 반환값이 스프링IoC 컨테이너에 관리하에 들어가게된다.
        // 즉 Gson 클래스를 IoC 컨테이너에 넘겨준거다. 그렇게 되면 Controller에서 @Autowired가 가능해진다.
        return new Gson();
    }


*/
}
