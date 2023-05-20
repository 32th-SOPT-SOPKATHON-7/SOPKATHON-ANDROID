package com.solosol.a32th_sopt_sopkathon_7_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.solosol.a32th_sopt_sopkathon_7_android.base.BaseViewBindingActivity
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ActivityCommentBinding
import com.solosol.a32th_sopt_sopkathon_7_android.extension.showToast
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant


class MainActivity : BaseViewBindingActivity<ActivityCommentBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** setContentView 하지 말기 **/
        showToast("화이팅")

        /*val    now: Instant = Clock.System.now()
        Log.d("time", now.toString())*/
    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivityCommentBinding {
        return ActivityCommentBinding.inflate(layoutInflater)
    }
}