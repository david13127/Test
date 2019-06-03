package com.function.emailcheck;

public interface Effect<T> {
    void apply(T t);
}