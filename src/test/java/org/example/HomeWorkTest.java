package org.example;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeWorkTest {

    HomeWork homeWork = new HomeWork();

    @Test
    void checkFirst() {
        TestCase1 testCase1 = generateTestCase1();
        assertEquals(testCase1.expected, homeWork.getOriginalDoorNumbers(testCase1.maxDoors, testCase1.actionList));

        TestCase1 testCase2 = generateTestCase2();
        assertEquals(testCase2.expected, homeWork.getOriginalDoorNumbers(testCase2.maxDoors, testCase2.actionList));
    }

    @Test
    void checkSecond() {
        assertEquals(asList(3, 1, 5, 2, 4), homeWork.getLeaveOrder(5, 3));
        assertEquals(asList(4, 3, 5, 2, 1), homeWork.getLeaveOrder(5, 4));
        assertEquals(asList(4, 8, 2, 7, 3, 10, 9, 1, 6, 5), homeWork.getLeaveOrder(10, 4));
    }


    private TestCase1 generateTestCase1() {
        TestCase1 testCase = new TestCase1();
        testCase.parseExpected("5\n" +
                "4\n" +
                "6\n" +
                "4\n" +
                "7");
        testCase.parseInput("20 7\n" +
                "L 5\n" +
                "D 5\n" +
                "L 4\n" +
                "L 5\n" +
                "D 5\n" +
                "L 4\n" +
                "L 5");
        return testCase;
    }

    private TestCase1 generateTestCase2() {
        TestCase1 testCase = new TestCase1();
        testCase.parseExpected("5\n" +
                "4\n" +
                "6\n" +
                "4\n" +
                "7\n" +
                "4\n" +
                "8\n" +
                "4\n" +
                "9\n" +
                "4\n" +
                "10\n" +
                "4\n" +
                "11\n" +
                "11");
        testCase.parseInput("50 20\n" +
                "L 5\n" +
                "D 5\n" +
                "L 4\n" +
                "L 5\n" +
                "D 5\n" +
                "L 4\n" +
                "L 5\n" +
                "D 5\n" +
                "L 4\n" +
                "L 5\n" +
                "D 5\n" +
                "L 4\n" +
                "L 5\n" +
                "D 5\n" +
                "L 4\n" +
                "L 5\n" +
                "D 5\n" +
                "L 4\n" +
                "L 5\n" +
                "L 5");
        return testCase;
    }


    @RequiredArgsConstructor
    static class TestCase1 {
        int maxDoors;
        List<Action> actionList = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        public void parseInput(String input) {
            String[] lines = input.split("(\n|\r|\r\n)");
            maxDoors = Integer.parseInt(lines[0].split(" ")[0]);
            Arrays.stream(lines)
                    .skip(1)
                    .map(Action::parse)
                    .forEach(actionList::add);

        }


        public void parseExpected(String output) {
            String[] lines = output.split("(\n|\r|\r\n)");
            Arrays.stream(lines)
                    .map(Integer::parseInt)
                    .forEach(expected::add);
        }
    }

}