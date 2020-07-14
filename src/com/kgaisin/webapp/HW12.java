package com.kgaisin.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HW12 {

    static int[] values = {7, 2, 1, 9, 1, 1, 2};

    static List<Integer> valueList = Arrays.stream(values).boxed().collect(Collectors.toList());

    public static void main(String[] args) {
        System.out.println("Performing minValue function \n" + minValue(values));
        System.out.println("Performing oddOrEven function \n" + oddOrEven(valueList));
    }

    static int minValue(int[] values) {
        List<Integer> uniques = Arrays.stream(values)
                .boxed()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        int num = 0;

        for (int nextNum : uniques) {
            num = num * 10 + nextNum;
        }
        return num;
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        List<Integer> toRemove = new ArrayList<>();
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum is " + sum);
        if(sum % 2 != 0) {
            integers.forEach(entry -> {
                if(entry % 2 != 0) {
                    toRemove.add(entry);
                }
            });
        }
        integers.removeAll(toRemove);
        return integers;
    }
}
