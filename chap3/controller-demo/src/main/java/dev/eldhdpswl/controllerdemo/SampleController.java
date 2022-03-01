package dev.eldhdpswl.controllerdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("view")
public class SampleController {
    private static final Logger logger
            = LoggerFactory.getLogger(SampleController.class);

    /*
    * 아래함수가 jsp를 찾는 이유는 application.properties에서
      spring.mvc.view.prefix: /WEB-INF/jsp/
      spring.mvc.view.suffix: .jsp
      이 설정을 해줬기 떄문에 가능하다.
    * */
    @GetMapping("/sample-jsp")
    public String sampleJsp(Model model){
        logger.info("in sample jsp");

        //어떠한 변수로서 사용을 할떄는 interface를 최대한 활용해라
        // 구현체를 사용할떄는 아무거나 사용해도 되지만 동일한 interface를 요구하면 구현체가 뭐든지 상관이 없다.
        List<SamplePayload> profiles = new ArrayList<>();

        profiles.add(new SamplePayload("Adam", 22, "student"));
        profiles.add(new SamplePayload("Bradleym", 22, "student2"));
        profiles.add(new SamplePayload("Charlie", 22, "student3"));

        model.addAttribute("profiles", profiles);
        return "view-jsp";
    }

    /*
    * thymeleaf를 사용하게 되면 resources에서 templates폴더를 사용하게 된다.
    * 기본적으로 spring boot에서는 기본 템플릿은 thymeleaf이다.
    * */
    @GetMapping("/sample-thyme")
    public ModelAndView sampleThyme(){
        logger.info("in sample thyme");

        ModelAndView modelAndView = new ModelAndView();
        List<SamplePayload> profiles = new ArrayList<>();

        profiles.add(new SamplePayload("Adam", 22, "student"));
        profiles.add(new SamplePayload("Bradleym", 22, "student2"));
        profiles.add(new SamplePayload("Charlie", 22, "student3"));

        modelAndView.addObject("profiles", profiles);
        modelAndView.setViewName("view-thyme");
        return modelAndView;
    }


    /*
    @GetMapping(
            value = "hello"
    )  //어떠한 경로에 어떤 함수가 들어갈지 결정
    public String hello(
            @RequestParam(name = "id", required = false, defaultValue = "") String id
    ){ //URL에서 쿼리 내용을 가져오는데 사용한다.
        logger.info("Path : hello ");
        logger.info("Query Param id : "+ id);
        return "/hello.html"; // html를 안하면 url의 value로 hello로 보내기떄문에 에러난다. /를 넣는거는 상대경로 확인
    }


    @GetMapping(
            value = "hello/{id}"
    ) //spring에서는 특정한 맵핑을 권장한다.(GetMapping이나 PostMapping 등등)
    //view resolver가 어떤 view를 보여줄지 결정한다.
    public String helloPath(@PathVariable("id") String id){
        logger.info("Path Variable is: "+ id);
        return "/hello.html";
    }


    @GetMapping(
            "/get-progile"
    )
    //데이터가 HTTP 요청 응답에 body에 작성이 된다. 그래서 @ResponseBody를 작성한다.
    //@ResponseBody를 작성을 하면 view를 찾는 것이 아니라, 이 자체로 데이터를 바디로서 사용한다.
    public @ResponseBody  SamplePayload getProfile(){
        return new SamplePayload("eldhdpswl", 10, "Developer");
    }

    */




}
