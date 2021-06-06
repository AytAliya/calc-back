package calc.calc.enums

import calc.calc.exceptions.BadRequestException

enum class OperationType(val type: String) {
    addition("+") {
        override fun evaluate(a: Float, b: Float): Float = a + b
                  },
    subtraction("-") {
        override fun evaluate(a: Float, b: Float): Float = a - b
                     },
    multiplication("*") {
        override fun evaluate(a: Float, b: Float): Float = a * b
                        },
    division("/") {
        override fun evaluate(a: Float, b: Float): Float = if ( b != 0F ) a/b
            else throw BadRequestException("You can not divide number by 0")
    };

    abstract fun evaluate(a: Float, b: Float): Float
}