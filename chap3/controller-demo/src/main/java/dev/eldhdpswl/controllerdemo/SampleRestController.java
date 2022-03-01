package dev.eldhdpswl.controllerdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;


/*
Controller는 기본적으로 view를 제공을 하거나 데이터를 제공하는 넓은 의미에서의
annotation이다. 그래서 @ResponseBody과 같은 분명하게 표시해야지 원하는 형태로 데이터를 보내거나 받을수 있다.
RestController를 Controller를 대신해서 사용하게 되면
RestController 기본적으로 데이터를 주고 받는데 주력이다. 그래서 @ResponseBody를 안붙여도 붙인거같은 효과가 난다.
*/
@RestController
@RequestMapping("/rest") //prefix mapping 설정이다. 클래스앞에 RequestMapping을 붙여줄수 있다.
public class SampleRestController {

    private static final Logger logger = LoggerFactory.getLogger(SampleRestController.class);

    @GetMapping("/sample-payload") // payload는 일반적으로 HTTP 요청 읃답에 body를 표현하는 말이다.
    public SamplePayload samplePayloadGet(){
        return new SamplePayload("eldhdpswl", 10, "Developer");
    }

    @PostMapping("/sample-payload")
    @ResponseStatus(HttpStatus.NO_CONTENT)  // @ResponseStatus는 정상적으로 처리됬을떄 status가 어떻게 정의가 될지를 annotation으로 정의
    //지금 상황은 body가 없다는 것을 statuscode가 정해주고 있다.
    // 그외에 처리중에 문제가 생기면 예외를 상황에 맞게 발생시켜서 예외에 따른 코드를 작성한다.
    public void samplePayloadPost(@RequestBody SamplePayload samplePayload){
        // @RequestBody이 controller의 @ResponseBody와 같은 역할을 한다.
        // @RequestBody은 보내진 요청의 바디의 내용을 가능하면 SamplePayload 이 객체의 형태에 맞게 변환해서 객체의 변수인자로서, 다시 전달을 한다.
        logger.info(samplePayload.toString());
    }

    @PostMapping(
            value = "/sample-multipart",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sampleMultipart(
            @RequestParam("name") String name,
            @RequestParam("age") Integer age,
            @RequestParam("occupation") String occupation,
            @RequestParam("file") MultipartFile multipartFile){

        logger.info("name : "+ name);
        logger.info("age : "+ age);
        logger.info("occupation : "+ occupation);
        logger.info("file original name : " + multipartFile.getOriginalFilename());
        //multipartFile.getBytes() -> 여기서 getBytes()는 byte[] 와 같다. throws IOException을 꼭해야된다.
    }


    // produces는 MediaType 설정해주기 위한 값이다.
    @GetMapping(
            value = "/sample-image",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    // 가장 중요한것은 image나 영상이든 결과적으로 byte[]를 기본적으로 돌려준다.
    public byte[] sampleImage() throws IOException{
        InputStream inputStream= getClass().getResourceAsStream("/static/img.png");
        // getClass().getResourceAsStream("")는 ()에 들어가는 문자열을 resources 폴더 안에서 찾기 시작한다.
        //inputStream = new FileInputStream((new File(""));

        return inputStream.readAllBytes();
    }


}
