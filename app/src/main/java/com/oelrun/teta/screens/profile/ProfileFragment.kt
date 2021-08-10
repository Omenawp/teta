package com.oelrun.teta.screens.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.oelrun.teta.R
import com.oelrun.teta.data.genre.GenreDto
import com.oelrun.teta.databinding.FragmentProfileBinding
import com.oelrun.teta.databinding.ListItemGenreBinding

class ProfileFragment: Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val imm = this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val editorActionListener = TextView.OnEditorActionListener { textView, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                textView.clearFocus()
                imm.hideSoftInputFromWindow(textView.windowToken, 0)
            }
            true
        }

        binding.userName.setOnEditorActionListener(editorActionListener)
        binding.userPassword.setOnEditorActionListener(editorActionListener)
        binding.userEmail.setOnEditorActionListener(editorActionListener)
        binding.userPhone.setOnEditorActionListener(editorActionListener)

        binding.btnExit.setOnClickListener {
            Toast.makeText(context, resources.getString(R.string.profile_exit_message), Toast.LENGTH_SHORT).show()
        }

        profileViewModel.favGenres.observe(viewLifecycleOwner, { data ->
            data?.let {
                binding.profileMainContainer.visibility = View.VISIBLE
                makeFavItems(data)
            }
            binding.loadingImage.visibility = View.GONE
        })

        profileViewModel.errorMessage.observe(viewLifecycleOwner, { message ->
            message?.let {
                binding.profileMainContainer.visibility = View.GONE
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                profileViewModel.errorMessageShown()
            }
        })

        return binding.root
    }

    private fun makeFavItems(data: List<GenreDto>) {
        val group = binding.listInterests
        val inflater = LayoutInflater.from(group.context)

        val children = data.map{
            val genreBinding = ListItemGenreBinding.inflate(inflater, group, false)
            val view = genreBinding.root
            view.text = it.name.lowercase()
            view.tag = it.id
            view
        }

        group.removeAllViews()
        for(chip in children) {
            group.addView(chip)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}