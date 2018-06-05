/*
 * Copyright 2018 BotsCrew
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.botscrew.messengercdk.domain.internal;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Queue wrapper for specific needs inside messenger module
 * Main idea is the ability to define if some consumer is already working with this queue, so another will not start working with it.
 * In result we have queues that can be used only with one thread
 *
 * Usecase is sending messages to a user in the correct order, how they was added to the queue.
 * If we will send next message only when previous was delivered and response returned, we will have correct order
 * of messages delivered to a user.
 * @param <T>
 */
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

    /**
     * Uses internal lock to be sure that this method cannot be used in the same time as other which also using internal lock
     * (f.e getNextOrUnlock)
     * @return if this queue is being used or not
     */
    public boolean isLocked() {
        internalLock.lock();
        boolean result = lock.isLocked();
        internalLock.unlock();
        return result;
    }

    /**
     * Uses internal lock to be sure that this method cannot be used in the same time as other which also using internal lock
     * (f.e isLocked)
     * @return Optional wrapper for next object in a queue
     */
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

    public void clear() {
        queue.clear();
    }

    public void unlock() {
        lock.unlock();
    }
}
