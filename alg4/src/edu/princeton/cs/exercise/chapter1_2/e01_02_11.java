package edu.princeton.cs.exercise.chapter1_2;

/**
 * 1.2.11 Develop an implementation SmartDate of our Date API that raises an exception if the date
 * is not legal.
 *
 * <p>开发一个根据 Data API 实现的 SmartDate 当时间不合法的时候抛出一个异常.
 *
 * @author LeonChen
 * @since 12/9/19
 */
class e01_02_11 {

  public static void main(String[] args) {
    SmartDate smartDate = new SmartDate(2, 24, 1444);
    SmartDate smartDate1 = new SmartDate(6, 44, 2008);
    SmartDate smartDate2 = new SmartDate(2, 24, 1444);
  }

  public static class SmartDate {

    private int[] daysOfMonth = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public SmartDate(int month, int day, int year) {
      if (!isValid(month, day, year)) {
        throw new IllegalArgumentException("not legal date");
      }
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
