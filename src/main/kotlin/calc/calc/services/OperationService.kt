package calc.calc.services

import calc.calc.dao.entities.CalculationEntity
import calc.calc.dao.entities.OperationEntity
import calc.calc.dao.repositories.OperationRepository
import calc.calc.enums.OperationType
import calc.calc.exceptions.BadRequestException
import calc.calc.mappers.toDao
import calc.calc.models.requests.OperationRequest
import org.springframework.stereotype.Service
import java.util.*

interface OperationService {
    fun findByCalcIdAndName(calculationId: UUID, varName: String): OperationEntity?
    fun create(operation: OperationRequest, type: OperationType, result: Float, calculation: CalculationEntity)
    fun update(newOperation: OperationRequest, result: Float, operationEntity: OperationEntity)
    fun delete(calculationId: UUID, operationName: String)
    fun getCalculationValue(calculationId: UUID): Float
}

@Service
class OperationServiceImpl(
        private val operationRepository: OperationRepository
) : OperationService {

    override fun findByCalcIdAndName(calculationId: UUID, varName: String): OperationEntity? {
        val operationEntity = operationRepository.findOperationEntityByCalculationIdAndName(calculationId, varName)
        return if (operationEntity.isPresent) {
            operationEntity.get()
        } else return null
    }

    override fun create(operation: OperationRequest, type: OperationType, result: Float, calculation: CalculationEntity) {
        operationRepository.save(operation.toDao(result, type, calculation))
    }

    override fun update(newOperation: OperationRequest, result: Float, operationEntity: OperationEntity) {
        operationRepository.save(operationEntity.copy(
                operandA = newOperation.operandA,
                operandB = newOperation.operandB,
                result = result
        ))
    }

    override fun delete(calculationId: UUID, operationName: String) {
        operationRepository.deleteOperationEntityByCalculationIdAndName(calculationId, operationName)
    }

    override fun getCalculationValue(calculationId: UUID): Float {
        val operationEntity = operationRepository.findLatestOperationByCalcId(calculationId)
        return operationEntity.result ?: throw BadRequestException("Not enough values to evaluate operation result. \n" +
                " Check the values of all your variables")
    }

}