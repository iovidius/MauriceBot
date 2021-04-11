
// This class represents the individual questions
class Question (){
    var intro: String = ""
    var text: String = ""
    var type: QuestionType = QuestionType.YES_NO
    var score: Int = 0

    // here are all scores
    var scoring = listOf<Score>()
}

// Different types of questions
enum class QuestionType{
    YES_NO, MULTIPLE_CHOICE, LIKERT, NUMBER, OPEN
}