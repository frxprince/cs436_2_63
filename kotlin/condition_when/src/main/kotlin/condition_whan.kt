fun main(args:Array<String>){
    var n=1
 /* var text=""
  when(n){
      0-> text="zero"
      1->text="one"
      2->text="two"
      else->text="I don't know"
  }
  */
  var text= when(n){
        0->"zero"
        1->"one"
        2->"two"
        else->"I don't know"
    }
    println("$n  =   $text")
}
