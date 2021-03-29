package furhatos.app.questiondialogue.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.questiondialogue.nlu.*

val Start : State = state(Interaction) {

    onEntry {
        // TODO: I'm going to ask you n questions
        furhat.ask("Hi there. I'm going to ask you some questions! Do you want to start?")
    }

    onResponse<Yes>{
        // Go to the first question
        furhat.say("I like humans.")
    }

    onResponse<No>{
        furhat.say("That's sad.")
    }
}
