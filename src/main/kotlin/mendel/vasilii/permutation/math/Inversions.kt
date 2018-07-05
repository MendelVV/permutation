package mendel.vasilii.permutation.math

class Inversions(n: Int) {
    //класс таблицы инверсий в том смысле который в моей математике
    var values:MutableList<Int> = mutableListOf()

    constructor(inv: Inversions):this (1){
        val n = inv.values.size
        for (i in 1..(n-1)){
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

    fun get(i: Int):Int{
        if (i<values.size && i>0)
            return values[i]
        return 0
    }

    fun set(i: Int, v: Int){
        if (i<values.size && i>0){
            values[i] = v
        }else{
            for (j in (values.size)..(i-1)){
                values.add(j,0)
            }
            values.add(i,v)
        }
    }

    init {
        for (i in 0..n){
            values.add(i,0)
        }
    }

}