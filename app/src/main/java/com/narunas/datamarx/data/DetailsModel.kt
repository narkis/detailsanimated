package com.narunas.datamarx.data

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlin.concurrent.thread


const val SECTION_CNT = 3
const val CARD_CNT = 10

class DetailsModel: ViewModel() {

    companion object {

        val ContentData: MutableLiveData<ArrayList<SectionData>> = MutableLiveData()

    }

    fun buildContent() {

        thread {

            val data = ArrayList<SectionData>()
            for(i in 0..SECTION_CNT) {

                val section = SectionData("Section $i")
                section.index = i
                section.cards = fetchRandomCardSet()
                data.add(section)
            }

            ContentData.postValue(data)
        }

    }

    private fun fetchRandomCardSet() : ArrayList<CardData> {

        val data = ArrayList<CardData>()

        for(i in 0..CARD_CNT) {

            /** stubbed image for now **/
            val card = CardData("http://feedinspiration.com/wp-content/uploads/2015/04/famous-abstract-tree-paintings.jpg")
            card.title = "... default.."
            card.sub_title = ".. default .."
            data.add(card)
        }

        return data
    }

    private fun fetchImageUrl() : String {

        return ""
    }


}