package com.lx.project5

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.lx.api.BasicClient
import com.lx.data.MemberListResponse
import com.lx.project5.databinding.FragmentLoginBinding
import com.lx.project5.databinding.FragmentMyPageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {
    var _binding: FragmentLoginBinding? = null
    val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.register.setOnClickListener {
            val curActivity = activity as MainActivity
            curActivity.onFragmentChanged(MainActivity.ScreenItem.ITEMjoin1)
        }

        binding.loginButton.setOnClickListener {
            readMember()
        }

        return binding.root
    }

    fun readMember() {
        val memberId = binding.loginId.text.toString()
        val memberPw = binding.loginPassword.text.toString()

        BasicClient.api.postMemberLogin(
            requestCode = "1001",
            memberId = memberId,
            memberPw = memberPw
        ).enqueue(object : Callback<MemberListResponse> {
            override fun onResponse(call: Call<MemberListResponse>, response: Response<MemberListResponse>) {
                val checkMember = response.body()?.header?.total.toString()
                println(checkMember)

                if(checkMember == "1"){
                    AppData.userdata="${response.body()?.data.toString()}"
                    (activity as MainActivity).showToast("로그인 성공")
                    AppData.loginData = LoginData()
                    AppData.loginData?.memberId = memberId
                    AppData.loginData?.memberPw = memberPw
                    AppData.loginData?.memberName = response.body()?.data?.get(0)?.memberName.toString()
                    AppData.loginData?.memberNo = response.body()?.data?.get(0)?.memberNo.toString()
                    AppData.loginData?.memberAddress = response.body()?.data?.get(0)?.memberAddress.toString()
                    AppData.loginData?.memberImage = response.body()?.data?.get(0)?.memberImage.toString()
                    (activity as MainActivity).onFragmentChanged(MainActivity.ScreenItem.ITEMmyPage)

                } else if(checkMember == "0"){
                    val builder = AlertDialog.Builder(activity)
                    builder.setTitle("로그인")
                    builder.setMessage("아이디/비밀번호를 다시 입력해주세요.")
                    builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                        toast("Positive")
                    }
                    builder.show()
                    binding.loginId.setText("")
                    binding.loginPassword.setText("")
                }

            }
            override fun onFailure(call: Call<MemberListResponse>, t: Throwable) {
                (activity as MainActivity).showToast("qkqh")
            }

        })

    }

    fun toast(message:String){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show()
    }

    }

