import com.google.gson.*
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

import java.io.File
import java.io.BufferedReader
import java.nio.file.Paths
import java.lang.reflect.Type


// This class describes the set of questions

class Quiz {
    

    val name: String = ""
    val version: String = ""
    val remarks: String = ""
    
    // Does the Quiz return a result?
    @SerializedName("give_results") 
    val giveResult: Boolean = false   
    
    // questions
    var questions = listOf<Question>()

    @SerializedName("results")
    var categories = listOf<Category>()

    // score per category
    var scoring = mutableMapOf<Category, Int>()



    companion object{

      // Load file into Quiz object
      fun load(fileName: String) : Quiz{
          
          val path = Paths.get(System.getProperty("user.dir")).resolve(Paths.get(fileName))
          val bufferedReader: BufferedReader = File(path.toUri()).bufferedReader()    
          val json = bufferedReader.use { it.readText() }
          val q = Gson().fromJson(json, Quiz::class.java)
         
          // initialize categories
          for (c in q.categories){
            q.scoring.put(c, c.startScore)
          }
         
          println("Loaded questions succesfully!")
      
         return q
      }
    }
}
