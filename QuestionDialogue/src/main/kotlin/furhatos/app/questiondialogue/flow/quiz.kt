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

   // init {
    //  questions = listOf(Question("What is love?", QuestionType.YES_NO),
    //                        Question("What is the meaning of life?", QuestionType.MULTIPLE_CHOICE))
   // }


    companion object{
      // Load file into Quiz object
      fun load(fileName: String) : Quiz{
          
          val path = Paths.get(System.getProperty("user.dir")).resolve(Paths.get(fileName))
          val bufferedReader: BufferedReader = File(path.toUri()).bufferedReader()    
          val json = bufferedReader.use { it.readText() }
          val q = Gson().fromJson(json, Quiz::class.java)
         
          println("Loaded questions succesfully!")
      
         return q
      }
    }

     //   var q =  Gson().fromJson(filename, Quiz::class.java)
        //val weatherList: List<Question> = gson.fromJson(stringReader , Array<WeatherObject>::class.java).toList()

        //q.questions = 
        //return q   

}
