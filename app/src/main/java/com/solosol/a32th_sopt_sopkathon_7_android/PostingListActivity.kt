package com.solosol.a32th_sopt_sopkathon_7_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.google.android.material.tabs.TabLayoutMediator
import com.solosol.a32th_sopt_sopkathon_7_android.base.BaseViewBindingActivity
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ActivityPostingListBinding

class PostingListActivity : BaseViewBindingActivity<ActivityPostingListBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val subwayName = intent.getStringExtra("subway_name")
        Log.d("subwayName", subwayName.toString())
        binding.tvSubwayName.text = subwayName + "역"
        val postVPAdapter = ViewPagerAdapter(subwayName.toString(), this)
        binding.viewPager.adapter = postVPAdapter
        postVPAdapter
        val tabTitleArray = arrayOf(
            "트렌딩", "최신"
        )
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btAdd.setOnClickListener {
            startActivity(Intent(this, PostActivity::class.java).apply {
                putExtra("subwayName", subwayName)
            })
        }
    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivityPostingListBinding {
        return ActivityPostingListBinding.inflate(layoutInflater)
    }

    private fun getSubwayName() {

    }
}