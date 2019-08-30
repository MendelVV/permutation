package mendel.vasilii.permutation.math

open class SetOfPermutation {
    //множество подстановок

    operator fun contains(other: PerClass): Boolean{
        return permutations.contains(other)
    }

    val permutations = arrayListOf<PerClass>()

    fun get(i: Int):PerClass{
        if (i<permutations.size){
            return permutations[i]
        }else{
            throw IndexOutOfBoundsException("i=$i size=${permutations.size}")
        }
    }

    open fun add(per: PerClass){
        if (permutations.contains(per)) return
        permutations.add(per)
    }

    open fun remove(per: PerClass){
        if (!permutations.contains(per)) return
        permutations.remove(per)
    }

/*    fun contains(per: PerClass):Boolean{
        return permutations.contains(per)
    }*/
}