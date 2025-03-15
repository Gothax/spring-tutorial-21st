package com.ceos21.spring_boot.aop.exam;

import com.ceos21.spring_boot.aop.annotation.Retry;
import com.ceos21.spring_boot.aop.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {

    private static int sequence = 0;


    @Trace
    @Retry(value = 4)
    public String save(String itemId) {
        sequence++;
        if (sequence % 5 == 0) {
            throw new IllegalArgumentException("Invalid item id: " + itemId);
        }
        return "ok";
    }

}
