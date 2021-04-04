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
            goto(Idle)
    }

    onResponse<Yes>{

        // Go to the first question
        var type = q.questions.get(0).type
        
        if(type == QuestionType.YES_NO)
            goto(YesNoQuestion)
        
    }

    onResponse<No>{
        // CLOSE
        furhat.say(q.goodbyeMessage)
        goto(Idle)
    }
}

val YesNoQuestion : State = state(Interaction){
    onEntry {
        furhat.ask(q.questions.get(i).msg)
    }
    onResponse<Yes> { 
        // XX
    }

    onResponse<No> { 
         
    }
}

