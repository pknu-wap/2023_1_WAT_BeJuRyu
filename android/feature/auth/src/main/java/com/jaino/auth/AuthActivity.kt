package com.jaino.auth

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
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

    private val viewModel : AuthViewModel by viewModels()

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
                        .onSuccess {
                            viewModel.executeServiceSignIn(it.token)
                        }
                        .onFailure {
                            Toast.makeText(this@AuthActivity,
                                "카카오톡 로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            else{
                lifecycleScope.launch {
                    socialAuthRepository.signInByKakaoAccount()
                        .onSuccess {
                            viewModel.executeServiceSignIn(it.token)
                        }
                        .onFailure {
                            Toast.makeText(this@AuthActivity,
                                "카카오 계정 로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }
    }
}
