package com.zulfadar.bisindgo.ui.edituserprofile

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.R
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.databinding.FragmentEditUserProfile2Binding
import com.zulfadar.bisindgo.data.User
import com.zulfadar.bisindgo.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentEditUserProfile : Fragment() {

    private lateinit var binding: FragmentEditUserProfile2Binding
    private val viewModel by viewModels<UserAccountViewModel>()
    private var imageUri: Uri? = null
    private lateinit var imageActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            imageUri = it.data?.data
            Glide.with(this).load(imageUri).into(binding.imgProfilePreview)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditUserProfile2Binding.inflate(inflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.user.collectLatest {
                when(it) {
                    is Resource.Loading -> {
                        showUserLoading()
                    }
                    is Resource.Success -> {
                        hideUserLoading()
                        showUserDataProfile(it.data!!)
                    }
                    is  Resource.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launch {
            viewModel.updateUserProfile.collectLatest {
                when(it) {
                    is Resource.Loading -> {
                        binding.btnSaveProfile.startAnimation()
                    }
                    is Resource.Success -> {
                        binding.btnSaveProfile.startAnimation()
                    }
                    is  Resource.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }

        binding.btnSaveProfile.setOnClickListener {
            binding.apply {
                val firstName = edtFirstName.text.toString().trim()
                val lastName = edtLastName.text.toString().trim()
                val email = edtEmail.text.toString().trim()
                val notelp = edtNotelp.text.toString().trim()
                val user = User(firstName, lastName, email, notelp)
                viewModel.upadateUserProfile(user, imageUri)
            }
        }

        binding.editImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            imageActivityResultLauncher.launch(intent)
        }
    }

    private fun showUserDataProfile(data: User) {
        binding.apply {
            Glide.with(this@FragmentEditUserProfile).load(data.imagePath).error(ColorDrawable(Color.BLACK)).into(imgProfilePreview)
            edtFirstName.setText(data.firstName)
            edtLastName.setText(data.lastName)
            edtEmail.setText(data.email)
            edtNotelp.setText(data.telpNumb)
        }
    }

    private fun hideUserLoading() {
        binding.apply {
            progressbarEditProfile.visibility = View.GONE
            imgProfilePreview.visibility = View.VISIBLE
            editImage.visibility = View.VISIBLE
            edtFirstName.visibility = View.VISIBLE
            edtLastName.visibility = View.VISIBLE
            edtEmail.visibility = View.VISIBLE
            edtNotelp.visibility = View.VISIBLE
            forgotPassword.visibility = View.VISIBLE
            btnSaveProfile.visibility = View.VISIBLE
        }
    }

    private fun showUserLoading() {
        binding.apply {
            progressbarEditProfile.visibility = View.VISIBLE
            imgProfilePreview.visibility = View.INVISIBLE
            editImage.visibility = View.INVISIBLE
            edtFirstName.visibility = View.INVISIBLE
            edtLastName.visibility = View.INVISIBLE
            edtEmail.visibility = View.INVISIBLE
            edtNotelp.visibility = View.INVISIBLE
            forgotPassword.visibility = View.INVISIBLE
            btnSaveProfile.visibility = View.INVISIBLE
        }
    }

}