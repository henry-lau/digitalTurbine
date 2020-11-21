package com.example.digitalturbine.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.digitalturbine.R
import com.example.digitalturbine.data.model.db.data.ad
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_ad_item.*

class AdFragment : Fragment() {
    private lateinit var adItem: ad

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bundle = arguments
        if (bundle != null) {
            adItem = bundle.getParcelable<Parcelable>(AD_ITEM) as ad
        }

        return inflater.inflate(R.layout.fragment_ad_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load(adItem.productThumbnail).fit().centerCrop().noFade()
            .into(photo)
        name.text = adItem.productName
        description.text = adItem.productDescription
        numberOfRatings.text = adItem.numberOfRatings
        rating.text = adItem.rating
        category_name.text = adItem.categoryName
    }

    companion object {
        const val AD_ITEM = "AdItem"
        fun newInstance(): AdFragment {
            return AdFragment()
        }
    }
}