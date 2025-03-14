package com.ceos21.spring_boot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompareTransactionService {

    private final PlatformTransactionManager transactionManager;

    @Transactional
    public void executeTransactional(String managerName, Runnable action) {
        TransactionTemplate txTemplate = new TransactionTemplate(transactionManager);
        txTemplate.execute(status -> {
            action.run();
            return null;
        });
    }
}
