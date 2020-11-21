package com.example.digitalturbine.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.digitalturbine.R
import com.example.digitalturbine.data.model.db.data.ad
import com.example.digitalturbine.data.viewModel.AdsViewModel
import com.example.digitalturbine.ui.fragment.AdFragment.Companion.AD_ITEM
import com.example.digitalturbine.utilities.FragmentUtils
import kotlinx.android.synthetic.main.fragment_menu.*

/**
 *   Second required screen showing one Ad
 */
class AdListFragment : BaseListFragment() {
    private val adsViewModel by viewModels<AdsViewModel> { getViewModelFactory() }
    override val layoutId: Int
        get() = R.layout.fragment_menu

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(adsViewModel) {
            adList.observeNotNull {
                updateView(it.adList)
            }

            onLoading.observeNotNull {
                progressBar.visibility =
                    if (it) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onItemClick(position: Int, view: View?) {
        val fragment: AdFragment = AdFragment.newInstance()
        val item: ad = responseList[position]
        val bundle = Bundle()

        bundle.putParcelable(AD_ITEM, item)
        fragment.arguments = bundle
        FragmentUtils.addFragment(requireActivity(), fragment, this, fragment.javaClass.simpleName)
    }

    companion object {
        fun newInstance(): AdListFragment {
            return AdListFragment()
        }
    }
}
