package main.java.com.test.interfacetest;

public class InterfaceImpl implements Interface {
    private String user;

    public void push(int n) {
        skipandpopelements(n, n);
        popElements(n);
    }

    public int pop() {
        return 0;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
