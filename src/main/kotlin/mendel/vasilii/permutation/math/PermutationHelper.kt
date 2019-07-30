package mendel.vasilii.permutation.math

object PermutationHelper {

    fun getGenerateElements(n: Int): ArrayList<PerClass>{
        //создаем все порождающие элементы шрыппы Sn
        //их n-1
        val pers = arrayListOf<PerClass>()
        for (i in 1 until n){
            val a = IntArray(i+1)
            a[a.lastIndex]=1
            pers.add(PerClass(Degree(a)))
        }
        return pers
    }

}