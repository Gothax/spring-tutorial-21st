package com.ceos21.spring_boot.aop.exam;

import com.ceos21.spring_boot.aop.RetryAspect;
import com.ceos21.spring_boot.aop.TraceAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
//@Import(TraceAspect.class)
@Import({RetryAspect.class, TraceAspect.class})
@Slf4j
class ExamServiceTest {

    @Autowired
    ExamService examService;

    @Test
    void test(){
        for (int i = 1; i < 6; i++) {
            log.info("client request: {}", i);
            examService.request("item" + i);
        }
    }

}