package com.botscrew.messengercdk.domain.internal;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class LockingQueue<T> {

    private ReentrantLock lock;
    private ReentrantLock internalLock;
    private Queue<T> queue;

    public LockingQueue() {
        lock = new ReentrantLock();
        internalLock = new ReentrantLock();
        queue = new ConcurrentLinkedQueue<>();
    }

    public void push(T t) {
        queue.add(t);
    }

    public boolean tryLock() {
        return lock.tryLock();
    }

    public boolean isLocked() {
        internalLock.lock();
        boolean result = lock.isLocked();
        internalLock.unlock();
        return result;
    }

    public Optional<T> getNextOrUnlock() {
        internalLock.lock();
        if (!queue.isEmpty()) {
            internalLock.unlock();
            return Optional.of(queue.poll());
        } else {
            lock.unlock();
        }
        internalLock.unlock();
        return Optional.empty();
    }
}
