# matrix-eventbus


[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?label=license)](https://github.com/forezp/matrix-eventbus/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.forezp/matrix-threadpool-starter.svg?label=maven%20central)](http://mvnrepository.com/artifact/io.github.forezp/matrix-threadpool-starter)

## 这个项目干嘛的?

线程池创建和管理的工具类

## 怎么用


### 添加依赖：


```$xslt

<dependency>
  <groupId>io.github.forezp</groupId>
  <artifactId>matrix-threadpool-starter</artifactId>
  <version>1.2</version>
</dependency>
```


### 添加配置（非必须）

- threadpool.core.pool.size
- threadpool.maximum.pool.size
- threadpool.keep.alive.time
- threadpool.allow.core.thread.timeout
- threadpool.queue
- threadpool.queue.capacity
- threadpool.rejected.policy

具体怎么配见ThreadConstant类。


### 使用

```
@Autowired
ThreadPoolFactory threadPoolFactory;

ThreadPoolExecutor threadPoolExecutor=threadPoolFactory.createDefaultPoolExecutor();

ThreadPoolExecutor threadPoolExecutor=threadPoolFactory.createThreadPoolExecutor("forezp");


ThreadParameter threadParameter =new ThreadParameter();
threadParameter.setThreadPoolCorePoolSize(12);
threadParameter.setThreadPoolKeepAliveTime(600000);
...
ThreadPoolExecutor threadPoolExecutor=threadPoolFactory.createThreadPoolExecutor("forezp",threadParameter);


```
