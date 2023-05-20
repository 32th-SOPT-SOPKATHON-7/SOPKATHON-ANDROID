package com.solosol.a32th_sopt_sopkathon_7_android

import android.view.LayoutInflater
import com.solosol.a32th_sopt_sopkathon_7_android.base.BaseViewBindingActivity
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ActivityPostBinding

class PostActivity : BaseViewBindingActivity<ActivityPostBinding>() {
    //private val postService = PostServicePool.PostService

    override fun setBinding(layoutInflater: LayoutInflater): ActivityPostBinding {
        return ActivityPostBinding.inflate(layoutInflater)
    }

//    private fun completeBtnClickListener() {
//        binding.ibPostTopAppBarSubmit.setOnClickListener {
//            postService.post(
//                with(binding) {
//                    RequestPostDto(
//                        etPostTitle.text.toString(),
//                        etPostContent.text.toString()
//                    )
//                }
//            ).enqueue(object : retrofit2.Callback<ResponsePostDto> {
//                override fun onResponse(
//                    call: Call<ResponsePostDto>,
//                    response: Response<ResponsePostDto>
//                ) {
//                    if (response.isSuccessful) {
//                        response.body()?.message?.let { showToast(it) }
//                            ?: getString(R.string.post_writing_success).also { showToast(it) }
//                    } else {
//                        response.body()?.message?.let { showToast(it) }
//                            ?: getString(R.string.post_writing_fail).also { showToast(it) }
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponsePostDto>, t: Throwable) {
//                    t.message?.let { showToast(it) }
//                        ?: getString(R.string.post_server_communication_fail).also { showToast(it) }
//                }
//            })
//        }
//    }
}