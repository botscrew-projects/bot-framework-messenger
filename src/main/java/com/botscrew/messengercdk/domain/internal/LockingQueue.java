package com.botscrew.messengercdk.domain.internal;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class LockingQueue<T> {

    private ReentrantLock lock;
    private Queue<T> queue;

    public LockingQueue() {
        lock = new ReentrantLock();
        queue = new ConcurrentLinkedQueue<>();
    }

    public synchronized void push(T t) {
        queue.add(t);
    }

    public T poll() {
        return queue.poll();
    }

    public boolean tryLock() {
        return lock.tryLock();
    }

    public void unlock() {
        lock.unlock();
    }

    public boolean isLocked() {
        return lock.isLocked();
    }

    public synchronized boolean hasNext() {
        return !queue.isEmpty();
    }
}
