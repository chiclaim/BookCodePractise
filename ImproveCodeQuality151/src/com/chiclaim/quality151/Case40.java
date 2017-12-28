package com.chiclaim.quality151;

/**
 * Created by Chiclaim on 2017/12/21.
 */
public class Case40 {

    enum Ops {
        ADD, SUB
    }

    {
        System.out.println("Case40{}");
    }

    private int i, j, result;

    // 无参构造
    public Case40() {

    }

    // 有参构造
    public Case40(int _i, int _j) {
        i = _i;
        j = _j;
        System.out.println("Case40(int _i, int _j)");
    }

    // 设置符号，是加法运算还是减法运算
    protected void setOperator(Ops _ops) {
        result = _ops.equals(Ops.ADD) ? i + j : i - j;
        System.out.println("setOperator() i=" + i + ",j=" + j);

    }

    // 取得运算结果
    public int getResult() {
        return result;
    }

    public static void main(String[] args) {

//        Case40{}
//        Case40(int _i, int _j)
//        setOperator()
//          先执行构造方法，再执行构造代码块
        Case40 c1 = new Case40(1, 2) {
            {
                setOperator(Ops.ADD);
            }
        };

        System.out.println(c1.getResult());

//        Case40{}
//        Case40(int _i, int _j)
//        setOperator()
//        Add(int _i, int _j)
//          先执行构造代码块，再执行构造方法
        System.out.println(new Add(1, 2).getResult());

    }


    //加法计算
    static class Add extends Case40 {
        {
            setOperator(Ops.ADD);
        }

        //覆写父类的构造方法
        public Add(int _i, int _j) {
            super(_i, _j);
            System.out.println("Add(int _i, int _j)");
        }
    }

}
