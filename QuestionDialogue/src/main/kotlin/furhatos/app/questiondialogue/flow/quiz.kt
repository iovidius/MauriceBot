

// This class describes the set of questions

class Quiz {
    
    // messages
    var welcomeMessage: String = "Hi there. I'm going to ask you some questions! Do you want to start?"
    var goodbyeMessage: String = "Thanks for answering all the questions. Goodbye!"
    
    // questions
    var questions = listOf<Question>()

    init {
      questions = listOf(Question("What is love?", QuestionType.YES_NO),
                            Question("What is the meaning of life?", QuestionType.MULTIPLE_CHOICE))

      // override welcome message
      var count = questions.count()
      welcomeMessage = "Hi there! I'm going to ask you $count questions. Do you want to start?"
    }

    

}