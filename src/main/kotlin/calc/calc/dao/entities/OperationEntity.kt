package calc.calc.dao.entities

import calc.calc.enums.OperationType
import com.fasterxml.jackson.annotation.JsonBackReference
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "operations")
class OperationEntity(

        id: UUID? = null,

        @Column(name = "name", nullable = false)
        val name: String,

        @Enumerated(EnumType.STRING)
        val type: OperationType,

        @Column(name = "operand_a", nullable = false)
        val operandA: String,

        @Column(name = "operand_b", nullable = false)
        val operandB: String,

        @Column(name = "result")
        val result: Float?,

        @JsonBackReference
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "calculation_id")
        val calculation: CalculationEntity

) : BaseEntity(id) {
        fun copy(
                id: UUID? = this.id,
                name: String = this.name,
                type: OperationType = this.type,
                operandA: String = this.operandA,
                operandB: String = this.operandB,
                result: Float? = this.result,
                calculation: CalculationEntity = this.calculation
        ) = OperationEntity (
                id,
                name,
                type,
                operandA,
                operandB,
                result,
                calculation
                )
}