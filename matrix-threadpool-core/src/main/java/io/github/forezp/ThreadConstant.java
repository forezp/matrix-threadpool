package io.github.forezp;



public class ThreadConstant {
    public static final int CPUS = Math.max(2, Runtime.getRuntime().availableProcessors());
    public static final String THREAD_POOL_CORE_POOL_SIZE = "threadpool.core.pool.size";
    public static final String THREAD_POOL_MAXIMUM_POOL_SIZE = "threadpool.maximum.pool.size";
    public static final String THREAD_POOL_KEEP_ALIVE_TIME = "threadpool.keep.alive.time";
    public static final String THREAD_POOL_ALLOW_CORE_THREAD_TIMEOUT = "threadpool.allow.core.thread.timeout";
    public static final String THREAD_POOL_QUEUE = "threadpool.queue";
    public static final String THREAD_POOL_QUEUE_CAPACITY = "threadpool.queue.capacity";
    public static final String THREAD_POOL_REJECTED_POLICY = "threadpool.rejected.policy";

    public static final String DEFAULT_THREADPOOL_SHARED_NAME = "SharedThreadPool";
}