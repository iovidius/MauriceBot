
// This class represents the individual questions
class Question (){
    var text: String = ""
    var type: QuestionType = QuestionType.YES_NO

}

// Different types of questions
enum class QuestionType{
    YES_NO, MULTIPLE_CHOICE, LIKERT, NUMBER, OPEN
}