lateinit var b:String
fun main(args: Array<String>) {
   var a:String?=null
//a="hello"
 //  if(a ==null )a="hello"
   // a=a?:"hello world"

    println("$a")
    println(a?.length?:-1)
   // println(a!!.length)

   b="abcd"
   println(b)
}