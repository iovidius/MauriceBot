
// This class represents the individual questions
class Question (val msg: String, val type: QuestionType){

}

// Different types of questions
enum class QuestionType{
    YES_NO, LIKERT, NUMBER, OPEN
}