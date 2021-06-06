package calc.calc.services

import calc.calc.dao.entities.CalculationEntity
import calc.calc.dao.repositories.CalculationRepository
import calc.calc.enums.OperationType
import calc.calc.exceptions.BadRequestException
import calc.calc.exceptions.ResourceNotFoundException
import calc.calc.mappers.toCreateDto
import calc.calc.mappers.toDao
import calc.calc.mappers.toDto
import calc.calc.models.Calculation
import calc.calc.models.Variable
import calc.calc.models.requests.CalculationCreateRequest
import calc.calc.models.requests.OperationDeleteRequest
import calc.calc.models.requests.OperationRequest
import calc.calc.models.requests.VariableDeleteRequest
import calc.calc.models.responses.CalculationCreateResponse
import calc.calc.models.responses.CalculationValueReponse
import org.springframework.stereotype.Service
import java.util.*

interface CalculationService {
    fun getAll() : List<Calculation>
    fun create(request: CalculationCreateRequest) : CalculationCreateResponse
    fun deleteById(id: UUID)
    fun addUpdateVariable(calculationId: UUID, variable: Variable)
    fun deleteVariable(calculationId: UUID, deleteVariable: VariableDeleteRequest)
    fun addUpdateOperation(calculationId: UUID, operationType: OperationType, operation: OperationRequest)
    fun deleteOperation(calculationId: UUID, deleteOperation: OperationDeleteRequest)
    fun getCalculationResult(calculationId: UUID) : CalculationValueReponse
}

@Service
class CalculationServiceImpl(
        private val calculationRepository: CalculationRepository,
        private val variableService: VariableService,
        private val operationService: OperationService
) : CalculationService {

    override fun getAll(): List<Calculation> {
        return calculationRepository.findAll().map { it.toDto() }
    }

    override fun create(request: CalculationCreateRequest): CalculationCreateResponse {
        return calculationRepository.save(request.toDao()).toCreateDto()
    }

    override fun deleteById(id: UUID) {
        calculationRepository.deleteById(id)
    }

    override fun addUpdateVariable(calculationId: UUID, variable: Variable) {
        val variableEntity = variableService.findByCalcIdAndName(calculationId, variable.name)
        if (variableEntity != null) {
            variableService.update(variable, variableEntity)
        } else {
            val calculationEntity = findCalculationById(calculationId)
            variableService.create(variable, calculationEntity)
        }
    }

    override fun deleteVariable(calculationId: UUID, deleteVariable: VariableDeleteRequest) {
        variableService.delete(calculationId, deleteVariable.name)
    }

    override fun addUpdateOperation(calculationId: UUID, operationType: OperationType, operation: OperationRequest) {
        val var1 = getValue(calculationId, operation.operandA)
        val var2 = getValue(calculationId, operation.operandB)
        val result = operationType.evaluate(var1, var2)
        val operationEntity = operationService.findByCalcIdAndName(calculationId, operation.name)
        if (operationEntity != null) {
            operationService.update(operation, result, operationEntity)
        } else {
            val calculationEntity = findCalculationById(calculationId)
            operationService.create(operation, operationType, result, calculationEntity)
        }
    }

    override fun deleteOperation(calculationId: UUID, deleteOperation: OperationDeleteRequest) {
        operationService.delete(calculationId, deleteOperation.name)
    }

    override fun getCalculationResult(calculationId: UUID): CalculationValueReponse {
        val calculationEntity = findCalculationById(calculationId)
        val result = operationService.getCalculationValue(calculationId)
        return CalculationValueReponse(result, calculationEntity.description)
    }

    private fun findCalculationById(calculationId: UUID) : CalculationEntity {
        return calculationRepository.findById(calculationId).orElseThrow {
            ResourceNotFoundException("Calculation with id $calculationId not found")
        }
    }

    private fun getValue(calculationId: UUID, varName: String) : Float {
        val variableEntity = variableService.findByCalcIdAndName(calculationId, varName)
        if (variableEntity == null) {
            val operationEntity = operationService.findByCalcIdAndName(calculationId, varName)
                    ?: throw ResourceNotFoundException("No variable or operation with name $varName is found")
            return operationEntity.result ?: throw BadRequestException("Not enough values to evaluate operation result. \n Check the values of all your variables")
        }
        return variableEntity.value
    }
}