package calc.calc.models

import java.util.*

class Calculation (
        var id: UUID? = null,
        val name: String,
        val description: String,
        val variables: List<Variable>?,
        val operations: List<Operation>?
)