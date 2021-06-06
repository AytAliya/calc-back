package calc.calc.dao.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "variables")
class VariableEntity(

        id: UUID? = null,

        @Column(name = "name", nullable = false)
        val name: String,

        @Column(name = "value", nullable = false)
        val value: Float,

        @JsonBackReference
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "calculation_id")
        val calculation: CalculationEntity

) : BaseEntity(id) {

        fun copy(
                id: UUID? = this.id,
                name: String = this.name,
                value: Float = this.value,
                calculation: CalculationEntity = this.calculation
        ) = VariableEntity(
                id,
                name,
                value,
                calculation
        )
}