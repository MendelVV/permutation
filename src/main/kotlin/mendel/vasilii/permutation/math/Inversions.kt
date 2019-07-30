package mendel.vasilii.permutation.math

class Inversions(n: Int) {
    //класс таблицы инверсий в том смысле который в моей математике
    var values:MutableList<Int> = mutableListOf()

    init {
        for (i in 0..n){
            values.add(i,0)
        }
    }

    constructor(inv: Inversions):this (1){
        val n = inv.values.size
        for (i in 1 until n){
            set(i,inv.get(i))
        }
    }

    constructor(deg: Degree):this(1){
        val n = deg.values.size
        for (i in n-1 downTo 1){
            val v = (get(i+1)+deg.get(i))%(i+1)
            set(i,v)
        }
    }

    constructor(arr: IntArray):this(arr.size-1){
        //задаем вместе с нулефой инверсией
        for (i in 0 until arr.size){
            set(i, arr[i])
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
        if (other is Inversions){
            //сравниваем значимые степени
            val n = Integer.max(other.lenght(), lenght())
            for (i in 1 until n){
                if (get(i)!=other.get(i)) return false
            }
            return true
        }else{
            return false
        }
    }

    override fun toString(): String {
        return "inv=$values"
    }
}