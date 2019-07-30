package mendel.vasilii.permutation.math

import java.lang.Integer.max


class Degree(n: Int) {
    //класс степеней образующих
    var values:MutableList<Int> = mutableListOf()

    init {
        for (i in 0..n){
            values.add(i,0)
        }
    }

    constructor(arr: IntArray):this(arr.size){
        //задаем вместе с нулевой степенью
        for (i in 0 until arr.size){
            set(i, arr[i])
        }
    }

    constructor(inv: Inversions):this(inv.values.size-1){
        //нужно
        for ( i in values.size downTo 1){
            val n = (i+1+inv.get(i)-inv.get(i+1))%(i+1)
            set(i, n)
        }
    }

    fun get(i: Int):Int{
        if (i<values.size && i>0)
            return values[i]
        return 0
    }

    fun set(i: Int, v: Int){
        if (i<values.size && i>0){
            values[i] = v
        }else{
            for (j in (values.size) until i){
                values.add(j,0)
            }
            values.add(i,v)
        }
    }

    fun lenght():Int{
        return values.size
    }

    override fun equals(other: Any?): Boolean {
        if (other is Degree){
            //сравниваем значимые степени
            val n = max(other.lenght(), lenght())
            for (i in 1 until n){
                if (get(i)!=other.get(i)) return false
            }
            return true
        }else{
            return false
        }
    }

    override fun toString(): String {
        return "deg=$values"
    }
}