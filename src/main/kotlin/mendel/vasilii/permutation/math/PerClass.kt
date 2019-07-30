package mendel.vasilii.permutation.math

import mendel.vasilii.permutation.exceptions.NotInitDegreeException
import mendel.vasilii.permutation.exceptions.NotInitInversionsException

class PerClass {
    //элемент группы перестановок
    private var permutation: Permutation? = null//перестановка
    private var inversions: Inversions? = null//таблица инверсий
    private var degree: Degree? = null//степени образующих

    constructor(number: Long){
        //более хитрый способ задания
        //задавать будем наверное степени
        if (number<0) return
        degree = Degree(1)//с этого начнем
        if (number==0L) return
        //можно идти от начала и до конца
        var max = 1
        var f=1
        var num = number
        while (!((number>=f)&&(number<f*(max+1)))){
            f*=++max
        }
        for (i in max downTo 1){
            degree!!.set(i,(num/f).toInt())
            num %= f
            f/=i
        }
        initInversions()
    }

    constructor(p: Permutation){
        permutation = p
    }

    constructor(i: Inversions){
        inversions = i
    }

    constructor(d: Degree){
        degree = d
    }

    fun getInversions(): Inversions{
        initInversions()
        return inversions!!
    }


    fun getOrderOnGroup():Long{
        //нужно сначала задать степени
        initDegree()
        var n = 0L
        for (i in 1 until degree!!.lenght()){
            n+=degree!!.get(i)*factorial(i)
        }

        return n
    }

    private fun initDegree(){
        if (degree==null){
            //задаем степень
            if (inversions==null){
                //кидаем иключение
                throw NotInitInversionsException()
            }
            degree = Degree(inversions!!)
        }
    }

    private fun initInversions(){
        //инициализируем таблицу инверсий с помощью степеней образующих
        if (inversions == null){
            if (degree==null){
                throw NotInitDegreeException()
            }
            inversions = Inversions(degree!!)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is PerClass){
            return other.getOrderOnGroup()==getOrderOnGroup()
        }
        return false
    }
}