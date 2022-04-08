package com.dicoding.picodiploma.submission2_ichsan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.picodiploma.submission2_ichsan.databinding.ActivityDetailUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder


class DetailUserActivity  : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_URL = "extra_url"
    }
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel : DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail User"

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatarUrl = intent.getStringExtra(EXTRA_URL)
        val mBundle = Bundle() //bundle berfungsi sebagai perpindahan fragment dengan membawa data
        mBundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)

        viewModel.setUserDetail(username)
        viewModel.getUserDetail().observe(this) {
            if (it != null) {
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = " Followers : ${it.followers}"
                    tvFollowing.text = " Following : ${it.following}"
                    tvRepost.text = " Repository : ${it.public_repos}"
                    tvComp.text = " Company : ${it.company}"
                    tvLink.text = " GitHub : ${it.html_url}"
                    tvLoc.text = " Location : ${it.location}"
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivPicture)
                }

            }
        }
        //untuk menampung state dari toggle
        var _isChecked =false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.CheckUser(id)
            withContext(Dispatchers.Main){
                if(count!=null){
                    if (count>0){
                        binding.tglFavorite.isChecked = true
                        _isChecked = true
                    }else{
                        binding.tglFavorite.isChecked = false
                        _isChecked = false
                    }
                }
            }
        }
        //untuk merubah aksi jika diklik
        binding.tglFavorite.setOnClickListener{
            _isChecked = !_isChecked
            if (_isChecked){
            viewModel.addToFavorite(username!!,id, avatarUrl!!)
            }else{
                viewModel.removeFromFavorite(id)
            }
            binding.tglFavorite.isChecked = _isChecked
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, mBundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tab.setupWithViewPager(viewPager)
        }
    }
}