package com.zulfadar.bisindgo.ui.profile

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.R
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.databinding.FragmentEditUserProfile2Binding
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.databinding.FragmentProfileBinding
import com.zulfadar.bisindgo.ui.edituserprofile.FragmentEditUserProfile
import com.zulfadar.bisindgo.ui.login.LoginActivity
import com.zulfadar.bisindgo.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    val viewModel by viewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userprofileCard.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_user_account_edit_profile)
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.user.collectLatest {
                when(it) {
                    is Resource.Loading -> {
                        binding.progressbarSettings.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressbarSettings.visibility = View.GONE
                        Glide.with(requireView()).load(it.data!!.imagePath).error(ColorDrawable(
                            Color.BLACK)).into(binding.imgItemPhoto)
                        binding.tvItemName.text = "${it.data.firstName}"
                        binding.tvEmail.text = "${it.data.email}"
                        binding.tvPhone.text = "${it.data.telpNumb}"
                        binding.tvFullname.text = "${it.data.firstName} ${it.data.lastName}"

                    }
                    is  Resource.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }

//    override fun onResume() {
//        super.onResume()
//
//    }
}