package mendel.vasilii.permutation.main

import mendel.vasilii.permutation.crypto.Crypto
import mendel.vasilii.permutation.math.Operations

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inv = Operations.randomPermutation(15)
//            System.out.println(inv.values)
            val text = "hello world!"
//            Crypto.testEncrypt(Crypto.keyToBytes(inv),text)
            val enc = Crypto.encrypt(16,inv,text)
            System.out.println(enc)
            val dec = Crypto.decrypt(16,inv,enc)
            System.out.println(dec)
        }
    }
}