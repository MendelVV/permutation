package mendel.vasilii.permutation.math


class Degree{
    //класс степеней образующих
    var values:MutableList<Int> = mutableListOf()

    constructor(n: Int){
        for (i in 0..n){
            values.add(i,0)
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
}