package com.dameng.common.core.utils.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * <p>
 *  线程池
 * </p>
 * @author 大梦
 * @since 2020-5-20
 */
public class ThreadPoolUtil {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolUtil.class);
    /**
     * 线程池
     */
    public static ThreadPoolExecutor threadPool;
    /**
     * 线程池配置
     */
    private static final int PROCESSOR_COUNT = Runtime.getRuntime().availableProcessors();
    /**
     * 阻塞队列
     */
    private static final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(10000);

    /**
     * 无返回值直接执行
     * @param runnable
     */
    public static void execute(String name, Runnable runnable){
        getThreadPool(name).execute(runnable);
    }

    /**
     * 返回值直接执行
     * @param callable
     */
    public static <T> Future<T> submit(String name, Callable<T> callable){
        return getThreadPool(name).submit(callable);
    }

    /**
     * 关闭统通用线程池
     */
    public static void shutdown() {
        threadPool.shutdown();
    }

    /**
     * 获取通用线程池
     */
    public static ThreadPoolExecutor getThreadPool(String name) {
        logger.info("准备申请线程资源执行[{}]", name);
        if (threadPool != null) {
            return threadPool;
        } else {
            synchronized (ThreadPoolUtil.class) {
                if (threadPool == null) {
                    threadPool = new ThreadPoolExecutor(PROCESSOR_COUNT, PROCESSOR_COUNT * 2, 1, TimeUnit.MINUTES,
                            workQueue, new NamedThreadFactory("Three-thread-pool"));
                }
                return threadPool;
            }
        }
    }

}
