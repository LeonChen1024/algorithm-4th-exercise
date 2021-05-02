package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.1.4 Develop Time and Event ADTs that allow processing of data as in the example
 * illustrated on page 367.
 * <p>
 * 开发一个 Time 和 Event 的 ADT ,可以像 367 页那样处理数据
 *
 * @author LeonChen
 * @since 1/31/21
 */
class E3_1_04 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ST<Time, Event> st = new ST();
        st.put(new Time(4, 3, 2), new Event("1"));
        st.put(new Time(2, 3, 2), new Event("2"));
        st.put(new Time(4, 6, 2), new Event("3"));
        st.put(new Time(4, 3, 6), new Event("4"));
        st.put(new Time(1, 3, 5), new Event("5"));

        for (Time time : st) {
            StdOut.println(time.toString() + st.get(time).toString());
        }

        StdOut.println(st.floor(new Time(4, 3, 4)));
    }


    private static class Time implements Comparable<Time> {
        private int hour;
        private int minute;
        private int second;

        public Time(int hour, int minute, int second) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }

        @Override
        public int compareTo(Time o) {
            return hour != o.hour ? Integer.compare(hour, o.hour) :
                    minute != o.minute ? Integer.compare(minute, o.minute)
                            : Integer.compare(second, o.second);

        }

        @Override
        public String toString() {
            return "Time{" +
                    "hour=" + hour +
                    ", minute=" + minute +
                    ", second=" + second +
                    '}';
        }

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }
    }

    private static class Event {
        private String name;

        @Override
        public String toString() {
            return "Event{" +
                    "name='" + name + '\'' +
                    '}';
        }

        public Event(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
