package io.github.forezp;

/**
 *
 * @author fangzhipeng create 2018-07-05
 */
public interface RejectedRunnable extends Runnable {
    // 如果任务被拒绝，用户自行处理
    void rejected();
}