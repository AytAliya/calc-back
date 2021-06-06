package calc.calc.controllers

import calc.calc.enums.OperationType
import calc.calc.models.Calculation
import calc.calc.models.Variable
import calc.calc.models.requests.CalculationCreateRequest
import calc.calc.models.requests.OperationDeleteRequest
import calc.calc.models.requests.OperationRequest
import calc.calc.models.requests.VariableDeleteRequest
import calc.calc.models.responses.CalculationCreateResponse
import calc.calc.models.responses.CalculationValueReponse
import calc.calc.services.CalculationService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/calculations")
class CalculationController(
        private val calculationService: CalculationService
) {

    @GetMapping
    fun getAllCalculations(): List<Calculation> {
        return calculationService.getAll()
    }

    @PostMapping
    fun create(
            @RequestBody calculation: CalculationCreateRequest
    ): CalculationCreateResponse {
        return calculationService.create(calculation)
    }

    @DeleteMapping("{id}")
    fun delete(
            @PathVariable id: UUID
    ) {
        calculationService.deleteById(id)
    }

    @PutMapping("{calculation_id}/variables")
    fun createUpdateVariables(
            @PathVariable calculation_id: UUID,
            @RequestBody variable: Variable
    ) {
        calculationService.addUpdateVariable(calculation_id, variable)
    }

    @DeleteMapping("{calculation_id}/variables")
    fun deleteVariables(
            @PathVariable calculation_id: UUID,
            @RequestBody variable: VariableDeleteRequest
    ) {
        calculationService.deleteVariable(calculation_id, variable)
    }

    @PutMapping("{calculation_id}/operations/{operation_type}")
    fun createUpdateOperations(
            @PathVariable calculation_id: UUID,
            @PathVariable operation_type: OperationType,
            @RequestBody operation: OperationRequest
    ) {
        calculationService.addUpdateOperation(calculation_id, operation_type, operation)
    }

    @DeleteMapping("{calculation_id}/operations")
    fun deleteOperations(
            @PathVariable calculation_id: UUID,
            @RequestBody operation: OperationDeleteRequest
    ) {
        calculationService.deleteOperation(calculation_id, operation)
    }

    @GetMapping("{calculation_id}/value")
    fun getResult(
            @PathVariable calculation_id: UUID
    ): CalculationValueReponse {
        return calculationService.getCalculationResult(calculation_id)
    }
}