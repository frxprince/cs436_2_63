fun main(args: Array<String>) {
    var l1= listOf("Thai","English")
    println(l1.get(0))
    var l2= mutableListOf("a","b")
    l2.add(0,"c")
    println(l2)
    var s1= setOf(1,2,3,5,5,5,4,3,2,3,4,5,6,4,32,3)
    println(s1)
    var acron= mapOf<String,String>(Pair("TH","Thailand"),Pair("EN","English"))
    println(acron.get("TH"))

}