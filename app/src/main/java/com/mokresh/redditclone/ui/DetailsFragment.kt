package com.mokresh.redditclone.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mokresh.redditclone.R
import com.mokresh.redditclone.databinding.FragmentDetailsBinding
import com.mokresh.redditclone.models.Children
import com.mokresh.redditclone.models.RedditData
import com.mokresh.redditclone.utils.FragmentSwitcher.closeFragment
import com.mokresh.redditclone.utils.ImageUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    private val listingViewModel: ListingViewModel by viewModel()


    companion object {
        fun newInstance(listingId: Int): DetailsFragment {
            val args = Bundle()
            val fragment = DetailsFragment()
            args.putInt("listing_id", listingId)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backImageButton.setOnClickListener {
            closeFragment(
                requireActivity().supportFragmentManager
                , this
            )
        }

        if (arguments != null) {
            val listingId = arguments?.getInt("listing_id")

            if (listingId != null) {
                listingViewModel.getListingById(listingId).observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        showDetail(it)
                    }
                })
            }

        }

    }

    private fun showDetail(data: Children) {
        if (data.data != null) {
            setupDetailHeader(data.data)
            setupDetailBody(data.data)
        }

    }

    private fun setupDetailHeader(subReddit: RedditData) {
        binding.subredditName.text = String.format("r/%s", subReddit.displayName)
        binding.subredditSubscribers.text =
            String.format(Locale.getDefault(), "%d Subscribers", subReddit.subscribers)

        ImageUtil.renderImage(
            subReddit.bannerImg,
            binding.subredditBanner,
            R.drawable.ic_launcher_background,
            requireContext()
        )

        ImageUtil.renderImage(
            subReddit.iconImg,
            binding.subredditIcon,
            R.drawable.ic_launcher_background,
            requireContext()
        )

    }

    private fun setupDetailBody(subReddit: RedditData) {
        binding.subredditPublicDescription.text = subReddit.publicDescription.orEmpty()
        binding.subredditFullDescription.setMarkDownText(subReddit.description.orEmpty())
        binding.subredditFullDescription.isOpenUrlInBrowser = true
    }


}
