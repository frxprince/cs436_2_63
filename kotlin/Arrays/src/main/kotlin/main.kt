fun main(args: Array<String>) {
    var country= arrayOf("TH","EN","JP","US")
    println(country[0])
    println(country[3])
    var currency=Array<String>(size=5,init={index->"no data"})
    currency[4]="THB"
    println(currency[0])
    println(currency[4])
    var pic= arrayOfNulls<Int>(10)
    pic[0]=100
    println(pic[0] == null)
    for((ind,c) in country.withIndex()){
        println("Country at ${ind+1} is $c")
    }
  var cats= Array<String>(size=10,init={index->"Cat ${index+1}"})
   for(c in cats){
       println(c)
   }

}