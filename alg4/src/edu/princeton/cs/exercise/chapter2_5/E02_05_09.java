package edu.princeton.cs.exercise.chapter2_5;

/**
 * 2.5.9 Develop a data type that allows you to write a client that can sort a file such as the
 * one shown at right.
 *
 * <code>
 * input (DJI volumes for each day)
 *
 * 1-Oct-28 3500000
 * 2-Oct-28 3850000
 * 3-Oct-28 4060000
 * 4-Oct-28 4330000
 * 5-Oct-28 4360000
 * ...
 * 30-Dec-99 554680000
 * 31-Dec-99 374049984
 * 3-Jan-00 931800000
 * 4-Jan-00 1009000000
 * 5-Jan-00 1085500032
 * ...
 *
 *
 * output
 *
 * 19-Aug-40 130000
 * 26-Aug-40 160000
 * 24-Jul-40 200000
 * 10-Aug-42 210000
 * 23-Jun-42 210000
 * ...
 * 23-Jul-02 2441019904
 * 17-Jul-02 2566500096
 * 15-Jul-02 2574799872
 * 19-Jul-02 2654099968
 * 24-Jul-02 2775559936</code>
 *
 * 开发一个数据类型使得你可以编写一个可以下面文件进行排序的客户端
 *
 * @author LeonChen
 * @since 12/18/20
 */
class E02_05_09 {


    /**
     *
     */
    public static void main(String[] args) {

    }

    private static class DayVolume implements Comparable<DayVolume> {
        private String day;
        private long volume;

        public DayVolume(String day, long volume) {
            this.day = day;
            this.volume = volume;
        }


        @Override
        public int compareTo(DayVolume o) {
            return Long.compare(volume, o.volume);
        }

    }
}
