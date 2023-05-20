package com.solosol.a32th_sopt_sopkathon_7_android.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SubwayData")
data class SubwayData(
    @ColumnInfo(name="line") val line:String?,
    @ColumnInfo(name="name") val name:String?,
    @PrimaryKey @ColumnInfo(name="code") val code: Int?,
    @ColumnInfo(name="lat") val lat: Double?,
    @ColumnInfo(name="lng") val lng:Double?
)
