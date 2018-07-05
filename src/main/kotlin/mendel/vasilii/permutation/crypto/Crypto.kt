package mendel.vasilii.permutation.crypto

//import android.content.Context
//import android.util.Base64
import mendel.vasilii.permutation.math.Inversions
import mendel.vasilii.permutation.math.Operations
import mendel.vasilii.permutation.math.Permutation
//import mendel.vasilii.permutation.MediaScannerHelper
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.CipherOutputStream
import javax.crypto.spec.SecretKeySpec
import javax.crypto.spec.IvParameterSpec
import java.util.Arrays



object Crypto {
    //умеет создать ключ нужной длины нужного типа

    fun encrypt(size: Int, key: Inversions, message: String):String{
        val cipher = getCipher(keyToBytes(inv = key, size = size),Cipher.ENCRYPT_MODE)
        val encodeBytes = cipher!!.doFinal(message.toByteArray())
        val bytes = Base64.getEncoder().encode(encodeBytes)
//        return Base64.encodeToString(encodeBytes, Base64.DEFAULT)
        return String(bytes)
    }

    fun decrypt(size: Int, key: Inversions, message: String):String{
        val text = Base64.getDecoder().decode(message)
//        val text = Base64.decode(message, Base64.DEFAULT)
        val cipher = getCipher(keyToBytes(inv = key,size = size),Cipher.DECRYPT_MODE)
        val encodeBytes = cipher!!.doFinal(text)
//        return Base64.encodeToString(encodeBytes, Base64.DEFAULT)
        return String(encodeBytes)
    }

/*    fun decryptFile(key: Inversions, path: String, context: Context) : String{
        val cipher = getCipher(keyToBytes(inv = key),Cipher.DECRYPT_MODE)

        val file = File(path)
        Log.d("MyTag",file.name)
        val fileRes = File(context.getExternalFilesDir("Decrypt"),
                file.name)

        if (!context.getExternalFilesDir("Decrypt")!!.mkdir()) {
            Log.e("MyTag", "dir not created " + fileRes.exists())
        }
        //теперь нужно из одного файла читать а во второй писать
        val fis = FileInputStream(file)

        val fos = FileOutputStream(fileRes)//отсюда читаем
        val cos = CipherOutputStream(fos, cipher)//сюда пишем


        val bytes = ByteArray(1024)
        var length: Int
        length = fis.read(bytes)
        while (length>0) {
            cos.write(bytes, 0, length)
            length = fis.read(bytes)
        }
        cos.close()
        fis.close()
        fos.close()

        val mediaScannerHelper = MediaScannerHelper()
        mediaScannerHelper.addFile(fileRes.path,context)
        return file.name
    }*/

    /*
        fun encryptFile(key: Inversions, path: String, context: Context):String{
        val cipher = getCipher(keyToBytes(inv = key),Cipher.ENCRYPT_MODE)
        val file = File(path)
        Log.d("MyTag",file.name)
        val fileRes = File(context.getExternalFilesDir("Encrypt"),
                file.name)

        if (!context.getExternalFilesDir("Encrypt")!!.mkdir()) {
            Log.e("MyTag", "dir not created " + fileRes.exists())
        }
        //теперь нужно из одного файла читать а во второй писать
        val fis = FileInputStream(file)

        val fos = FileOutputStream(fileRes)//отсюда читаем
        val cos = CipherOutputStream(fos, cipher)//сюда пишем


        val bytes = ByteArray(1024)
        var length: Int
        length = fis.read(bytes)
        while (length>0) {
            cos.write(bytes, 0, length)
            length = fis.read(bytes)
        }
        cos.close()
        fis.close()
        fos.close()

        val mediaScannerHelper = MediaScannerHelper()
        mediaScannerHelper.addFile(fileRes.path,context)
        return fileRes.name
    }
     */

    fun generateKey(sz: Int, first: Boolean): Inversions {
        //sz - длина ключа (длина перестановки)
        //если first - то это первый ключ которые делится на две части
        //с которого начинается все
        val inv = Operations.randomPermutation(sz-1)//случайна таблица инверсий нужной длины
        val per = Permutation(inv)
//        Log.d("MyTag", "inv=${inv.values}")
        //Log.d("MyTag", "per=${per.values}")
        //если это первый ключ то первая половина остается там где и была
        //это sz/2-1 значения таблицы инверсий (точнее максимальное значение sz/2-1)
        val res = Inversions(1)
        if (first){
            val n = sz/2-1
            for (i in 1..n){
                res.set(i,inv.get(i))
            }
            //затем отступаем на sz/2 элементов
            val d = per.smallAfter(n)//этим заполняем все что посередине
            for (i in(n+1)..(n+sz/2)){
                res.set(i,d)
            }
            for (i in (n+sz/2+1)..(n+sz)){
                if (per.getPos(i-sz/2)<=n){
                    //увеличиваем
                    res.set(i,inv.get(i-sz/2)+sz/2)
                }else{
                    //добавляем как есть
                    res.set(i,inv.get(i-sz/2))
                }
            }
        }else{
            val n = sz/2-1
            for (i in 1..n){
                res.set(i,0)
            }
            for (i in (n+1)..(n+sz/2)){
                res.set(i,inv.get(i-sz/2))
            }

            val d = per.smallAfter(n)//этим заполняем все что посередине
            for (i in(n+sz/2+1)..(n+sz)){
                res.set(i,d)
            }
            for (i in (n+sz+1)..(n+3*sz/2)){
                if (per.getPos(i-sz)<=n){
                    //увеличиваем
                    res.set(i,inv.get(i-sz)+sz/2)
                }else{
                    //добавляем как есть
                    res.set(i,inv.get(i-sz))
                }
            }
        }
//        Log.d("MyTag","res=${res.values}")
        return res
    }

/*    fun testEncrypt(key: ByteArray, value: String){
        val inArr = ByteArray(16)
        for (i in 0..15){
            inArr[i]=52
        }
        val iv = IvParameterSpec(inArr)
        val skeySpec = SecretKeySpec(inArr, "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")//AES/CBC/PKCS5Padding
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)

        val encrypted = cipher.doFinal(value.toByteArray())
        System.out.println("encrypted string: " + String(encrypted))
    }*/

    fun getCipher(raw: ByteArray, mode: Int): Cipher? {
        val secretKeySpec = SecretKeySpec(raw, "AES")
        val iv = IvParameterSpec(raw)
        var cipher: Cipher? = null
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
//            cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING")
            cipher!!.init(mode, secretKeySpec,iv)
            return cipher
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //создаем на ее основе Cipher

        return null
    }

    fun keyToString(inv: Inversions): String{
        var res = ""
        val n = inv.values.size
        for (i in 0..(n-1)){
            res+= numToSymbol(inv.get(i))
        }
        return res
    }

    fun keyToBytes(inv: Inversions, size: Int): ByteArray{
        var bytes = ByteArray(size)
        for (i in 0..(size-1)){
            bytes[i]= numToSymbol(inv.get(i)).toByte()
        }
        return bytes
    }

    fun stringToKey(str: String): Inversions {
        val key = Inversions(1)
        var i = 0
        for (c in str){
            key.set(i, symbolToNum(c))
            i++
        }
        return key
    }

    private fun numToSymbol(num: Int): Char{
        return when (num){
            in 0..9->(48+num).toChar()
            in 10..31->(65+num-10).toChar()
            else -> ' '
        }
    }

    private fun symbolToNum(num: Char): Int{
        val v = num.toByte().toInt()
        return when (v){
            in 48..57->(v-48)
            in 65..86->(v-65+10)
            else->0
        }
    }
}