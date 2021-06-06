package calc.calc.mappers

import calc.calc.dao.entities.CalculationEntity
import calc.calc.models.Calculation
import calc.calc.models.requests.CalculationCreateRequest
import calc.calc.models.responses.CalculationCreateResponse

fun CalculationEntity.toDto(): Calculation {
    return Calculation(
            id = id,
            name = name,
            description = description,
            variables = variables?.map { it.toDto() },
            operations = operations?.map { it.toDto() }
    )
}

fun CalculationCreateRequest.toDao(): CalculationEntity {
    return CalculationEntity(
            name = name,
            description = description
    )
}

fun CalculationEntity.toCreateDto(): CalculationCreateResponse {
    return CalculationCreateResponse(
            id = id
    )
}