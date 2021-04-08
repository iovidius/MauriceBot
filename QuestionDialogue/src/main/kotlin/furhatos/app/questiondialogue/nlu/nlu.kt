package furhatos.app.questiondialogue.nlu
import furhatos.nlu.common.Number
import furhatos.nlu.*
import furhatos.util.Language


class Numeric(val number : Number? = null) : Intent() {
     override fun getExamples(lang: Language): List<String> {
        return listOf("1", "2", "3")
    }
}

