package com.solosol.a32th_sopt_sopkathon_7_android


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import androidx.core.content.getSystemService
import androidx.lifecycle.lifecycleScope
import com.solosol.a32th_sopt_sopkathon_7_android.api.ApiFactory.soptService
import com.solosol.a32th_sopt_sopkathon_7_android.api.SoptService
import com.solosol.a32th_sopt_sopkathon_7_android.base.BaseViewBindingActivity
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ActivityCommentBinding
import com.solosol.a32th_sopt_sopkathon_7_android.extension.showToast
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant


class CActivity : BaseViewBindingActivity<ActivityCommentBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hidekeyboard()

        binding.btReport.setOnClickListener(
            showToast("게시글이 신고되었습니다.")
        )

        lifecycleScope.launch {
            val response = soptService.getDetailArticle(postId)
            if (response.isSuccessful) {
                binding.tvTitlePost.text = response.body().data?.
            } else {

            }
        }

    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivityCommentBinding {
        return ActivityCommentBinding.inflate(layoutInflater)
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