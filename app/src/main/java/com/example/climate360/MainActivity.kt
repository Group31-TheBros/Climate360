package com.example.climate360

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    private fun getDogURL(){
//        val client = AsyncHttpClient()
//        client["https://dog.ceo/api/breed/labrador/images/random/20", object : JsonHttpResponseHandler()
//        {
//            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON)
//            {
//                val petImageArray = json.jsonObject.getJSONArray("message")
////                val regex = Regex("/breeds/([^/]+)/")
//
//                for (i in 0 until petImageArray.length())
//                {
//                    val currLine = petImageArray.getString(i)
//                    dogList.add(currLine)
////                    val matchResult = regex.find(currLine)
////                    val breed = matchResult?.groups?.get(1)?.value
//
//                }
//                val adapter = DogAdapter(dogList)
//                rvDogs.adapter = adapter
//                rvDogs.layoutManager = LinearLayoutManager(this@MainActivity)
//            }
//
//            override fun onFailure(
//                statusCode: Int,
//                headers: Headers?,
//                errorResponse: String,
//                throwable: Throwable?
//            ) {
//                Log.d("Dog Error", errorResponse)
//            }
//        }]
//    }
}