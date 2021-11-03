package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 3.5.25 Registrar scheduling. The registrar at a prominent northeastern University
 * recently scheduled an instructor to teach two different classes at the same exact time.
 * Help the registrar prevent future mistakes by describing a method to check for such
 * conflicts. For simplicity, assume all classes run for 50 minutes starting at 9:00,
 * 10:00, 11:00, 1:00, 2:00, or 3:00.
 * <p>
 * 注册员调度.一个北部大学的注册员最近打算在同一时间内调度一个讲师教授两个不同的课.帮助注册员使用一个方法
 * 来检查冲突防止未来的错误.简单来说,假设所有课程从 9:00, 10:00, 11:00, 1:00, 2:00, 或 3:00 开始
 * 持续 50 分钟
 *
 * @author LeonChen
 * @since 9/13/21
 */
class E3_5_25 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {

        Map<Integer, HashSet<Integer>> instructorsMap = new HashMap<>();
        HashSet<Integer> instructor1Classes = new HashSet<>();
        instructor1Classes.add(9);
        instructor1Classes.add(10);
        instructor1Classes.add(15);

        HashSet<Integer> instructor2Classes = new HashSet<>();
        instructor2Classes.add(11);
        instructor2Classes.add(14);
        instructor2Classes.add(15);

        HashSet<Integer> instructor3Classes = new HashSet<>();
        instructor3Classes.add(10);

        instructorsMap.put(1, instructor1Classes);
        instructorsMap.put(2, instructor2Classes);
        instructorsMap.put(3, instructor3Classes);

        boolean conflict1 = isThereAConflict(instructorsMap, 1, 9);
        StdOut.println("Check conflict 1: " + conflict1 + " Expected: true");

        boolean conflict2 = isThereAConflict(instructorsMap, 1, 11);
        StdOut.println("Check conflict 2: " + conflict2 + " Expected: false");

        boolean conflict3 = isThereAConflict(instructorsMap, 1, 15);
        StdOut.println("Check conflict 3: " + conflict3 + " Expected: true");

        boolean conflict4 = isThereAConflict(instructorsMap, 2, 9);
        StdOut.println("Check conflict 4: " + conflict4 + " Expected: false");

        boolean conflict5 = isThereAConflict(instructorsMap, 2, 14);
        StdOut.println("Check conflict 5: " + conflict5 + " Expected: true");

        boolean conflict6 = isThereAConflict(instructorsMap, 3, 10);
        StdOut.println("Check conflict 6: " + conflict6 + " Expected: true");

        boolean conflict7 = isThereAConflict(instructorsMap, 3, 11);
        StdOut.println("Check conflict 7: " + conflict7 + " Expected: false");
    }

    private static boolean isThereAConflict(Map<Integer, HashSet<Integer>> instructorsMap, int instructor, int timeToSchedule) {
        if (timeToSchedule != 9
                && timeToSchedule != 10
                && timeToSchedule != 11
                && timeToSchedule != 13
                && timeToSchedule != 14
                && timeToSchedule != 15) {
            throw new IllegalArgumentException("Invalid class start time (must be 9, 10, 11, 13, 14 or 15)");
        }

        return instructorsMap.get(instructor).contains(timeToSchedule);
    }


}
