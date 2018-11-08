package com.narunas.datamarx.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.narunas.datamarx.R
import com.narunas.datamarx.data.DetailsModel.Companion.ContentData
import com.narunas.datamarx.data.SectionData
import com.narunas.datamarx.ui.adapters.Adapter
import kotlinx.android.synthetic.main.recycler.*
import kotlinx.android.synthetic.main.recycler.view.*

class RecyclerFragment: Fragment() {

    private lateinit var section: SectionData
    private lateinit var mAdapter: Adapter
    private var index: Int = -1
    private var layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

    companion object {
        fun getInstance(i: Int) : RecyclerFragment {
            val f = RecyclerFragment()
            val args = Bundle()
            args.putInt("index", i)
            f.arguments = args
            return f
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        index = arguments!!.getInt("index")
        section = ContentData.value!!.get(index)
        mAdapter = Adapter()
        mAdapter.setData(section.cards)
        view.recycler.layoutManager = layoutManager
        view.recycler.adapter = mAdapter
        section_title.text = section.title
    }
}