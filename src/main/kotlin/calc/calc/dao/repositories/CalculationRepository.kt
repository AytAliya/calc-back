package calc.calc.dao.repositories

import calc.calc.dao.entities.CalculationEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CalculationRepository : JpaRepository<CalculationEntity, UUID>