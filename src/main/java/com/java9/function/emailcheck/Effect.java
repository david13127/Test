package com.java9.function.emailcheck;

public interface Effect<T> {
    void apply(T t);
}