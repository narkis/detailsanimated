package com.narunas.datamarx



import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.UiThread
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.narunas.datamarx.base.BaseApplicationTest
import com.narunas.datamarx.data.DetailsModel
import com.narunas.datamarx.data.DetailsModel.Companion.ErrorData
import com.narunas.datamarx.data.SectionData
import com.narunas.datamarx.ui.RecyclerFragment
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import kotlin.concurrent.thread

/**
 * Instrumented test, which will execute on an Android device.
 *
 */
@RunWith(AndroidJUnit4::class)
class MyInstrumentedTest: BaseApplicationTest<MainActivity>(MainActivity::class.java) {

    val appContext = ApplicationProvider.getApplicationContext<MainApplication>()
    lateinit var model : DetailsModel

    @Before
    fun setUp () {

        model = ViewModelProviders.of(testRule.activity).get(DetailsModel::class.java)
    }


    @Test
    fun useAppContext() {

        assertEquals("com.narunas.datamarx", appContext.packageName)


    }

    @Test
    fun checkVisibility() {

        /** visibility check **/
        checkThat.viewIsVisible(R.id.action_bar)
        checkThat.viewIsVisible(R.id.scroll_view)
    }

    @Test
    fun touchEvents() {

        /** UI touch event check **/
        events.clickOnView(R.id.action_bar)
    }

    @Test
    fun runHttpTest() {


        val model = ViewModelProviders.of(testRule.activity).get(DetailsModel::class.java)
        val searchTerm = model.fetchRandomSearchTerm()


        thread {


            val sections =  ArrayList<SectionData>()
            val sectionData = model.sendRequest(searchTerm)

            assertNotEquals("data is NULL ", sectionData, null)

            if(sectionData != null) {
                sections.add(sectionData)
            }

            DetailsModel.ContentData.postValue(sections)


        }

    }

    @Test
    fun checkErrorHandling() {

        val model = ViewModelProviders.of(testRule.activity).get(DetailsModel::class.java)
        ErrorData.postValue("test error message ")

    }
}
