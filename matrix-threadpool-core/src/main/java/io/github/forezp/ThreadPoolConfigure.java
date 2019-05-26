package io.github.forezp;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * Created by forezp on 2019/5/26.
 */

@Configurable
public class ThreadPoolConfigure {


    @Value("${" + ThreadConstant.THREAD_POOL_CORE_POOL_SIZE + ":4}")
    private int threadPoolCorePoolSize;

    @Value("${" + ThreadConstant.THREAD_POOL_MAXIMUM_POOL_SIZE + ":8}")
    private int threadPoolMaximumPoolSize;

    @Value("${" + ThreadConstant.THREAD_POOL_KEEP_ALIVE_TIME + ":900000}")
    private long threadPoolKeepAliveTime;

    @Value("${" + ThreadConstant.THREAD_POOL_ALLOW_CORE_THREAD_TIMEOUT + ":false}")
    private boolean threadPoolAllowCoreThreadTimeout;

    @Value("${" + ThreadConstant.THREAD_POOL_QUEUE + ":LinkedBlockingQueue}")
    private String threadPoolQueue;

    @Value("${" + ThreadConstant.THREAD_POOL_QUEUE_CAPACITY + ":1024}")
    private int threadPoolQueueCapacity;

    @Value("${" + ThreadConstant.THREAD_POOL_REJECTED_POLICY + ":BlockingPolicyWithReport}")
    private String threadPoolRejectedPolicy;

    @Bean
    public ThreadPoolFactory threadPoolFactory() {

        ThreadParameter threadParameter = new ThreadParameter();
        threadParameter.setThreadPoolCorePoolSize(threadPoolCorePoolSize);
        threadParameter.setThreadPoolMaximumPoolSize(threadPoolMaximumPoolSize);
        threadParameter.setThreadPoolKeepAliveTime(threadPoolKeepAliveTime);
        threadParameter.setThreadPoolAllowCoreThreadTimeout(threadPoolAllowCoreThreadTimeout);
        threadParameter.setThreadPoolQueue(threadPoolQueue);
        threadParameter.setThreadPoolQueueCapacity(threadPoolQueueCapacity);
        threadParameter.setThreadPoolRejectedPolicy(threadPoolRejectedPolicy);

        return new ThreadPoolFactory(threadParameter);
    }


    @Bean
    public EventContextClosedHandler eventContextClosedHandler() {
        return new EventContextClosedHandler();
    }
}