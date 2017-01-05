package com.inobil.fraudwatcher.loaders;

import java.math.BigDecimal;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.inobil.fraudwatcher.dal.TransactionRepository;
import com.inobil.fraudwatcher.entity.Transaction;

@Component
public class TransactionLoader implements ApplicationListener<ContextRefreshedEvent> {
 
    private TransactionRepository transactionRepository;
 
    private Logger log = Logger.getLogger(TransactionLoader.class);
 
    @Autowired
    public void setProductRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
 
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {  	
    	Random rnd = new Random();
        for (int i = 1; i <= 100; ++i){
          Boolean isPaid = rnd.nextBoolean();
          int applicationIdIndex = getRandomInteger(0, ApplicationLoader.ApplicationIdList.length-1, rnd);
          int orderId = getRandomInteger(10000, 100000, rnd);
          long receiverPhone = getRandomLong(5321000000L, 5559999999L, rnd);
          long senderPhone = getRandomLong(5321000000L, 5559999999L, rnd);
          String name = rnd.nextBoolean() ? "John" : "Jane";
          Transaction transaction = new Transaction();
          transaction.setApplicationId(ApplicationLoader.ApplicationIdList[applicationIdIndex].toString());
          transaction.setAmount(new BigDecimal(Math.abs(rnd.nextGaussian()) * 10));
          transaction.setIsPaid(isPaid);
          transaction.setNameSurname(String.format("%2$s Doe %1$d", getRandomInteger(100, 1000, rnd), name));
          transaction.setEmail(String.format("%2$sdoe%1$d@mail.net", getRandomInteger(100, 1000, rnd), name.toLowerCase()));
          transaction.setOrderId(String.valueOf(orderId));
          transaction.setReceiverPhone(String.format("0%1$d", receiverPhone));
          transaction.setSenderPhone(String.format("0%1$d", senderPhone));
          transactionRepository.save(transaction); 
          
          log.info("Saved Transaction - id: " + transaction.getId());
        }        
    }
    
    private static int getRandomInteger(int rangeStart, int rangeEnd, Random aRandom){
        if (rangeStart > rangeEnd) {
          throw new IllegalArgumentException("Start cannot exceed End.");
        }
        long range = (long)rangeEnd - (long)rangeStart + 1;
        long fraction = (long)(range * aRandom.nextDouble());
        return (int)(fraction + rangeStart);
      }
    
    private static long getRandomLong(long rangeStart, long rangeEnd, Random aRandom){
        if (rangeStart > rangeEnd) {
          throw new IllegalArgumentException("Start cannot exceed End.");
        }
        long range = (long)rangeEnd - (long)rangeStart + 1;
        long fraction = (long)(range * aRandom.nextDouble());
        return fraction + rangeStart;
      }
}