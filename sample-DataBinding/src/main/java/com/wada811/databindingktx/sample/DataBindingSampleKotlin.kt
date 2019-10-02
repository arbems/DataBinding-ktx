package com.wada811.databindingktx.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.wada811.databinding.dataBinding
import com.wada811.databinding.inflate
import com.wada811.databinding.setContentView
import com.wada811.databindingktx.R
import com.wada811.databindingktx.databinding.DataBindingActivityBinding
import com.wada811.databindingktx.databinding.DataBindingFragmentBinding

@SuppressLint("Registered")
class DataBindingSampleKotlin {

    class DataBindingActivity : FragmentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val binding = DataBindingUtil.setContentView<DataBindingActivityBinding>(this, R.layout.data_binding_activity)
            binding.lifecycleOwner = this
        }
    }

    class TopLevelFunctionActivity : FragmentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val binding = setContentView<DataBindingActivityBinding>(this, R.layout.data_binding_activity)
        }
    }

    class DelegatedPropertyActivity : FragmentActivity() {
        private val binding: DataBindingActivityBinding by dataBinding(R.layout.data_binding_activity)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            // use binding
        }
    }

    class DataBindingFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val binding = DataBindingUtil.inflate<DataBindingFragmentBinding>(inflater, R.layout.data_binding_fragment, container, false)
            binding.lifecycleOwner = viewLifecycleOwner
            return binding.root
        }
    }

    class TopLevelFunctionFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val binding = inflate<DataBindingFragmentBinding>(this, R.layout.data_binding_fragment, container, false)
            return binding.root
        }
    }


    class DelegatedPropertyFragment : Fragment() {
        private val binding: DataBindingFragmentBinding by dataBinding(R.layout.data_binding_fragment)
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return binding.root
        }
    }

}
