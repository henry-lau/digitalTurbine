package com.example.digitalturbine.ui.fragment

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalturbine.R
import com.example.digitalturbine.data.model.db.data.ad
import com.example.digitalturbine.ui.adapter.ItemAdapter
import java.util.*

abstract class BaseListFragment : BaseViewModelFragment(), ItemAdapter.OnItemClickListener {
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    protected var pageSize = 0
    protected var firstVisibleItemPosition = 0
    protected var lastItemPosition = 0
    protected lateinit var adapter: ItemAdapter
    protected var responseList: MutableList<ad> = ArrayList<ad>()

    private val recyclerViewOnScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                if (firstVisibleItemPosition + pageSize > lastItemPosition) {
                    loadMoreItems()
                }
            }
        }

    /**
     * Should return resource id for layout of current fragment
     */
    abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calculatePageSize()
        recyclerView = view.findViewById<View>(R.id.recycler_view) as RecyclerView
        layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = this.layoutManager
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener)
        adapter = ItemAdapter()
        recyclerView.adapter = this.adapter
        adapter.onItemClickListener = this
    }

    fun updateView(items: List<ad>?) {
        if (items == null) {
            return
        }
        responseList.clear()
        responseList.addAll(items)
        lastItemPosition = if (responseList.size < pageSize) responseList.size else pageSize
        adapter.addItems(responseList.subList(0, lastItemPosition))
        adapter.notifyDataSetChanged()
    }

    private fun loadMoreItems() {
        //load additional Items by the size of pagesize
        if (responseList.size < pageSize) {
            return
        }
        val items = ArrayList<ad>()
        var i = 0
        while (lastItemPosition < responseList.size - 1 && i < pageSize) {
            lastItemPosition++
            items.add(responseList[lastItemPosition])
            i++
        }
        adapter.addItems(items)
        //check if it reaches the bottom, don't notifyDataSetChanged
        adapter.notifyDataSetChanged()
    }

    private fun calculatePageSize() {
        //update pagesize = all Item items fitting screen height * 2
        val display: Display? = activity?.getWindowManager()?.getDefaultDisplay()
        val outMetrics = DisplayMetrics()
        display?.getMetrics(outMetrics)
        val density: Float = resources.displayMetrics.density
        val dpHeight = outMetrics.heightPixels / density
        val dpHeightThumbnail: Float =
            resources.getDimension(R.dimen.item_row_height) / density
        pageSize = (dpHeight / dpHeightThumbnail).toInt() * 2
    }
}

