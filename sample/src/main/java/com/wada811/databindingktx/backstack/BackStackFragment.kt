package com.wada811.databindingktx.backstack

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.wada811.databinding.dataBinding
import com.wada811.databindingktx.R
import com.wada811.databindingktx.databinding.BackStackFragmentBinding

class BackStackFragment : Fragment(R.layout.back_stack_fragment) {
    private val binding: BackStackFragmentBinding by dataBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.text = requireArguments().getString(EXTRA_TEXT)
    }

    companion object {
        private const val EXTRA_TEXT = "text"
        fun newInstance(text: String) = BackStackFragment().also { fragment ->
            fragment.arguments = Bundle().also { bundle ->
                bundle.putString(EXTRA_TEXT, text)
            }
        }
    }
}
