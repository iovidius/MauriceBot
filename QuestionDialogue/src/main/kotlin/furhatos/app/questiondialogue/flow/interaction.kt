package furhatos.app.questiondialogue.flow

import furhatos.nlu.common.*
import furhatos.nlu.intent
import furhatos.flow.kotlin.*
import furhatos.app.questiondialogue.nlu.*

import Quiz

var q = Quiz()      // new Quiz
var i = 0           // Question index

// messages
var n = q.questions.count()
var welcomeMessage = "I'm going to ask you $n questions. Do you want to start?"
var goodbyeMessage: String = "Thanks for answering all the questions. Goodbye!"


val Start : State = state(Interaction) {
    onEntry {
        furhat.say("Hi there! My name is Maurice.")

        if (q.questions.count() == 0) {
            furhat.say("No questions were specified.")
            goto(Stop)
        }

        furhat.askYN(welcomeMessage){
            onResponse<Yes> {
                for (question in q.questions){
                    call(NextQuestion)
                    i++
                }
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
        furhat.say(goodbyeMessage)
        terminate()
    }
}


// Go to next question
val NextQuestion: State = state(Interaction){
    onEntry{
           // Go to the first question
           var type = q.questions.get(i).type
        
           if(type == QuestionType.YES_NO)
               call(YesNoQuestion)
           else if(type == QuestionType.MULTIPLE_CHOICE)
               call(MultipleChoiceQuestion)

            terminate()
    }
}

val YesNoQuestion : State = state(Interaction){
    onEntry {
        furhat.ask(q.questions.get(i).msg)
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
        furhat.ask(q.questions.get(i).msg)
    }
    onResponse<Numeric> { 
        terminate(it.intent.number)
    }

}