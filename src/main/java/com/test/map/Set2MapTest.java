package com.test.map;

import java.util.HashSet;

public class Set2MapTest {
    public static void main(String[] args) {
        Set2Map<String, Integer> scores = new Set2Map<>();
        Integer langScore = scores.put("语文", 87);
        scores.put("英语", 82);
        scores.put("英语", 82);
        System.out.println(scores);

        HashSet<Name> names = new HashSet<>();
        names.add(new Name("abc", "123"));
        names.add(new Name("abc", "456"));
        System.out.println(names.size());
        System.out.println(names);
    }
}
