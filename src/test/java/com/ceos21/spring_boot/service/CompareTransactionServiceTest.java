package com.ceos21.spring_boot.service;

import com.ceos21.spring_boot.config.DummyTransactionManager;
import org.junit.jupiter.api.Test;


class CompareTransactionServiceTest {

    @Test
    public void testJpaTransactionManager() {
        // "JPA" DummyTransactionManager 주입
        DummyTransactionManager jpaTM = new DummyTransactionManager("JPA");
        CompareTransactionService service = new CompareTransactionService(jpaTM);

        System.out.println("== JPA Transaction Manager Test 시작 ==");
        service.executeTransactional("JPA", () -> System.out.println("Action executed using JPA TM"));
        System.out.println("== JPA Transaction Manager Test 종료 ==");
    }

    @Test
    public void testJdbcTransactionManager() {
        // "JDBC" DummyTransactionManager 주입
        DummyTransactionManager jdbcTM = new DummyTransactionManager("JDBC");
        CompareTransactionService service = new CompareTransactionService(jdbcTM);

        System.out.println("== JDBC Transaction Manager Test 시작 ==");
        service.executeTransactional("JDBC", () -> System.out.println("Action executed using JDBC TM"));
        System.out.println("== JDBC Transaction Manager Test 종료 ==");
    }

}