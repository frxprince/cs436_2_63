fun main(args: Array<String>) {
    var score=90
  /*  var status=""
    if(score>50){
        status="pass"
    }else
    {
        status="fail"
    }*/
 var status=if(score>50)"pass" else "fail"
    println(status)

 var grade=if(score>=80){
     println("Yeh!")
     "A"
 }else if (score>=70){
     "B"
 } else if(score>=60){
     "C"
 }else{
    "I"
 }
    println(grade)



}