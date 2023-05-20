package com.solosol.a32th_sopt_sopkathon_7_android.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SubwayDao {
    @Insert
    fun insert(subwayData:SubwayData)

    @Delete
    fun delete(subwayData:SubwayData)

    @Query("SELECT * FROM SubwayData")
    fun selectAllSubway() : MutableList<SubwayData>

    @Query("SELECT * FROM SubwayData WHERE name=:name")
    fun selectOneSubway(name:String) : SubwayData?
}