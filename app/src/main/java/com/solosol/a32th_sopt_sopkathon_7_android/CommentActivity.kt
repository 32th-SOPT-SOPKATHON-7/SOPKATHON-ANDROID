package com.solosol.a32th_sopt_sopkathon_7_android


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.solosol.a32th_sopt_sopkathon_7_android.api.ApiFactory.soptService
import com.solosol.a32th_sopt_sopkathon_7_android.base.BaseViewBindingActivity
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ActivityCommentBinding
import com.solosol.a32th_sopt_sopkathon_7_android.extension.showToast
import com.solosol.a32th_sopt_sopkathon_7_android.sample.CommentAdapter
import kotlinx.coroutines.launch


class CommentActivity: BaseViewBindingActivity<ActivityCommentBinding>() {

    private val commentAdapter = CommentAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hidekeyboard()

        binding.btReport.setOnClickListener{
            showToast("신고 완료")
        }
        val postId = intent.getIntExtra("postId", 0)
        binding.rvComment.apply {
            adapter = commentAdapter
            layoutManager = LinearLayoutManager(this@CommentActivity)
        }

        lifecycleScope.launch {
            val response = soptService.getDetailArticle(postId)
            if (response.isSuccessful) {
                binding.tvTitlePost.text = response.body()?.data?.title ?:""
                binding.tvArticle.text = response.body()?.data?.content ?:""
                commentAdapter.submitList(response.body()?.data?.comments)

            } else {
                showToast("에러 발생")
            }
        }

    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivityCommentBinding {
        return ActivityCommentBinding.inflate(layoutInflater)
    }

    private fun hidekeyboard() {
        val imm: InputMethodManager =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            this.currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}