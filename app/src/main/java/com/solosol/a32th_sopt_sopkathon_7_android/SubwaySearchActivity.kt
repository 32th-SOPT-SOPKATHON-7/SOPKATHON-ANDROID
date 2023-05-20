package com.solosol.a32th_sopt_sopkathon_7_android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.solosol.a32th_sopt_sopkathon_7_android.base.BaseViewBindingActivity
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ActivitySearchBinding
import com.solosol.a32th_sopt_sopkathon_7_android.extension.showToast
import com.solosol.a32th_sopt_sopkathon_7_android.roomdb.AppDatabase
import com.solosol.a32th_sopt_sopkathon_7_android.roomdb.SubwayData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class SubwaySearchActivity: BaseViewBindingActivity<ActivitySearchBinding>(){

    private lateinit var roomDB : AppDatabase
    private val dataList:ArrayList<SubwayData> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        roomDB = AppDatabase.getInstance(this@SubwaySearchActivity)!!
        getSubwayData()
        hidekeyboard()
    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivitySearchBinding {
       return ActivitySearchBinding.inflate(layoutInflater)
    }

    private fun getSubwayData() {
        with(binding) {
            btnSearch.setOnClickListener {
                if (!etSearch.text.isEmpty()) {
                    lifecycleScope.launch(Dispatchers.IO) {
                       val subwayTrue =  roomDB.subwayDao().selectOneSubway(etSearch.text.toString())
                        if (subwayTrue == null) {
                            Log.e("지하철 존재", "하지않음")
                        }
                        else{
                            Log.e("지하철 존재", "함")
                            Log.e("지하철 정보:" ,subwayTrue.toString())
                            val intent = Intent(this@SubwaySearchActivity,PostingListActivity::class.java)
                            intent.putExtra("subway_name", etSearch.text.toString())
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }

    private fun getData(){
        lifecycleScope.launch(Dispatchers.IO) {
            Log.e("data" ,roomDB.subwayDao().selectAllSubway().toString() ) // get한거 보여주는 log 찍은거.
        }
    }

    private fun hidekeyboard() {
        if (this != null) {
            val imm: InputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(
                this.currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}