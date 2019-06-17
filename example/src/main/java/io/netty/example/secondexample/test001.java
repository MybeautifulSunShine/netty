package io.netty.example.secondexample;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2019-06-17 10:42
 */
public class test001 {
    public static void main(String[] args) {

        //在构造方法执行之前就执行的
        int[] SIZE_TABLE;
        List<Integer> sizeTable = new ArrayList<Integer>();
        //16进制规律
        for (int i = 16; i < 512; i += 16) {
            sizeTable.add(i);
            System.out.println("sizeTabl:" + sizeTable);
        }
        //i向左位移1位,也就是乘以 2 一旦溢出 就跳出循环
        for (int i = 512; i > 0; i <<= 1) {
            sizeTable.add(i);
            System.out.println("sizeTabl2:" + sizeTable);
        }
        //sizeTable 是做什么事情?
        //创建同样大小的SIZE_TABLE 从小到大的顺序 设定可分配缓存区大小
        //也就是说根据前一次的缓存区大小分配 SIZE_TABLE 的大小去判断下一个大小并且 是
        //怎么去申请缓存区呢?
        SIZE_TABLE = new int[sizeTable.size()];
        System.out.println("sizeTable.size()"+sizeTable.size());
        for (int i = 0; i < SIZE_TABLE.length; i++) {
            SIZE_TABLE[i] = sizeTable.get(i);
            System.out.println(SIZE_TABLE[i]);
        }
    }
}
