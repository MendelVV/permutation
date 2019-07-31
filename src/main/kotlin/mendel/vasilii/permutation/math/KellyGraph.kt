package mendel.vasilii.permutation.math

import mendel.vasilii.permutation.math.PermutationHelper.getGenerateElements

class KellyGraph(val n: Int) {
    //это граф кели
    //входной параметр - длина перестановок

    val graph: ArrayList<IntArray>//размеры графа n на n, если есть ребро то 1 если нет то 0

    init {
        val max = factorial(n)
        graph = arrayListOf()
        for (i in 0 until max){
            graph.add(IntArray(max.toInt()))
        }
    }

    fun createGraph(){
        //создаем теперь сам граф
        //ребро есть только если g1=g2xi, где xi - один из образующих
        //поэтому для начала нужно получить множество всех образующих
        val pers = getGenerateElements(n)
        //теперь для каждого элемента группы мы должны посчитать все варианты которые
        // получатся при умножении его на некоторый образующий
        val max = factorial(n)
        for (i in 0 until max){
            val per = PerClass(i)
            for (e in pers){
                val res = Operations.multiplication(per, e)
                graph[i.toInt()][res.getOrderOnGroup().toInt()]=1
                graph[res.getOrderOnGroup().toInt()][i.toInt()]=1
            }
        }
    }

    fun getLengthGraph(per: PerClass): IntArray{
        //создаем список вершин с
        val max = graph.size
        val res = IntArray(max) {-1}

        //далее идем по всем смежным вершинам для начального элемента и запоминаем все использованные вершины
        val num = per.getOrderOnGroup().toInt()
        res[num]=0
        val arr = arrayListOf(num)
        val preArr = arrayListOf(num)
        var length = 0
        while (arr.size<max){
            //собираем список из всех кто инциндентен вершинам с предыдущего шага
            length++
            val list = arrayListOf<Int>()
            for (a in preArr){
                val inc = getIncidents(a)
                for (b in inc){
                    if (!list.contains(b) && !arr.contains(b)){
                        if (res[b]>=0){
                            println("ERROR b=$b res[b]=${res[b]}")
                        }
                        res[b]=length
                        arr.add(b)
                        list.add(b)
                    }
                }
            }
            preArr.clear()
            preArr.addAll(list)
        }

        return res
    }

    private fun getIncidents(num: Int):ArrayList<Int>{
        val res = arrayListOf<Int>()
        for (i in 0 until graph[num].size){
            if (graph[num][i]==1){
                res.add(i)
            }
        }
        return res
    }

    fun print(){
        for (arr in graph){
            for (a in arr){
                print("$a ")
            }
            println()
        }
    }


}