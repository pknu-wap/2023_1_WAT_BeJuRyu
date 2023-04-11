package com.jaino.setting.account

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jaino.data.repository.auth.SocialAuthRepository
import com.jaino.data.repository.user.LocalUserRepository
import com.jaino.setting.R
import com.jaino.setting.SettingActivity
import com.jaino.setting.databinding.ActivityAccountBinding
import com.jakewharton.processphoenix.ProcessPhoenix
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AccountActivity : AppCompatActivity() {

    @Inject
    lateinit var socialAuth : SocialAuthRepository

    @Inject
    lateinit var localData : LocalUserRepository

    private var _binding: ActivityAccountBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "binding object is not initialized" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_account)
        binding.lifecycleOwner = this
        initButtons()
    }

    private fun initButtons(){
        binding.accountBackButton.setOnClickListener {
            navigateToSetting()
        }

        binding.deleteAccountCardView.setOnClickListener {
            ConfirmDialog(
                this,
                "계정을 삭제하시겠습니까?",
                onDoneButtonClick = {
                    socialAuth.unlink()
                    localData.clear()
                    ProcessPhoenix.triggerRebirth(this)
                }
            ).show()
        }

        binding.signOutCardView.setOnClickListener {
            ConfirmDialog(
                this,
                "로그아웃을 하시겠습니까?",
                onDoneButtonClick = {
                    socialAuth.unlink()
                    localData.clear()
                    ProcessPhoenix.triggerRebirth(this)
                }
            ).show()
        }
    }

    private fun navigateToSetting(){
        startActivity(Intent(this, SettingActivity::class.java))
    }
}