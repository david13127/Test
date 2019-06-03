package com.test.map;

public class Name {
    private String first;
    private String last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj.getClass() == Name.class) {
            Name name = (Name) obj;
            return name.first.equals(first);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return first.hashCode();
    }

    @Override
    public String toString() {
        return "Name[first = " + first + ", last = " + last + "]";
    }
}
