package com.solosol.a32th_sopt_sopkathon_7_android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.solosol.a32th_sopt_sopkathon_7_android.base.BaseViewBindingActivity
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ActivitySearchBinding
import com.solosol.a32th_sopt_sopkathon_7_android.roomdb.AppDatabase
import com.solosol.a32th_sopt_sopkathon_7_android.roomdb.SubwayData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubwaySearchActivity: BaseViewBindingActivity<ActivitySearchBinding> (){

    private lateinit var roomDB : AppDatabase
    private val dataList:ArrayList<SubwayData> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        roomDB = AppDatabase.getInstance(this@SubwaySearchActivity)!!
        //setData() -> data set은 한번만 하면됨 마지막 더미데이터 잘 넣고 set하면 될듯 함.
        getData()
    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivitySearchBinding {
       return ActivitySearchBinding.inflate(layoutInflater)
    }

    private fun setData(){
        dataList.apply{
            roomDB.subwayDao().insert((SubwayData("01호선","녹양",1908,37.75938,127.042292)))
            roomDB.subwayDao().insert((SubwayData("01호선","남영",1002,37.541021,126.9713)))
            roomDB.subwayDao().insert((SubwayData("01호선","용산",1003,37.529849	,126.964561)))
            roomDB.subwayDao().insert((SubwayData("01호선","노량진",1004	,37.514219,126.942454)))
        }
    }

    private fun getData(){
        lifecycleScope.launch(Dispatchers.IO) {
            Log.e("data" ,roomDB.subwayDao().selectAllSubway().toString() ) // get한거 보여주는 log 찍은거.
        }
    }
}