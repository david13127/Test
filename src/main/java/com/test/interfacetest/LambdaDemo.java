package com.test.interfacetest;

public class LambdaDemo {
    private static <R, T> R arrayOp(Lambda<R, T> lambda, T val){
        return lambda.func(val);
    }
    public static void main(String[] args) {
        Integer[] vals = {1, 2, 4, 3, 4, 4, 5};
        String[] strs = {"One", "Two", "Two", "Three", "Four", "Five"};
        int count;
        Lambda<MyClass, Integer> arrayOps = MyClass::new;
        MyClass integerMyClass = arrayOp(arrayOps, 4);
        System.out.println("val in integerMyClass: " + integerMyClass.getVal());


    }

}

class MyClass<T> {
    private T val;

    MyClass(T v) {
        val = v;
    }

    T getVal(){
        return val;
    }
}

class MyArrayOps<T> {
    private T t;
    MyArrayOps(T t) {
        this.t = t;
    }
    
    public <T> int countMatching(T[] vals) {
        int count = 0;
        for (int i = 0; i < vals.length; i++) {
            if(this.t == vals[i]) {
                count++;
            }
        }
        return count;
    }
}

