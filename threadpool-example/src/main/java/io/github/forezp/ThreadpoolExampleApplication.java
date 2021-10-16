package io.github.forezp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
public class ThreadpoolExampleApplication {

    Logger logger = LoggerFactory.getLogger(ThreadpoolExampleApplication.class);

    @Autowired
    ThreadPoolFactory threadPoolFactory;

    public static void main(String[] args) {
        SpringApplication.run(ThreadpoolExampleApplication.class, args);

    }

    @GetMapping("/test")
    public String test() {
        ThreadPoolExecutor threadPoolExecutor = threadPoolFactory.createDefaultPoolExecutor();

        logger.info("poolsize" + threadPoolExecutor.getPoolSize());
        logger.info("quenue" + threadPoolExecutor.getQueue().toString());
        logger.info("maxpoolsieze" + threadPoolExecutor.getMaximumPoolSize());
        return "ok";
    }

    @GetMapping("/test2")
    public String test2() {
        ScheduledThreadPoolExecutor threadPoolExecutor = threadPoolFactory.createScheduledThreadPoolExecutor(2);
        threadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("createScheduledThreadPoolExecutor:" + System.currentTimeMillis());
            }
        }, 2, 2, TimeUnit.SECONDS);
        logger.info("poolsize" + threadPoolExecutor.getPoolSize());
        logger.info("quenue" + threadPoolExecutor.getQueue().toString());
        logger.info("maxpoolsieze" + threadPoolExecutor.getMaximumPoolSize());
        return "ok";
    }


    @GetMapping("/test3")
    public String test3() {
        ScheduledThreadPoolExecutor threadPoolExecutor = threadPoolFactory.createSingleScheduledThreadPoolExecutor();
        threadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("createSingleScheduledThreadPoolExecutor:" + System.currentTimeMillis());
            }
        }, 2, 2, TimeUnit.SECONDS);
        logger.info("poolsize" + threadPoolExecutor.getPoolSize());
        logger.info("quenue" + threadPoolExecutor.getQueue().toString());
        logger.info("maxpoolsieze" + threadPoolExecutor.getMaximumPoolSize());
        return "ok";
    }

}
