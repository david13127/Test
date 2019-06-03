package main.java.com.test.enumtest;

public enum Apple {
    Jonathan("Red",1),
    GoldenDel("Green",2),
    RedDel("Red",3),
    Winesap("Green",4),
    Cortland("Red",5);

    private String color;
    private int price;
    Apple(String color, int price) {
        this.color = color;
        this.price = price;
    }
    public int getPrice() {
        return price;
    }

    public String getColor(){
        return color;
    }
}
