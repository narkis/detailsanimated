package com.narunas.datamarx

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.transition.Transition
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*

class DetailsActivity: AppCompatActivity() {

    companion object {
        val MAIN_IMAGE_TRANSITION: String = "class:image:transition"
        val MAIN_TITLE_TRANSITION: String = "class:title:transition"
        val MAIN_IMAGE_URL: String = "class:image:url"
    }
    private lateinit var transition: Transition
    private lateinit var url: String


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_detail)

        url = intent.getStringExtra(MAIN_IMAGE_URL)
        addTransitionListener()

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }


    private fun addTransitionListener() {


        transition.addListener(object : Transition.TransitionListener {

            override fun onTransitionResume(p0: Transition?) {
            }

            override fun onTransitionPause(p0: Transition?) {
            }

            override fun onTransitionCancel(p0: Transition?) {
                transition.removeListener(this)
            }

            override fun onTransitionStart(p0: Transition?) {
            }

            override fun onTransitionEnd(p0: Transition?) {

                loadFullImage()
                transition.removeListener(this)
            }
        })
    }

    private fun loadFullImage() {

        main_image.imageSource(url)

    }

    private fun hideSystemUI() {

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                // Hide the nav bar and status bar
//                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_FULLSCREEN)

    }

}