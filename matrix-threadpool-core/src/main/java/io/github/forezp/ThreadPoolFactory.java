package io.github.forezp;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 线程池构造类
 *
 * @author fangzhipeng create 2018-07-05
 **/
public class ThreadPoolFactory {

    private Map<String, ThreadPoolExecutor> threadPoolMap = new ConcurrentHashMap<>();
    private static final String DEFAULT_THREAD_POOL = "defaultThreadPool";
    private ThreadParameter threadParameter;

    public ThreadPoolFactory(ThreadParameter threadParameter) {
        this.threadParameter = threadParameter;
    }

    public ThreadPoolExecutor createDefaultPoolExecutor() {
        return createThreadPoolExecutor(DEFAULT_THREAD_POOL);
    }

    public ThreadPoolExecutor createThreadPoolExecutor(String identifier) {
        if (threadParameter == null) {
            return null;
        }
        return createThreadPoolExecutor(identifier, threadParameter);
    }

    public ThreadPoolExecutor createThreadPoolExecutor(String identifier, ThreadParameter threadParameter) {
        return createThreadPoolExecutor(identifier, threadParameter.getThreadPoolCorePoolSize(), threadParameter.getThreadPoolMaximumPoolSize(), threadParameter.getThreadPoolKeepAliveTime(), threadParameter.getThreadPoolQueue(), threadParameter.getThreadPoolQueueCapacity(),
                threadParameter.getThreadPoolRejectedPolicy());

    }

    public ThreadPoolExecutor createThreadPoolExecutor(String identifier, int coreSize, int maxSize,
                                                       Long keepAliveTime, String queneType, int queueSize,
                                                       String rejectType) {
        if (threadPoolMap.get(identifier) != null) {
            return threadPoolMap.get(identifier);
        } else {
            ThreadPoolExecutor newThreadPool = new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime,
                    TimeUnit.MILLISECONDS, createQuene(queneType, queueSize), createThreadFactory(identifier),
                    createRejectedExecutionHandler(rejectType));
            if (newThreadPool != null) {
                threadPoolMap.putIfAbsent(identifier, newThreadPool);
            }
            return newThreadPool;
        }
    }

    private RejectedExecutionHandler createRejectedExecutionHandler(String rejectedPolicy) {

        ThreadRejectedPolicyType rejectedPolicyType = ThreadRejectedPolicyType.fromString(rejectedPolicy);

        switch (rejectedPolicyType) {
            case BLOCKING_POLICY_WITH_REPORT:
                return new BlockingPolicyWithReport();
            case CALLER_RUNS_POLICY_WITH_REPORT:
                return new CallerRunsPolicyWithReport();
            case ABORT_POLICY_WITH_REPORT:
                return new AbortPolicyWithReport();
            case REJECTED_POLICY_WITH_REPORT:
                return new RejectedPolicyWithReport();
            case DISCARDED_POLICY_WITH_REPORT:
                return new DiscardedPolicyWithReport();
        }
        return null;
    }

    private ThreadFactory createThreadFactory(final String threadName) {

        return new ThreadFactory() {
            private AtomicInteger number = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, threadName + "-" + number.getAndIncrement());
            }
        };
    }

    private BlockingQueue<Runnable> createQuene(String queue, int queueCapacity) {

        ThreadQueueType queueType = ThreadQueueType.fromString(queue);
        switch (queueType) {
            case LINKED_BLOCKING_QUEUE:
                return new LinkedBlockingQueue<>(queueCapacity);
            case ARRAY_BLOCKING_QUEUE:
                return new ArrayBlockingQueue<>(queueCapacity);
            case SYNCHRONOUS_QUEUE:
                return new SynchronousQueue<>();
        }
        return null;
    }

    public void shutdown() {
        Set<Map.Entry<String, ThreadPoolExecutor>> entrySet = threadPoolMap.entrySet();
        for (Map.Entry<String, ThreadPoolExecutor> entry : entrySet) {
            ThreadPoolExecutor threadPoolExecutor = entry.getValue();
            threadPoolExecutor.shutdown();
        }
        threadPoolMap.clear();
        threadPoolMap = null;
    }

}
