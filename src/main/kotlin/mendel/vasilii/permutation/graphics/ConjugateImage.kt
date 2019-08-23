package mendel.vasilii.permutation.graphics

import mendel.vasilii.permutation.math.Operations
import mendel.vasilii.permutation.math.PerClass
import mendel.vasilii.permutation.math.PermutationHelper
import mendel.vasilii.permutation.math.factorial
import sun.plugin2.util.ColorUtil
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.geom.Ellipse2D
import java.awt.image.BufferedImage
import javax.swing.Spring.height
import javax.swing.Spring.width
import java.io.IOException
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.abs
import javax.swing.JPanel
import javax.swing.WindowConstants
import javax.swing.JFrame




class ConjugateImage(val per: PerClass, val n:Int) {

    val max: Long = factorial(n)

    fun createImage(){
        //создаем полотно и оси
        val d = 6
        val sz = (max*d+20).toInt()
        val bi = BufferedImage(sz, sz, BufferedImage.TYPE_INT_ARGB)
        val graphics = bi.createGraphics()
        //graphics.paint

        graphics.stroke = BasicStroke(2f)
        graphics.color = Color.BLACK
        graphics.drawLine(10,sz-10,sz-10, sz-10)
        graphics.drawLine(10,sz-10,10, 10)
        graphics.drawLine(10,sz-10,sz-10, 10)
        val colors = arrayListOf(Color.BLACK, Color.GREEN, Color.BLUE, Color.RED, Color.MAGENTA, Color.ORANGE)
        //дальше идем по всем элементам группы и строим точки до и после сопряжения
//        graphics.drawOval(0,0,20,20)
        val r = 10
        for (i in 0 until max){
            val p1 = PerClass(i)
            val res = Operations.conjugate(per, p1)
            val l1 = PermutationHelper.getLength(per, p1, n)
            val l2 = PermutationHelper.getLength(per, res, n)
            val delta = abs(l1-l2)
            graphics.color = colors[delta]
//            graphics.color = colors[l1]
            val x = 10+d*p1.getOrderOnGroup().toInt()-r/2
            val y = sz-(10+d*res.getOrderOnGroup().toInt())-r/2
            //graphics.drawOval(x,y,r,r)
            graphics.fill(Ellipse2D.Double(x.toDouble(),y.toDouble(),r.toDouble(),r.toDouble()))
        }

        val frame = JFrame()
        frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        frame.setSize(200, 200)

        val pane = object : JPanel() {
            override fun paintComponent(g: Graphics) {
                super.paintComponent(g)
                g.drawImage(bi, 0, 0, null)
            }
        }

        frame.add(pane)
        frame.extendedState = JFrame.MAXIMIZED_BOTH
        frame.isVisible = true

/*        try {
            if (ImageIO.write(bi, "png", File("e://output_image.png"))) {
                println("-- saved")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }*/

    }
}

fun main(){
    val n = 5
    val per = PerClass(6)
    val ci = ConjugateImage(per, n)
    ci.createImage()
}