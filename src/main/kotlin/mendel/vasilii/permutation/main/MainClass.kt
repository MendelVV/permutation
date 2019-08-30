package mendel.vasilii.permutation.main

import mendel.vasilii.permutation.crypto.Crypto
import mendel.vasilii.permutation.math.*


private fun crypto(){
    val inv = Operations.randomPermutation(15)
//            System.out.println(inv.values)
    val text = "hello world!"
//            Crypto.testEncrypt(Crypto.keyToBytes(inv),text)
    val enc = Crypto.encrypt(16,inv,text)
    println(enc)
    val dec = Crypto.decrypt(16,inv,enc)
    println(dec)
}

private fun commutative(){
    val x1 = PerClass(1)
    val x2 = PerClass(2)
    val x3 = PerClass(6)
    val x4 = PerClass(24)
    val x5 = PerClass(120)

    val group = GroupOfPermutation(arrayListOf(x1, x2, x3, x4, x5))
    println(group.permutations.size)

    val n = group.permutations.size
    var sz = 0
    for (i in 0 until n){
//        println("i=$i")
        val p1 = group.get(i)
        if (p1.getOrderOnGroup()==0L) continue
        val g1 = GroupOfPermutation(arrayListOf(p1))
        for (j in i+1 until n){
            val p2 = group.get(j)
            if (p2 in g1 || p1*p2 != p2*p1) continue
            val g2 = GroupOfPermutation(arrayListOf(p1, p2))
            for (k in j+1 until n){
                val p3 = group.get(k)
                if (p3 in g2) continue
                    if ((p1*p2==p2*p1)
                            && (p1*p3==p3*p1)
                            && (p2*p3==p3*p2)){
                        val newGroup = GroupOfPermutation(arrayListOf(p1, p2, p3))
                        println("newGroup size = ${newGroup.permutations.size} p1=${p1.getDegree()} p2=${p2.getDegree()} p3=${p3.getDegree()}")
                        sz++
                }
            }
        }
    }
    println("total commutative = $sz")
}

fun main(){
//    crypto()
    val p1 = PerClass(Degree(intArrayOf(0, 0, 0, 0, 0, 0, 0, 7)))
    val p2 = PerClass(Degree(intArrayOf(0, 0, 0, 3)))
//    val res = Operations.multiplication(p1, p2)
    val res = p1*p2
   // println(p2.getInversions())
    println(res.getDegree())
//    commutative()
}

