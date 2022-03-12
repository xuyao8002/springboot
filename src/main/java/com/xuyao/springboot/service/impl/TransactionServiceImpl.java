package com.xuyao.springboot.service.impl;


import com.xuyao.springboot.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private DefaultTransactionDefinition defaultTransactionDefinition;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transactionAnnotation() {
        execSql();
    }

    @Override
    public void transactionTemplate() {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                execSql();
            }
        });
    }

    @Override
    public void transactionManager() {
        TransactionStatus transaction = platformTransactionManager.getTransaction(defaultTransactionDefinition);
        try{
            execSql();
            platformTransactionManager.commit(transaction);
        }catch (Exception e){
            platformTransactionManager.rollback(transaction);
        }
    }

    private void execSql(){
        jdbcTemplate.execute("insert into t values (x)");
        String str = null;
        str = str.trim();
        jdbcTemplate.execute("insert into t1 values (x)");
    }

}
