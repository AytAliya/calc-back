package calc.calc.mappers

import calc.calc.dao.entities.CalculationEntity
import calc.calc.dao.entities.VariableEntity
import calc.calc.models.Variable

fun VariableEntity.toDto(): Variable {
    return Variable(
            name = name,
            value = value
    )
}

fun Variable.toDao(calculationEntity: CalculationEntity): VariableEntity {
    return VariableEntity(
            name = this.name,
            value = this.value,
            calculation = calculationEntity
    )
}