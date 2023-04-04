package com.jaino.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.jaino.auth.databinding.ActivityAuthBinding
import com.jaino.data.repository.auth.SocialAuthRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    @Inject
    lateinit var socialAuthRepository: SocialAuthRepository

    private var _binding: ActivityAuthBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
        binding.lifecycleOwner = this
        initButtons()
    }

    private fun initButtons(){
        binding.kakaoSignInButton.setOnClickListener{
            if(socialAuthRepository.isKakaoTalkLoginAvailable){ // KakaoTalk 실행 가능
                lifecycleScope.launch {
                    socialAuthRepository.signInByKakaoTalk()
                        .onSuccess {  }
                        .onFailure {  }
                }
            }
            else{
                lifecycleScope.launch {
                    socialAuthRepository.signInByKakaoAccount()
                        .onSuccess {  }
                        .onFailure {  }
                }
            }
        }
    }
}
