package com.narunas.datamarx.data

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.HttpURLConnection.*
import java.net.URL
import java.net.URLEncoder
import kotlin.concurrent.thread
import kotlin.random.Random


const val SECTION_CNT = 3


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

    private val HOST = "https://api.cognitive.microsoft.com"
    private val PATH = "/bing/v7.0/images/search"
    private val KEY1 = "8d7a2427dd15457cbf133ea4115be0c2"
    private val KEY2 = "2aca3d6368404258a0b57c13bc82bdc8"

    companion object {

        val TAG: String = DetailsModel::class.java.simpleName
        val ContentData: MutableLiveData<ArrayList<SectionData>> = MutableLiveData()
        val CardInDetail: MutableLiveData<CardData> = MutableLiveData()
        val ErrorData: MutableLiveData<String> = MutableLiveData()

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

    fun fetchRandomSearchTerm() : String {

        val ret: String
        val index = Random.nextInt(0, SEARCH_TERMS.values().size)
        ret = SEARCH_TERMS.values()[index].name
        return ret
    }

    fun sendRequest(searchTerm: String) : SectionData? {

        var section: SectionData? = null
        val reqParam = URLEncoder.encode(searchTerm, "UTF-8")
        val mURL = URL(HOST + PATH + "?q=" + reqParam)

        with(mURL.openConnection() as HttpURLConnection) {

            setRequestProperty("Ocp-Apim-Subscription-Key", KEY1)
            connectTimeout = 6000

            when (responseCode) {
                HTTP_OK -> {

                } else -> {

                    ErrorData.postValue(errorStream.read().toString())
                    return null
                }
            }


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

            disconnect()

            if (section == null) {

                val error = "section for $searchTerm is not available"
                ErrorData.postValue(error)

            }

        }

        return section
    }

}