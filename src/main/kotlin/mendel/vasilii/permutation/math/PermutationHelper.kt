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

    fun getAllGenerateElements(n: Int): ArrayList<PerClass>{
        //создаем все порождающие элементы шрыппы Sn
        //их n-1
        val pers = arrayListOf<PerClass>()
        for (i in 1 until n){
            val a = IntArray(i+1)
            a[a.lastIndex]=1
            pers.add(PerClass(Degree(a)))
            if (i==1) continue
            val b = IntArray(i+1)
            b[b.lastIndex]=i
            pers.add(PerClass(Degree(b)))
        }
        return pers
    }

    fun getLength(p1: PerClass, p2: PerClass, n: Int): Int{
        //ищем расстояние между двумя перестановками в группе порядка n!
        val list = getAllGenerateElements(n)//все образующие и обратные к ним в Sn
        //нужно получать на каждом шаге список всех перестановок
        val arr = arrayListOf(p1)
        val prevArr = arrayListOf(p1)
        var length = 0
        while (!arr.contains(p2)){
            length++
            val curArr = arrayListOf<PerClass>()
            for (a in prevArr){
                val inc = getIncidents(a, list)
                for (b in inc){
                    if (!curArr.contains(b) && !arr.contains(b)){
                        arr.add(b)
                        curArr.add(b)
                    }
                }
            }
            prevArr.clear()
            prevArr.addAll(curArr)
        }
        return length
    }

    private fun getIncidents(per:PerClass, list: ArrayList<PerClass>):ArrayList<PerClass>{
        //получаем все инциндентные перестановки к заданной
        val res = arrayListOf<PerClass>()
        for (x in list){
            val p = Operations.multiplication(per, x)
            res.add(p)
        }
        return res
    }

}