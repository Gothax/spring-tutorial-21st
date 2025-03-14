package com.ceos21.spring_boot.controller;

import com.ceos21.spring_boot.domain.Test;
import com.ceos21.spring_boot.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping
    public List<Test> findAllTests() {
        return testService.findAllTest();
    }

}
