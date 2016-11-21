/* SimpleApp.scala */

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.graphx.lib._
import scala.util.control._


object SimpleApp {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    
    val loop = new Breaks;
    loop.breakable {
        while(true) {
           val query = readLine("input your query: ")
           if (query == "finish") {
               loop.break
           }
           val uuid = readLine("input the uuid/color: ")
	   val queryUuid = query + "," + uuid
	   // NLP to process the query 

           val javaClass2 = new MyJavaClass()
           val addResult = javaClass2.adder(3,4)
           println(addResult)

           //test NLP
	   val query2 = "VM keeps in migrating state, and can not finish migration"
	   val uuid2 = "xxx-111-yyy-222"
	   val myPrediction = new PredictionRun()
	   val predictRes = myPrediction.predictionRun(query2, uuid2)
           println(predictRes) 

           val intentArg = nlp(queryUuid)
	   // graphx to execute the intent 
      	   val result = graphx(intentArg._1, intentArg._2)
           println(result)
	   }
    }
  }
  def nlp(queryUuid: String): (String, String) = {
       println("the nlp is processing: " + queryUuid)
       val intent = queryUuid.split(",")(0)
       val argument = queryUuid.split(",")(1)
       return (intent, argument)
  }
 
  def graphx(intent: String, argument: String): String = {
      val result = intent + "/" + argument 
      return result  
 } 


}
  
