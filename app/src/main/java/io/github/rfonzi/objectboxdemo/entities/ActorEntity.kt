package io.github.rfonzi.objectboxdemo.entities

import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by ryan on 11/17/17.
 */

@Entity
data class ActorEntity(
        @Id var id: Long = 0,
        var name: String? = null,
        var age: String? = null,
        @Convert(converter = GenderConverter::class, dbType = String::class)
        var gender: Gender = Unknown()
) {
    override fun equals(other: Any?): Boolean {
        val otherActor = other as ActorEntity

        return name == otherActor.name
                && age == otherActor.age
                && gender.value == otherActor.gender.value
                && id == otherActor.id
    }
}