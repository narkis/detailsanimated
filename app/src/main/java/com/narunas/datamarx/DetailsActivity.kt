package com.narunas.datamarx

import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.transition.Transition
import android.view.Window
import com.narunas.datamarx.data.CardData
import com.narunas.datamarx.data.DetailsModel.Companion.CardInDetail
import kotlinx.android.synthetic.main.activity_detail.*

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

        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        }

        setContentView(R.layout.activity_detail)

        cardData = CardInDetail.value

        if(cardData != null) {

            ViewCompat.setTransitionName(main_image, MAIN_IMAGE_TRANSITION)
            ViewCompat.setTransitionName(main_headline, MAIN_TITLE_TRANSITION)

            transition = window.sharedElementEnterTransition
            addTransitionListener()

            title = cardData?.title!!
            thumbUrl = cardData?.thumbUrl!!
            url = cardData?.mainUrl!!


            loadFullImage()
            main_headline.text = title

        } else {

            /** handle data error **/
        }

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
                transition.removeListener(this)
            }
        })
    }

    private fun loadFullImage() {

        main_image.imageSource(url, false)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAfterTransition()
    }

}