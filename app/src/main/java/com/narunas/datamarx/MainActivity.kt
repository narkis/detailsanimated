package com.narunas.datamarx

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.narunas.datamarx.ui.RecyclerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        addContent()
    }


    private fun addContent() {

        for(i in 0..1) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, RecyclerFragment.getInstance(), i.toString())
                .commit()
        }


    }
}
