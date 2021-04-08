package furhatos.app.questiondialogue.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.questiondialogue.nlu.*

import Quiz


var q = Quiz()      // new Quiz
var i = 0           // Question index

val Start : State = state(Interaction) {
    onEntry {
        // TODO: I'm going to ask you n questions
        if (q.questions.count() > 0)
            furhat.ask(q.welcomeMessage)
        else
            furhat.say("No questions were specified.")
            furhat.say(q.goodbyeMessage)
            terminate()
    }

    onResponse<Yes>{
        goto(NextQuestion)
    }

    onResponse<No>{
        // CLOSE
        furhat.say(q.goodbyeMessage)
        terminate()
    }
}

// Go to next question
val NextQuestion: State = state(Interaction){
    onEntry{
           // Go to the first question
           var type = q.questions.get(i).type
        
           if(type == QuestionType.YES_NO)
               goto(YesNoQuestion)
           else if(type == QuestionType.MULTIPLE_CHOICE)
               goto(MultipleChoiceQuestion)
    }
}

val YesNoQuestion : State = state(Interaction){
    onEntry {
        furhat.ask(q.questions.get(i).msg)
    }
    onResponse<Yes> { 
        // XX
        i++
        goto(NextQuestion)
    }

    onResponse<No> { 
         
    }
}

val MultipleChoiceQuestion: State = state(Interaction){
    onEntry {
        furhat.ask(q.questions.get(i).msg)
    }
    onResponse<Numeric> { 
        furhat.say("you chose " + it.intent.number)
    }

}