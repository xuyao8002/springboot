package com.xuyao.springboot.service;


public interface ITransactionService {

    /**
     * 声明式事务
     */
    void transactionAnnotation();

    /**
     * 编程式事务，TransactionTemplate
     */
    void transactionTemplate();

    /**
     * 编程式事务，TransactionManager
     */
    void transactionManager();

}
