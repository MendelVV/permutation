package mendel.vasilii.permutation.math

import java.util.*

object Operations {

    fun randomPermutation(sz: Int): Inversions {
        //здесь sz это количество элементов которые мы переставлем
        //а значит и последний индекс в таблице инверсий
        val random = Random()
        val inv = Inversions(sz)
        for (i in 1..(sz)){
            val d = random.nextInt(i+1)
            inv.set(i,d)
        }
        //сгенерировали случайную таблицу инверсий
        return inv
    }

    fun multiplication(i1: Inversions, i2: Inversions): Inversions {
        var inv = Inversions(i2)
        val n = i1.values.size-1

        for (i in n downTo 1){
            if (i1.get(i)>0){
                inv = subMultiplications(i,i1.get(i),inv)
            }
        }

        return inv
    }

    fun multiplication(p1: PerClass, p2: PerClass): PerClass {
        return PerClass(multiplication(p1.getInversions(), p2.getInversions()))
    }

    fun getReverse(inv: Inversions): Inversions {
        val n = inv.values.size-1//максимальный индекс
        val res = Inversions(1)//результат
        var nums:MutableList<Int> = mutableListOf()
        for (i in n downTo 1){
            var k = 0
            var j = n
            while (k<inv.get(i)){
                if (!nums.contains(j)){
                    res.set(j,res.get(j)+1)
                    k++
                }
                j--
            }
            nums= addNone(nums, j)
        }

        return res
    }

    fun conjugate(i1: Inversions, i2: Inversions): Inversions {
        //i1^-1*i2*i1
        val reverse = getReverse(i1)
        val res = multiplication(multiplication(reverse,i2),i1)
        return res
    }

    private fun addNone(nums: MutableList<Int>, n: Int): MutableList<Int>{
        var i =n
        while (true){
            if (!nums.contains(i)){
                nums.add(i)
                break
            }
            i--
        }
        return nums
    }

    private fun subMultiplications(k: Int, ek: Int, inv: Inversions): Inversions {
        //позиция, стпень, таблица инверсий
        //умножение на один элемент таблицы инверсий
        val res = Inversions(1)
        val maxIndex = inv.values.size-1
        var max = maxIndex
        if (k>maxIndex)
            max=k
        if ((k-ek)>maxIndex){
            //если просто проходит мимо
            for (i in 1..(k-ek-1)){
                res.set(i,inv.get(i))
            }
            res.set(k,ek)
        }else{
            //заполнили часть которая остается без изменений
            for (i in 1..(k-ek-1)){
                res.set(i,inv.get(i))
            }

            var c = 0
            for (i in (k-ek)..(k-1)){
                val w= calcW(inv.get(k-ek)+c,inv.get(i+1))
                c=c+1-w
                if (i>0)
                    res.set(i, inv.get(i+1)-w)
            }
            res.set(k,inv.get(k-ek)+c);
            if (max>k)
                for (i in (k+1)..max){
                    res.set(i, inv.get(i))
                }
        }
        return res
    }

    private fun calcW(a: Int, b:Int): Int{
        return if (a>=b){
            0
        }else{
            1
        }
    }

    fun generateAllCycles(n: Int){

    }

}