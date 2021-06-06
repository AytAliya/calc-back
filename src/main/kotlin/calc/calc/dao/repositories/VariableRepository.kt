package calc.calc.dao.repositories

import calc.calc.dao.entities.VariableEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface VariableRepository : JpaRepository<VariableEntity, UUID> {

    fun findVariableEntityByCalculationIdAndName(calculationId: UUID, name: String) : Optional<VariableEntity>

    @Transactional
    fun deleteVariableEntityByCalculationIdAndName(calculationId: UUID, name: String)
}