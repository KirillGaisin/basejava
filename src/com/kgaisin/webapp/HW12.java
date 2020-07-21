package com.kgaisin.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HW12 {

    static int[] values = {7, 2, 1, 9, 1, 1, 2, 4};

    static List<Integer> valueList = Arrays.stream(values).boxed().collect(Collectors.toList());
    static int sum = valueList.stream().mapToInt(Integer::intValue).sum();
    static boolean odd = sum % 2 != 0;
    static boolean even = sum % 2 == 0;

    public static void main(String[] args) {
        System.out.println("Performing minValue function \n" + minValue(values));
        System.out.println("Performing oddOrEven function \n" + oddOrEven(valueList));
    }

    static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce((num, nextNum) -> num * 10 + nextNum)
                .orElse(0);
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        System.out.println("Sum is " + sum);
        return integers.stream()
                .filter(entry -> entry % 2 == 0 ? even : odd)
                .collect(Collectors.toList());
    }
}
