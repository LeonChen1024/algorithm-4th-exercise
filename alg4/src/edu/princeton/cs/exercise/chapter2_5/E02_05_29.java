package edu.princeton.cs.exercise.chapter2_5;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 2.5.29 Sort files by size and date of last modification. Write comparators for the type
 * File to order by increasing/decreasing order of file size, ascending/descending order
 * of file name, and ascending/descending order of last modification date. Use these
 * comparators in a program LS that takes a command-line argument and lists the files
 * in the current directory according to a specified order, e.g ., "-t" to sort by timestamp.
 * Support multiple flags to break ties. Be sure to use a stable sort.
 * <p>
 * 通过大小和最近修改日期来排序文件.编写一个比较器使用文件大小的升/降序,文件名字的升/降序还有最近修改时间的升/降序
 * 来排序 File.在程序 LS 中接收一个命令行参数和一个当前文件夹下按照指定顺序排序列表,比如,"-t" 是通过时间戳来排序.
 * 支持使用多个标志来指定升降序.使用一个稳定排序.
 *
 * @author LeonChen
 * @since 1/9/20
 */
class E02_05_29 {

    private static final String SORT_SIZE = "sort_size";
    private static final String SORT_NAME = "sort_name";
    private static final String SORT_MODIFICATION = "sort_modification";

    private static final String ORDER_AC = "order_ac";
    private static final String ORDER_DC = "order_dc";

    /**
     * 便于测试直接使用参数设置
     */
    public static void main(String[] args) {

        String sortType = SORT_NAME;
        String order = ORDER_AC;

        String curPath = Paths.get("").toAbsolutePath().toString();
        File file = new File(curPath);
        File[] files = file.listFiles();

        Comparator comparator;

        switch (sortType) {
            case SORT_MODIFICATION:
                comparator =
                        new ModificationTimeComparator(order.equals(ORDER_AC));
                Insertion.sort(files, comparator);
                break;
            case SORT_SIZE:
                comparator =
                        new SizeComparator(order.equals(ORDER_AC));
                Insertion.sort(files, comparator);
                break;
            case SORT_NAME:
                comparator =
                        new NameComparator(order.equals(ORDER_AC));
                Insertion.sort(files, comparator);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + sortType);
        }

        StdOut.println(Arrays.toString(files));
    }

    private static class SizeComparator implements Comparator<File> {

        private final boolean ac;

        public SizeComparator(boolean ac) {
            this.ac = ac;
        }

        @Override
        public int compare(File o1, File o2) {
            return Long.compare(o1.length(), o2.length());
        }
    }

    private static class NameComparator implements Comparator<File> {

        private final boolean ac;

        public NameComparator(boolean ac) {
            this.ac = ac;
        }

        @Override
        public int compare(File o1, File o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    private static class ModificationTimeComparator implements Comparator<File> {
        private final boolean ac;

        public ModificationTimeComparator(boolean ac) {
            this.ac = ac;
        }

        @Override
        public int compare(File o1, File o2) {
            return Long.compare(o1.lastModified(), o2.lastModified());
        }
    }


}
