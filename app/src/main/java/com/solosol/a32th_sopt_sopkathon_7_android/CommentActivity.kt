package com.solosol.a32th_sopt_sopkathon_7_android


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.solosol.a32th_sopt_sopkathon_7_android.api.ApiFactory.soptService
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.request.CreateCommentRequest
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.response.DetailArticleResponse
import com.solosol.a32th_sopt_sopkathon_7_android.base.BaseViewBindingActivity
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ActivityCommentBinding
import com.solosol.a32th_sopt_sopkathon_7_android.extension.showToast
import com.solosol.a32th_sopt_sopkathon_7_android.sample.CommentAdapter
import kotlinx.coroutines.launch


class CommentActivity : BaseViewBindingActivity<ActivityCommentBinding>() {
    val postId: Int = 1
    val commentAdapter = CommentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hidekeyboard()

        binding.btReport.setOnClickListener {
            showToast("신고완료")
        }

        lifecycleScope.launch {
            val response = soptService.getDetailArticle(postId)
            if (response.isSuccessful) {
                binding.tvTitlePost.text = response.body()?.data?.title
                binding.tvPostTime.text = response.body()?.data?.createdAt?.substring(0 until 10)
                binding.tvArticle.text = response.body()?.data?.content
                val commentList: List<DetailArticleResponse.Data.Comment?>? =
                    response.body()?.data?.comments
                commentAdapter.submitList(commentList)
            } else {
                showToast("서버통신 실패")
            }
        }

        lifecycleScope.launch {
            val response = soptService.getDetailArticle(postId)
            val comment_response = soptService.postCreateComment(postId, CreateCommentRequest(binding.etWriteComment.text.toString()))
            if (comment_response.isSuccessful){
                soptService.getDetailArticle(postId)
                val commentList: List<DetailArticleResponse.Data.Comment?>? =
                    response.body()?.data?.comments
                commentAdapter.submitList(commentList)
            }
            else{
                showToast("서버통신 실패")
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

    private fun closeBtnClickListener() {
        binding.btBack.setOnClickListener {
            this.finish()
//            val intent = Intent(this, SubwaySearchActivity::class.java).apply {
//                putExtra("stationName", binding.tvPostStationName.text.toString())
//            }
//            startActivity(intent)
        }
    }
}