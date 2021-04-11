
import com.google.gson.annotations.SerializedName

// Class that represents categories, like 'Gryffindor' in a Hogwarts quiz
class Category(){

    @SerializedName("category") 
    var name: String = ""
    var content: String = ""
    
    @SerializedName("start_score")
    var startScore: Int = 0
}

