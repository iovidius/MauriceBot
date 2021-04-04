

// This class describes the set of questions

class Quiz {
    
    // messages
    var welcomeMessage: String = "Hi there. I'm going to ask you some questions! Do you want to start?"
    var goodbyeMessage: String = "Goodbye!"
    
    // questions
    var questions = listOf<Question>()

    init {
      questions = listOf(Question("What is love?", QuestionType.YES_NO),
                            Question("What is the meaning of life?", QuestionType.YES_NO))

       // welcomeMessage = "Hi there! I'm going to ask you " + questions.count() + " questions. Do you want to start?"
    }

    

}