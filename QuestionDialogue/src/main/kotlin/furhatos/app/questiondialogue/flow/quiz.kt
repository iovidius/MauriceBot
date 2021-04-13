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

    val likert: Int = 5
    
    // Does the Quiz return a result?
    @SerializedName("give_results") 
    val giveResult: Boolean = false   
    
    // questions
    var questions = listOf<Question>()

    @SerializedName("results")
    var categories = listOf<Category>()

    // score per category
    var scoring = mutableMapOf<String, Int>()

    // Evaluate given answers
    fun evaluate(question: Question, answer: String){

      for (s in question.scoring) {

        // If the answer is given, update scoring
        if (answer == s.answer){
          
          var currentScore = scoring.get(s.category)
          currentScore?.let{
            var q = currentScore + s.score
            var r = s.category
            println("updating $r from $currentScore to $q")
            scoring.replace(s.category, currentScore + s.score)
          } 
        }
      }
    }


    companion object{

      // Load file into Quiz object
      fun load(fileName: String) : Quiz{
          
          val path = Paths.get(System.getProperty("user.dir")).resolve(Paths.get(fileName))
          val bufferedReader: BufferedReader = File(path.toUri()).bufferedReader()    
          val json = bufferedReader.use { it.readText() }
          val q = Gson().fromJson(json, Quiz::class.java)
         
          // initialize categories
          for (c in q.categories){
            q.scoring.put(c.name, c.startScore)
          }
         
          println("Loaded questions succesfully!")
      
         return q
      }
    }
}
