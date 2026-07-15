package com.automation.workflow.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: String?): List<String>? {
        if (value == null) return null
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toStringList(list: List<String>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromMapString(value: String?): Map<String, Any>? {
        if (value == null) return null
        val mapType = object : TypeToken<Map<String, Any>>() {}.type
        return gson.fromJson(value, mapType)
    }

    @TypeConverter
    fun toMapString(map: Map<String, Any>?): String? {
        return gson.toJson(map)
    }

    @TypeConverter
    fun fromJson(value: String?): Any? {
        if (value == null) return null
        return try {
            gson.fromJson(value, Any::class.java)
        } catch (e: Exception) {
            value
        }
    }

    @TypeConverter
    fun toJson(obj: Any?): String? {
        return gson.toJson(obj)
    }
}
