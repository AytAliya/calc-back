package calc.calc.dao.repositories

import calc.calc.dao.entities.OperationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface OperationRepository : JpaRepository<OperationEntity, UUID> {

    fun findOperationEntityByCalculationIdAndName(calculationId: UUID, name: String) : Optional<OperationEntity>

    @Transactional
    fun deleteOperationEntityByCalculationIdAndName(calculationId: UUID, name: String)

    @Query("select * from operations op where op.calculation_id = :calculationId order by op.created_date desc limit 1", nativeQuery = true)
    fun findLatestOperationByCalcId(calculationId: UUID): OperationEntity
}