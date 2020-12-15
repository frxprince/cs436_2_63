fun main(args: Array<String>) {
   // name("cs","mju")
//    println(name2(b="mju",a="cs"))
    println(cube2(1,2,3))
}

fun cube2(a:Int,b:Int,c:Int=1)=a*b*c


fun cube(a:Int,b:Int,c:Int=1):Int{
    return a*b*c
}


fun name2(a:String,b:String):String{
    return a+b
}


fun name(a:String,b:String){
    println("Hello my name is $a$b")
}