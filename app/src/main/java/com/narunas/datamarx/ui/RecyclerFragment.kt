package com.narunas.datamarx.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.narunas.datamarx.R

class RecyclerFragment: Fragment() {

    companion object {
        fun getInstance(bundle: Bundle) : RecyclerFragment {
            val f = RecyclerFragment()
            f.arguments = bundle
            return f
        }
    }

    init {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}