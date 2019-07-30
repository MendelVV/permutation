import mendel.vasilii.permutation.math.Degree
import mendel.vasilii.permutation.math.Inversions
import mendel.vasilii.permutation.math.PerClass
import org.junit.Test

class TestBasePermutation {

    @Test
    fun checkCreateDegreeOfInversion(){
        val inversion = Inversions(intArrayOf(0, 1, 1, 3, 0, 1))
        val d1 = Degree(inversion)
        val d2 = Degree(intArrayOf(0, 0, 1, 3, 4, 1))
        assert(d1==d2)
    }

    @Test
    fun checkOrder(){
        val i1 = Inversions(intArrayOf(0, 1, 1, 3, 0, 1))
        val p1 = PerClass(i1)
        assert(p1.getOrderOnGroup()==236L)

        val i2 = Inversions(intArrayOf(0, 1, 0, 1, 4, 0, 3))
        val p2 = PerClass(i2)
        assert(p2.getOrderOnGroup()==2627L)
    }

}