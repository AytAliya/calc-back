package calc.calc.dao.entities

import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "calculations")
class CalculationEntity(

        id: UUID? = null,

        @Column(name = "name", nullable = false)
        val name: String,

        @Column(name = "description", nullable = false)
        val description: String,

        @OneToMany(mappedBy="calculation", cascade = [CascadeType.ALL], orphanRemoval = true)
        var variables: List<VariableEntity>? = listOf(),

        @OneToMany(mappedBy="calculation", cascade = [CascadeType.ALL], orphanRemoval = true)
        var operations: List<OperationEntity>? = listOf()

) : BaseEntity(id)