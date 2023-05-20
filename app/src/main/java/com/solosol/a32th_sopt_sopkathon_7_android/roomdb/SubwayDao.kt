package com.solosol.a32th_sopt_sopkathon_7_android.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SubwayDao {
    @Insert
    suspend fun insert(subwayData:SubwayData)

    @Delete
    suspend fun delete(subwayData:SubwayData)

    @Query("SELECT * FROM SubwayData")
    suspend fun selectAllSubway() : MutableList<SubwayData>

    @Query("SELECT * FROM SubwayData WHERE name=:name")
    suspend fun selectOneSubway(name:String) : SubwayData
}