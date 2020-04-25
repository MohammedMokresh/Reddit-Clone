package com.mokresh.redditclone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mokresh.redditclone.R
import com.mokresh.redditclone.databinding.FragmentAddTopicBottomSheetBinding
import com.mokresh.redditclone.utils.DialogUtil


/**
 * A simple [Fragment] subclass.
 */
class AddTopicBottomSheetFragment : BottomSheetDialogFragment(),
    DraftDialogFragment.DraftClickListener {
    private lateinit var binding: FragmentAddTopicBottomSheetBinding

    private lateinit var addTopicClickListener: AddTopicClickListener

    fun setAddTopicClickListener(addTopicClickListener: AddTopicClickListener) {
        this.addTopicClickListener = addTopicClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_topic_bottom_sheet,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.setCanceledOnTouchOutside(false)

        binding.addTextView.setOnClickListener {
            if (binding.addTopicEditText.text.toString() != "") {
                dismiss()
                addTopicClickListener.submitOnClick(binding.addTopicEditText.text.toString())
            } else {
                Toast.makeText(
                    binding.root.context
                    , getString(R.string.cant_be_blank), Toast.LENGTH_LONG
                ).show()
            }

        }

        binding.closeImageView.setOnClickListener {
            if (binding.addTopicEditText.text.toString() != "") {
                DialogUtil.showDraftDialog(requireActivity().supportFragmentManager, this)
            } else {
                dismiss()
            }
        }

    }

    interface AddTopicClickListener {
        fun submitOnClick(topic: String)
    }

    override fun clearDraftOnClick() {
        dismiss()
    }


}
