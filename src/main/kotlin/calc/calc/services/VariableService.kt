package calc.calc.services

import calc.calc.dao.entities.CalculationEntity
import calc.calc.dao.entities.VariableEntity
import calc.calc.dao.repositories.VariableRepository
import calc.calc.mappers.toDao
import calc.calc.models.Variable
import org.springframework.stereotype.Service
import java.util.*

interface VariableService {
    fun findByCalcIdAndName(calcId: UUID, varName: String): VariableEntity?
    fun create(variable: Variable, calculation: CalculationEntity)
    fun update(newVariable: Variable, variableEntity: VariableEntity)
    fun delete(calculationId: UUID, variableName: String)
}

@Service
class VariableServiceImpl(
        private val variableRepository: VariableRepository
) : VariableService {

    override fun findByCalcIdAndName(calcId: UUID, varName: String): VariableEntity? {
        val variableEntity = variableRepository.findVariableEntityByCalculationIdAndName(calcId, varName)
        return if (variableEntity.isPresent) {
            variableEntity.get()
        } else return null
    }

    override fun create(variable: Variable, calculation: CalculationEntity) {
        variableRepository.save(variable.toDao(calculation))
    }

    override fun update(newVariable: Variable, variableEntity: VariableEntity) {
        variableRepository.save(variableEntity.copy(
            value = newVariable.value
        ))
    }

    override fun delete(calculationId: UUID, variableName: String) {
        variableRepository.deleteVariableEntityByCalculationIdAndName(calculationId, variableName)
    }

}