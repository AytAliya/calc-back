package calc.calc.mappers

import calc.calc.dao.entities.CalculationEntity
import calc.calc.dao.entities.OperationEntity
import calc.calc.enums.OperationType
import calc.calc.models.Operation
import calc.calc.models.requests.OperationRequest

fun OperationEntity.toDto(): Operation {
    return Operation(
            name = name,
            type = type,
            operandA = operandA,
            operandB = operandB
    )
}

fun OperationRequest.toDao(result: Float, type: OperationType, calculationEntity: CalculationEntity): OperationEntity {
    return OperationEntity(
            name = name,
            operandA = operandA,
            operandB = operandB,
            result = result,
            calculation = calculationEntity,
            type = type
    )
}