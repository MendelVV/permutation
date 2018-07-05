package mendel.vasilii.permutation.math

class PerClass {
    //элемент группы перестановок
    lateinit var permutation: Permutation//перестановка
    lateinit var inversions: Inversions//таблица инверсий
    lateinit var degree: Degree//степени образующих

    constructor(number: Long){
        //более хитрый способ задания
        //задавать будем наверное степени
        if (number<0) return
        degree = Degree(1)//с этого начнем
        //можно идти от начала и до конца
        var max = 1
        var f=1
        var num = number
        while (!((number>=f)&&(number<f*(max+1)))){
            f*=++max
        }
        for (i in max downTo 1){
            degree.set(i,(num/f).toInt())
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

    private fun initInversions(){
        //инициализируем таблицу инверсий с помощью степеней образующих
        if (::degree.isInitialized)
            inversions = Inversions(degree)
    }

}