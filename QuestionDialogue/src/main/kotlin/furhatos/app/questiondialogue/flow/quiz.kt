

// This class describes the set of questions

class Quiz {
    

    
    // questions
    var questions = listOf<Question>()

    init {
      questions = listOf(Question("What is love?", QuestionType.YES_NO),
                            Question("What is the meaning of life?", QuestionType.MULTIPLE_CHOICE))
    }

    

}