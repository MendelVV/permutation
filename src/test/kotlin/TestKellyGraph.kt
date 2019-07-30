import mendel.vasilii.permutation.math.Degree
import mendel.vasilii.permutation.math.KellyGraph
import mendel.vasilii.permutation.math.PerClass
import mendel.vasilii.permutation.math.PermutationHelper.getGenerateElements
import mendel.vasilii.permutation.math.factorial
import org.junit.Test

class TestKellyGraph{

    @Test
    fun  testCreateGraph(){
        val graph = KellyGraph(4)
        graph.createGraph()
        graph.print()
    }

    @Test
    fun checkGenerateElements(){

        val list = arrayListOf<PerClass>()
        list.add(PerClass(Degree(intArrayOf(0, 1))))
        list.add(PerClass(Degree(intArrayOf(0, 0, 1))))
        val listE = getGenerateElements(3)
        assert(listE.size==2)
        for (per in list){
            assert(listE.contains(per))
        }
    }

    @Test
    fun checkLength(){
//        val per = PerClass(Degree(intArrayOf(0, 0, 2)))

        //8! - не влезает в Int
        val n = 7
        val graph = KellyGraph(n)
        graph.createGraph()
        val max = factorial(n)
        var maxLength = 0
        for (i in 0 until max){
            val per = PerClass(i)
            val res = graph.getLengthGraph(per)
            if (maxLength<res.max()!!){
                maxLength = res.max()!!
            }
            /*println()
            for (a in res){
                print("$a ")
            }*/
            println("step $i from $max max=$maxLength")
        }
        println(maxLength)

    }

}