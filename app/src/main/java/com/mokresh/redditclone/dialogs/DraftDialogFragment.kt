package com.mokresh.redditclone.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.mokresh.redditclone.R
import com.mokresh.redditclone.databinding.FragmentDraftDialogBinding

/**
 * A simple [Fragment] subclass.
 */
class DraftDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentDraftDialogBinding

    private lateinit var draftClickListener: DraftClickListener

    fun setDraftClickListener(draftClickListener: DraftClickListener) {
        this.draftClickListener = draftClickListener
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_draft_dialog, container, false)
        binding.clearDraftTextView.setOnClickListener {
            dismiss()
            draftClickListener.clearDraftOnClick()
        }
        binding.keepEditingTextView.setOnClickListener { dismiss() }

        return binding.root
    }


    interface DraftClickListener {
        fun clearDraftOnClick()
    }

}
