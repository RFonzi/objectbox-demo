package io.github.rfonzi.objectboxdemo.entities

import io.objectbox.converter.PropertyConverter

/**
 * Created by ryan on 11/17/17.
 */

sealed class Gender(val value: String) {
    override fun toString() = value

    companion object {
        val UNKNOWN_STRING = "Unknown"
        val MALE_STRING = "Male"
        val FEMALE_STRING = "Female"

        @JvmStatic
        fun getGenderOf(value: String): Gender = when(value) {
            MALE_STRING -> Male()
            FEMALE_STRING -> Female()
            else -> Unknown()
        }
    }
}

// Can't put in sealed class Gender for some reason
class GenderConverter : PropertyConverter<Gender, String> {
    override fun convertToEntityProperty(databaseValue: String?): Gender {
        return Gender.getGenderOf(databaseValue ?: Gender.UNKNOWN_STRING)
    }

    override fun convertToDatabaseValue(entityProperty: Gender?): String {
        return entityProperty?.value ?: Gender.UNKNOWN_STRING
    }

}

class Unknown : Gender(UNKNOWN_STRING)

class Male : Gender(MALE_STRING)

class Female : Gender(FEMALE_STRING)

