package com.oelrun.teta.screens.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.chip.ChipGroup
import com.oelrun.teta.R
import com.oelrun.teta.data.genre.GenresDataSource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val profileViewModel = ProfileViewModel()

        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        val editorActionListener = TextView.OnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                textView.clearFocus()
                imm.hideSoftInputFromWindow(textView.windowToken, 0)
            }
            true
        }

        view.findViewById<EditText>(R.id.user_name).setOnEditorActionListener(editorActionListener)
        view.findViewById<EditText>(R.id.user_password).setOnEditorActionListener(editorActionListener)
        view.findViewById<EditText>(R.id.user_email).setOnEditorActionListener(editorActionListener)
        view.findViewById<EditText>(R.id.user_phone).setOnEditorActionListener(editorActionListener)

        view.findViewById<AppCompatButton>(R.id.btn_exit).setOnClickListener {
            Toast.makeText(this.context, resources.getString(R.string.profile_exit_message),
                Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launch {
            profileViewModel.favGenres.collect { data ->
                data?.let {
                    makeFavItems(view)
                }
            }
        }

        lifecycleScope.launch {
            profileViewModel.errorMessage.collect { message ->
                message?.let {
                    Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
                    profileViewModel.errorMessageShown()
                }
            }
        }

        return view
    }

    private fun makeFavItems(view: View) {
        val data = GenresDataSource().getGenres()
        val group = view.findViewById<ChipGroup>(R.id.list_interests)
        val inflater = LayoutInflater.from(group.context)

        val children = data.map{
            val chip = inflater.inflate(R.layout.list_item_genre, group, false) as TextView
            chip.text = it.name.lowercase()
            chip.tag = it.id
            chip
        }

        group.removeAllViews()
        for(chip in children) {
            group.addView(chip)
        }
    }
}