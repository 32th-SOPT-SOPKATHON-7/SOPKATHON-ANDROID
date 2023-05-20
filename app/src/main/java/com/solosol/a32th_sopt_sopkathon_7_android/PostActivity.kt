package com.solosol.a32th_sopt_sopkathon_7_android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.lifecycleScope
import com.solosol.a32th_sopt_sopkathon_7_android.api.ApiFactory.soptService
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.request.CreateArticleRequest
import com.solosol.a32th_sopt_sopkathon_7_android.base.BaseViewBindingActivity
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ActivityPostBinding
import com.solosol.a32th_sopt_sopkathon_7_android.extension.showToast
import kotlinx.coroutines.launch

class PostActivity : BaseViewBindingActivity<ActivityPostBinding>() {

    override fun setBinding(layoutInflater: LayoutInflater): ActivityPostBinding {
        return ActivityPostBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        closeBtnClickListener()
        checkBtnClickListener()
        hidekeyboard()
    }

    private fun closeBtnClickListener() {
        binding.ibPostTopAppBarClose.setOnClickListener {
            this.finish()
//            val intent = Intent(this, SubwaySearchActivity::class.java).apply {
//                putExtra("stationName", binding.tvPostStationName.text.toString())
//            }
//            startActivity(intent)
        }
    }

    private fun checkBtnClickListener() {
        binding.ibPostTopAppBarCheck.setOnClickListener {
            lifecycleScope.launch {
                val response = soptService.postCreateArticle(
                    with(binding) {
                        CreateArticleRequest(
                            etPostContent.text.toString(),
                            intent.getStringExtra("stationName").toString(),
                            etPostTitle.text.toString()
                        )
                    }
                )
                if (response.isSuccessful) {
                    response.body()?.message?.let { showToast(it) }
                        ?: getString(R.string.post_writing_success).also { showToast(it) }
                } else {
                    response.body()?.message?.let { showToast(it) }
                        ?: getString(R.string.post_server_communication_fail).also { showToast(it) }
                }

            }
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