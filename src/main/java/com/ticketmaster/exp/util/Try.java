package com.ticketmaster.exp.util;

import com.ticketmaster.exp.Experiment;

import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * Created by dannwebster on 4/17/15.
 */
public class Try<T> implements Callable<T>{
    private final Optional<T> v;
    private final Optional<Exception> x;

    public static <T> Try<T> from(Callable<T> c) {
        T t = null;
        Exception e = null;
        try {
            t = c.call();
        } catch (Exception ex) {
            e = ex;
        }
        return of(t, e);

    }
    public static <T> Try<T> of(T t, Exception e) {
        return new Try(t, e);
    }

    private Try(T t, Exception e) {
        this.v = Optional.ofNullable(t);
        this.x = Optional.ofNullable(e);
    }

    public T call() throws Exception {
        if (v.isPresent()) {
            return v.get();
        } else {
            throw x.get();
        }
    }
    public Optional<T> value() {
        return v;
    }

    public Optional<Exception> exception() {
        return x;
    }

    public boolean isFailure() {
        return x.isPresent();
    }

    public boolean isSuccess() {
        return v.isPresent();
    }
}