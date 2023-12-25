package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass()); // lombok 이 써줌

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);
        log.trace(" trace Log=" + name);// << 이런 식으로 쓰면 쓸모없는 연산이 벌어짐 (info 만 확인하는데 trace 설정해놔도 안에 문자열은 연산이루어짐)

        log.trace("trace Log={}", name);
        log.debug("debug Log={}", name);
        log.info(" info Log={}", name);
        log.warn(" warn Log={}", name);
        log.error("error Log={}", name);


        return "ok";
    }
}
