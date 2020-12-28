fun main(args: Array<String>) {
  var lineObj=Line(5.0)
  lineObj.printwidth()
  var squareObj=Square(6.0)
  squareObj.printwidth();squareObj.findarea();squareObj.printarea();
    var rectangleObj=Rectangle(3.0,6.0)
  rectangleObj.printwidth();rectangleObj.findarea();rectangleObj.printarea()
var diamondObj=AnyShape(5.0,6.0)
diamondObj.findarea(object:AnyShapeInterface{
    override fun anyArea(w: Double, h: Double): Double {
        return w*h*0.5
    }
})
diamondObj.printarea()
var diamondObj2=AnyShapeLambda(5.0,6.0)
diamondObj2.findarea { a, b -> a*b*0.5}
diamondObj2.printarea()
}


class AnyShapeLambda(var a_w:Double,var a_h:Double):Rectangle(a_w,a_h){
   fun findarea(farea:(w:Double,h:Double)->Double){
       area=farea(width,height)
   }
}


class AnyShape(var a_w:Double,var a_h:Double):Rectangle(a_w,a_h){
   fun findarea(farea:AnyShapeInterface){
      area= farea.anyArea(width,height)
   }
}
interface AnyShapeInterface{
    fun anyArea(w:Double,h:Double):Double
}











open class Line(var width:Double){
   init{
      println("Line is called!")
  }
 fun printwidth(){ println("The width is $width")}
}
open class Square(var square_width:Double):Line(square_width){
    var area:Double=0.0
    open fun findarea(){ area=width *width }
    fun printarea(){println("The area is $area")}
}
open class Rectangle(var rec_w:Double,var height:Double):Square(rec_w){
    override fun findarea() {
        area=width*height
    }
}



