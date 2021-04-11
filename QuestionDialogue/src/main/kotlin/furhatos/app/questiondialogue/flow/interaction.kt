package furhatos.app.questiondialogue.flow

import furhatos.nlu.common.*
import furhatos.nlu.intent
import furhatos.flow.kotlin.*
import furhatos.app.questiondialogue.nlu.*
import java.io.IOException

import Quiz
import Question

var q : Quiz = Quiz()    // new Quiz
var i = 0                // Question index


val Start : State = state(Interaction) {
    onEntry {
        
        furhat.say("Hi there! My name is Maurice.")

        // load quiz
        try{
            q = Quiz.load("../../assets/hogwarts.json")
        }
         catch (ioException: IOException) {
            ioException.printStackTrace()
            furhat.say("There was an error loading the questions.")
            goto(Stop)
        }


        if (q.questions.count() == 0) {
            furhat.say("No questions were specified.")
            goto(Stop)
        }

        var n = q.questions.count()
        furhat.askYN("I'm going to ask you $n questions. Do you want to start?"){
            onResponse<Yes> {
                for (question in q.questions){
                    call(NextQuestion)
                    i++
                }
                goto(Stop)
            }

            onResponse<No> { 
                goto(Stop)
             }
        }
    }
}

// Terminate program
val Stop: State = state(Interaction){
    onEntry {
        if (i > 0)
            furhat.say("Thanks for answering all questions!")

        // TODO calculate result
        if (q.giveResult){

        }

        furhat.say("Goodbye!")
        terminate()
    }
}


// Go to next question
val NextQuestion: State = state(Interaction){
    onEntry{
           // Go to the first question
           var x = q.questions.get(i)

           // Optionally start with introduction
           if (!x.intro.isBlank()) {
               furhat.say(x.intro)
           }
        
        
           // Ask question
           if(x.type == QuestionType.YES_NO)
               call(YesNoQuestion)
           else if(x.type == QuestionType.MULTIPLE_CHOICE)
               call(MultipleChoiceQuestion)

            terminate()
    }
}

val YesNoQuestion : State = state(Interaction){
    onEntry {
        furhat.ask(q.questions.get(i).text)
    }
    onResponse<Yes> { 
        terminate(true)
    }

    onResponse<No> { 
        terminate(false)
    }
}

val MultipleChoiceQuestion: State = state(Interaction){
    onEntry {
        furhat.ask(q.questions.get(i).text)
    }
    onResponse<Numeric> { 
        var j = it.intent.number

        // very rudimentary way of checking that the option is there
        if (!q.questions.get(i).text.contains("$j:")){
            furhat.say("$j is not an option.")
            reentry()
        }

        terminate(it.intent.number)
    }
}
