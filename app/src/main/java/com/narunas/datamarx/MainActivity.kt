package com.narunas.datamarx

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.narunas.datamarx.data.DetailsModel
import com.narunas.datamarx.data.DetailsModel.Companion.ContentData
import com.narunas.datamarx.data.SectionData
import com.narunas.datamarx.ui.RecyclerFragment

class MainActivity : AppCompatActivity() {


    private lateinit var model: DetailsModel
    private var observer = Observer<ArrayList<SectionData>> {
        it?.let {
            updateUi(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        model = ViewModelProviders.of(this).get(DetailsModel::class.java)

        if(savedInstanceState == null) {
            model.buildContent()
        }


        ContentData.observe(this, observer)


    }

    override fun onStop() {
        super.onStop()
        ContentData.removeObserver(observer)
    }

    private fun updateUi(data: ArrayList<SectionData>) {

            var section = data[0]
            val f1 = RecyclerFragment.getInstance(0)

            supportFragmentManager.beginTransaction()
                .replace(R.id.section_one, f1, section.title)
                .commit()

            section = data[1]
            val f2 = RecyclerFragment.getInstance(1)

            supportFragmentManager.beginTransaction()
                .replace(R.id.section_two, f2, section.title)
                .commit()


            section = data[2]
            val f3 = RecyclerFragment.getInstance(2)

            supportFragmentManager.beginTransaction()
                .replace(R.id.section_three, f3, section.title)
                .commit()


    }
}
