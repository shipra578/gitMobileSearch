package com.shipra.android.gitmobilesearch.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OwnerTypeConverter{


    private val gson = Gson()

    @TypeConverter
    fun stringToOwner(data: String?): Owner? {
        if (data == null) {
            Owner(0,"","","")
        }
        val type = object : TypeToken<Owner?>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun OwnerToString(ownerObj: Owner?): String? {
        return gson.toJson(ownerObj)
    }
}