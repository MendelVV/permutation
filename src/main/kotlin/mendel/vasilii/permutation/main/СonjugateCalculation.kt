package mendel.vasilii.permutation.main

import mendel.vasilii.permutation.math.Operations
import mendel.vasilii.permutation.math.PerClass
import mendel.vasilii.permutation.math.PermutationHelper
import mendel.vasilii.permutation.math.factorial

//работаем тут с сопряженными

fun main(){
    //берем какой-то элемент
    val n = 4//порядок группы
    val per = PerClass(1)

    val max = factorial(n)
    println("per ${per.getDegree()}\n========================")
    for (i in 1 until max){
        val e = PerClass(i)
        val res = Operations.conjugate(e, per)
        val l1 = PermutationHelper.getLength(per, e, n)
        val l2 = PermutationHelper.getLength(per, res, n)
        println("${e.getDegree()} l1=$l1")
        println("${res.getDegree()} l2=$l2")
        println("----------------------------------")
    }
}