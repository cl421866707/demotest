package cn.my3gods.demotest;

import org.junit.jupiter.api.Test;

public class BinaryTest {

    @Test
    public void testBinary() {

        int i = 0B00001110;
        int y = 0B00001001;

        // 与 运算：二进制位每一位 都为1 则为1
        int result1 = i & y;
        // 或 运算：二进制位每一位 有一位为1 则为1
        int result2 = i | y;
        // 异或 运算：二进制位每一位 不同 则为1
        int result3 = i ^ y;
        System.err.println(result1 == 8);
        System.err.println(result2 == 15);
        System.err.println(result3 == 7);
    }

    /**
     * 判断就奇偶
     */
    @Test
    public void testOddEvenNumber() {
        // 87
        int odd = 0B1010111;
        // 62
        int even = 0B00111110;
        // 1
        int one = 0B00000001;
        // 使用 与运算 判断奇偶：结果为1则为奇数，为0则为偶数
        System.err.println(odd & one);
        // 87 & 1 = 1010111 & 00000001 = 00000001
        System.err.println(even & one);
        // 62 & 1 = 00111110 & 00000001 = 00000000
    }

    /**
     * 判断是否为2的正整数次幂
     */
    @Test
    public void testPositiveInteger() {
        // 0B10000
        int n = 16;
        // 打印二进制原码
        System.err.println(Integer.toBinaryString(n));
        /*
         如果为2的正整数次幂，则其二进制原码必为1后面n个0的形式，则 n & (n-1) 必为 0，
         eg:
         16 = 10000
         16 - 1 = 01111
         10000 & 01111 = 0
         */
        System.err.println(n & (n - 1));

        /*
        15 = 1111
        14 = 1110
        1111 & 1110 = 1110
         */
    }

    @Test
    public void test() {
        /*
        mod 2^k 呢？（对2的倍数取模）
        n&((1<<k)-1)
         */
        int n = 13;
        int k = 2;
        System.err.println(13 % 4);
        System.err.println(n & ((1 << k) - 1));
    }
}
