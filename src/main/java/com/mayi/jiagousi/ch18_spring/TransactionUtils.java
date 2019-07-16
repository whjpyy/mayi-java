package com.mayi.jiagousi.ch18_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
public class TransactionUtils {

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * 获取事务
     * @return
     */
    public TransactionStatus begin(){
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(new DefaultTransactionDefinition());
        return transaction;
    }

    /**
     * 提交事务
     * @param transactionStatus
     */
    public void commit(TransactionStatus transactionStatus){
        dataSourceTransactionManager.commit(transactionStatus);
    }

    /**
     * 回滚事务
     * @param transactionStatus
     */
    public void rollback(TransactionStatus transactionStatus){
        dataSourceTransactionManager.rollback(transactionStatus);
    }

}
