package com.iontrading.cat.foureyes.client_lib.mock;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.common.util.concurrent.ListenableFuture;
import com.iontrading.isf.executors.spi.ISequentialExecutorService;

public class MockSequentialExecutorService implements ISequentialExecutorService {
    
    private Runnable task;

    @Override
    public <T> ListenableFuture<T> submit(Callable<T> task) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListenableFuture<?> submit(Runnable task) {
       this.task = task;
        return null;
    }
    
    public Runnable getTask() {
        return task;
    }
    
    public void runTask() {
        task.run();
    }

    @Override
    public <T> ListenableFuture<T> submit(Runnable task, T result) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void shutdown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Runnable> shutdownNow() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isShutdown() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isTerminated() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void execute(Runnable command) {
        // TODO Auto-generated method stub
        
    }

}
