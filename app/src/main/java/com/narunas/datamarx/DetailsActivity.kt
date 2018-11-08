package com.narunas.datamarx

import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v7.app.AppCompatActivity
import android.transition.Transition
import android.view.View
import android.view.animation.BounceInterpolator
import com.narunas.datamarx.data.CardData
import com.narunas.datamarx.data.DetailsModel.Companion.CardInDetail
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.card.*

class DetailsActivity: AppCompatActivity() {

    companion object {
        val MAIN_IMAGE_TRANSITION: String = "class:image:transition"
        val MAIN_TITLE_TRANSITION: String = "class:title:transition"
    }
    private lateinit var transition: Transition
    private lateinit var thumbUrl: String
    private lateinit var url: String
    private lateinit var title: String
    private var cardData: CardData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        cardData = CardInDetail.value

        if(cardData != null) {

            ViewCompat.setTransitionName(main_image, MAIN_IMAGE_TRANSITION)
            ViewCompat.setTransitionName(main_headline, MAIN_TITLE_TRANSITION)
            transition = window.sharedElementEnterTransition
            transition.interpolator = LinearOutSlowInInterpolator()
            addTransitionListener()

            title = cardData?.title!!
            thumbUrl = cardData?.thumbUrl!!
            url = cardData?.mainUrl!!


//            loadThumbnail()
            main_headline.text = title

        } else {
            /** handle data error **/
        }

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
                loadThumbnail()
            }

            override fun onTransitionEnd(p0: Transition?) {

                loadFullImage()
                transition.removeListener(this)
            }
        })
    }

    private fun loadFullImage() {

        main_image.imageSource(url, false)

    }

    private fun loadThumbnail() {

        main_image.imageSource(thumbUrl, false)

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