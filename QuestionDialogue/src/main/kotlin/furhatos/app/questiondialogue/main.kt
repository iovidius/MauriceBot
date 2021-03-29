package furhatos.app.questiondialogue

import furhatos.app.questiondialogue.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class QuestiondialogueSkill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
