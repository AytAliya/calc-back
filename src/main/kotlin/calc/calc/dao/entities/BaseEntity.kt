package calc.calc.dao.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity(givenId: UUID? = null) {

    @Id
    @Column(name = "id", length = 16, unique = true, nullable = false)
    internal val id: UUID = givenId ?: UUID.randomUUID()

    @field:CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    val createdDate: Instant = Instant.now()

    @field:UpdateTimestamp
    @Column(name = "modified_date", nullable = false)
    val modifiedDate: Instant = Instant.now()
}