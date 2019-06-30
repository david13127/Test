package com.test.interfacetest;

public interface Interface {

    abstract int pop();
    abstract void push(int item);

    default int[] popElements(int n) {
        return getElements(n);
    }

    default int[] skipandpopelements(int skip, int n) {
        getElements(skip);
        return getElements(n);
    }

    default int[] getElements(int n) {
        int[] elements = new int[n];
        for (int i = 0; i < n; i++) {
            elements[i] = pop();
        }
        return elements;
    }
}
