package edu.princeton.cs.exercise.chapter1_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.12 Add a method dayOfTheWeek() to SmartDate that returns a String value Monday, Tuesday,
 * Wednesday, Thursday, Friday, Saturday, or Sunday, giving the appropriate day of the week for the
 * date. You may assume that the date is in the 21st century.
 *
 * <p>添加一个 dayOfTheWeek() 方法到 SmartDate 并返回一个字符串值 Monday, Tuesday, Wednesday, Thursday, Friday,
 * Saturday, or Sunday,给出该日期对应的星期名.你可以假设这个日期是21世纪.
 *
 * @author LeonChen
 * @since 12/9/19
 */
class e01_02_12 {

  public static void main(String[] args) {

    SmartDate smartDate = new SmartDate(12, 9, 2019);
    StdOut.println(smartDate.dayOfTheWeek());
  }

  public static class SmartDate {

    private final int year;
    private final int month;
    private final int day;
    private int[] daysOfMonth = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int[] leapDays = {0, 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
    private int[] days = {0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    private static final String[] weekdays = {
      "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };

    public SmartDate(int month, int day, int year) {

      if (!isValid(month, day, year)) {
        throw new IllegalArgumentException("not legal date");
      }
      this.year = year;
      this.month = month;
      this.day = day;
    }

    private boolean isValid(int month, int day, int year) {
      if (month < 1 || month > 12) {
        return false;
      }
      if (day < 1 || day > daysOfMonth[month]) {
        return false;
      }
      if (month == 2 && day == 29 && !isLeapYear(year)) {
        return false;
      }

      return true;
    }

    private String dayOfTheWeek() {
      int crossDay = 0;
      if (isLeapYear(year)) {
        crossDay = leapDays[month];
      } else {
        crossDay = days[month];
      }

      crossDay += day;

      for (int i = 2000; i < year; i++) {
        if (isLeapYear(i)) {
          crossDay += 366;
        } else {
          crossDay += 365;
        }
      }

      // 1/1/2000 是周六
      return weekdays[((crossDay - 1) % 7 + 6) % 7];
    }

    private boolean isLeapYear(int year) {
      if (year % 400 == 0) {
        return true;
      }

      if (year % 100 == 0) {
        return false;
      }

      return year % 4 == 0;
    }
  }
}
