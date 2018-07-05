package mendel.vasilii.permutation.math

import mendel.vasilii.permutation.math.Inversions

class Permutation {
    //перестановка

    var values:MutableList<Int> = mutableListOf()

    constructor(n: Int){
        for (i in 0..n){
            values.add(i,i)
        }
    }

    constructor(inv: Inversions){
        val n = inv.values.size-1
        values.add(0)
        for (i in 1..n){
            var pos = i-inv.get(i)
            values.add(pos,i)
        }
    }

    fun get(i: Int):Int{
        if (i<values.size)
            return values[i]
        return 0
    }

    fun set(i: Int, v: Int){
        if (i<values.size)
            values[i] = v
    }

    fun getPos(n: Int):Int{
        return values.indexOf(n)
    }

    fun smallAfter(n: Int): Int{
        var res = 0
        for (i in (n+1)..(values.size-1)){
            if (values[i]<=n){
                res++
            }
        }
        return res
    }
}