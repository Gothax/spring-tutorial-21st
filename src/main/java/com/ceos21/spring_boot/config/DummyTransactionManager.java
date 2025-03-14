package com.ceos21.spring_boot.config;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public class DummyTransactionManager implements PlatformTransactionManager {

    private final String name;

    public DummyTransactionManager(String name) {
        this.name = name;
    }

    @Override
    public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
        System.out.println("[" + name + "] Transaction started.");
        return new DummyTransactionStatus();
    }

    @Override
    public void commit(TransactionStatus status) throws TransactionException {
        System.out.println("[" + name + "] Transaction committed.");
    }

    @Override
    public void rollback(TransactionStatus status) throws TransactionException {
        System.out.println("[" + name + "] Transaction rolled back.");
    }

    private static class DummyTransactionStatus implements TransactionStatus {
        @Override public boolean isNewTransaction() { return true; }
        @Override public boolean hasSavepoint() { return false; }
        @Override public void setRollbackOnly() {}
        @Override public boolean isRollbackOnly() { return false; }
        @Override public void flush() {}
        @Override public boolean isCompleted() { return false; }

        @Override
        public Object createSavepoint() throws TransactionException {
            return null;
        }

        @Override
        public void rollbackToSavepoint(Object savepoint) throws TransactionException {

        }

        @Override
        public void releaseSavepoint(Object savepoint) throws TransactionException {

        }
    }
}
