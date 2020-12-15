fun main(args: Array<String>) {
    var x= listOf("Hello","World","M","J","U")
    for (i in x){
      println("The string is $i")
    }

    var a=0..10
    println('d' in 'a'..'z')
 for(i in a){
     println("The value of i is $i")
 }

    loopA@for (i in 1..4)
        loopB@for(j in 1..3)
        { //  if(i==2 && j==2)continue
            if(i==3 && j==2)break@loopA
            print("$i , $j -->")
            println(i*j)
        }
println("Done")
 for(i in 10 downTo 1 step 3){
     print("$i,")
 }

  repeat(5){
      println("X")
  }

   var s= readLine()
   while(s!="csmju"){
       s= readLine()
   }
    println("yes!")
  for(i in 1 until 10)print(" $i,")
}