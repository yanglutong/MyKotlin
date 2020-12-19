package com.yang.mykotlin

//kotlin应用的入口点是main函数 第一要素为函数，其次是类
//kotlin的优点代码简洁，能有效避免空指针
//kotlin === 用于判断引用数据类型的地址值 == 用于判断两个数据的值

fun main() {
    println("杨路通")

    var jsonClass=TestDemo.JsonClass()
    jsonClass.hide()

    //类型之间的转换
    var a1:Int=1
    var b1:Long=2

    a1=b1.toInt()
    b1=a1.toLong()

    println(a1)
    println(b1)
    //此为java中的调用匿名类对象 相等于new TestDemo()


    //此为调用类的无参构造 TestDemo d=new TestDemo()
    var testDemo=TestDemo()

    testDemo.a=15
    testDemo.b="var testDemo=TestDemo()"
    testDemo.c=3


    println(testDemo.a)
    println(testDemo.b)
    println(testDemo.c)

    testDemo.android()
}
//测试类
class TestDemo {

    //Kotlin定义数据类型前必须先进行赋值
    var a:Int = 0
    //lateinit 是用在引用类型上 加上lateinit可以延迟初始化，但在调用这个对象时必须初始化赋值否则会报错
    lateinit var b:String

    //var 为数据类型 c变量名字 var在进行赋值时会自动判断数据类型
    var c=0




    //测试类里的方法
    fun android(){
        //Kotlin定义数组 初始化
        var arr:Array<Int> =Array(10){0}
        //使用增强for遍历数组
        arr.forEach {
            println(it)
        }
        arr[0]=1

        //类似java的int[]a ={1,2,3,4,5}
        var c:Array<Int> = arrayOf(1,2,3,4,5,6)

        //for循环遍历数组
        for (i in c) {
            println("i$i c$c")
        }
        //增强forIndexed此方法为遍历数组的下标和元素 index 下标 i 元素
        c.forEachIndexed { index, i ->
            println("下标为$index 元素为$i")
//            System.out.println("下标为"+id+"元素为"+name);
        }


        //找到c数组里元素大于3的返回boolean值
        val filter = c.filter {
            it > 3
        }
        //将boolean值输出打印
        filter.forEach {
            println(it)
        }
    }

    class JsonClass:Du{
        var de:Int=15
        var de2:String="123"

        fun hide(){
            var i=size(de,de2 .toInt())
            println(i)
        }
    }
    //父类
    open class Teacher(gs:Int,array:ArrayList<String>){

    }
//    class Student(a:Int,b:String):Teacher(5,arr){
        
//    }
    interface Du{
        fun size(a:Int,b:Int):Int{
            return if(a+b>5) a else b
        }
    }
}
