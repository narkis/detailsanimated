package com.narunas.datamarx.data

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import kotlin.concurrent.thread
import kotlin.random.Random


const val SECTION_CNT = 3
const val HOST = "https://api.cognitive.microsoft.com"
const val PATH = "/bing/v7.0/images/search"
const val KEY1 = "8d7a2427dd15457cbf133ea4115be0c2"
const val KEY2 = "2aca3d6368404258a0b57c13bc82bdc8"

class DetailsModel: ViewModel() {

    enum class SEARCH_TERMS {
        Abstract,
        Sunsets,
        Food,
        Sailing,
        Car,
        Sports,
        NBA,
        Drones,
        Art,
        Cats,
        Dogs,
        Skiing,
        Tropics,
        Underwater,
        Beach,
        Pets,
        Soccer,
        Football
    }

    companion object {

        val ContentData: MutableLiveData<ArrayList<SectionData>> = MutableLiveData()

    }

    fun buildContent() {

        thread {


            val data = ArrayList<SectionData>()
            for(i in 1..SECTION_CNT) {

                val searchTerm = fetchRandomSearchTerm()
                val section = sendRequest(searchTerm)
                if(section != null) {
                    data.add(section)
                }
            }

            ContentData.postValue(data)
        }

    }

    private fun fetchRandomSearchTerm() : String {

        val ret: String
        val index = Random.nextInt(0, SEARCH_TERMS.values().size)
        ret = SEARCH_TERMS.values()[index].name
        return ret
    }

    private fun sendRequest(searchTerm: String) : SectionData? {

        var section: SectionData? = null
        val reqParam = URLEncoder.encode(searchTerm, "UTF-8")
        val mURL = URL(HOST + PATH + "?q=" + reqParam)

        with(mURL.openConnection() as HttpURLConnection) {

            setRequestProperty("Ocp-Apim-Subscription-Key", KEY1)

            BufferedReader(InputStreamReader(inputStream)).use {

                val response = StringBuffer()
                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }

                val gson = Gson()
                section = gson.fromJson(response.toString(), SectionData::class.java)
                section?.title = searchTerm

                it.close()

            }

        }

        return section
    }

}