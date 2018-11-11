package com.narunas.datamarx.base

import android.support.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers

class Events {


    fun clickOnView(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId)).perform(ViewActions.click())
    }



}