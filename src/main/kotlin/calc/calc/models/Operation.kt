package calc.calc.models

import calc.calc.enums.OperationType

class Operation (
        val name: String,
        val type: OperationType,
        val operandA: String,
        val operandB: String
)