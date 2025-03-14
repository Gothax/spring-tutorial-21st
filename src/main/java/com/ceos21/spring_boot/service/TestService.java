package com.ceos21.spring_boot.service;

import com.ceos21.spring_boot.domain.Test;
import com.ceos21.spring_boot.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    @Transactional(readOnly = true)
    public List<Test> findAllTest() {
        return testRepository.findAll();
    }
}
