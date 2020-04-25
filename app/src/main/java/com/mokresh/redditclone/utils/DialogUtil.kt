package com.mokresh.redditclone.utils

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.mokresh.redditclone.ui.AddTopicBottomSheetFragment
import com.mokresh.redditclone.ui.DraftDialogFragment

object DialogUtil {

    fun showAddTopicDialog(
        fragmentManager: FragmentManager?,
        addTopicClickListener: AddTopicBottomSheetFragment.AddTopicClickListener
    ) {
        if (fragmentManager == null) return
        try {
            val fragment = AddTopicBottomSheetFragment()
            fragment.setAddTopicClickListener(addTopicClickListener)
            val args = Bundle()
            fragment.arguments = args
            fragment.show(fragmentManager, "show_add_topic_dialog")
        } catch (ignore: Exception) {
        }
    }

    fun showDraftDialog(
        fragmentManager: FragmentManager?,
        draftClickListener: DraftDialogFragment.DraftClickListener
    ) {
        if (fragmentManager == null) return
        try {
            val fragment = DraftDialogFragment()
            fragment.setDraftClickListener(draftClickListener)
            val args = Bundle()
            fragment.arguments = args
            fragment.show(fragmentManager, "show_draft_dialog")
        } catch (ignore: Exception) {
        }
    }
}