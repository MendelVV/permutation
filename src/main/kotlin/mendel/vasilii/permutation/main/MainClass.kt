package mendel.vasilii.permutation.main

import mendel.vasilii.permutation.math.Operations

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inv = Operations.randomPermutation(10)
            System.out.println(inv.values)
        }
    }
}