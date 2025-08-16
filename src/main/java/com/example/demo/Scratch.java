package com.example.demo;

import java.util.*;

public class Scratch {
    public static void main(String[] args) {
        var inputString ="abcd";
//        System.out.println(solution("aaabc"));

        System.out.println("Permutations of \"" + inputString + "\" with length " + 3 + ":");

        Set<String> stringList = new HashSet<>();
        generatePermutations(inputString, "",stringList);
        System.out.println(stringList.stream().filter(Scratch::isPalindrome).count());

    }

    public static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        String rev = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            rev = rev + s.charAt(i);
        }
            return s.equals(rev);
        }


    public static void generatePermutations(String str, String prefix,Set<String> stringList) {
        int length  =3;
        if (prefix.length() == length) {
            stringList.add(prefix);
            return;
        }

        if (str.isEmpty()) {
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String remaining = str.substring(0, i) + str.substring(i + 1);
            generatePermutations(remaining, prefix + ch,stringList);
        }

    }
}