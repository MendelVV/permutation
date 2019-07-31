import mendel.vasilii.permutation.math.factorial
import org.junit.Test

class TestLanguage {

    @Test
    fun testListSize(){
        val n = 10

        val max = factorial(n)
        val list = ArrayList<Long>()
        for (i in 0 until max){
            list.add(i)
        }
        println("size=${list.size} e=${list.last()}")
    }

}