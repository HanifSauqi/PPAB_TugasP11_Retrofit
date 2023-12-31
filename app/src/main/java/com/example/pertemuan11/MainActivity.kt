package com.example.pertemuan11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.pertemuan11.databinding.ActivityMainBinding
import com.example.pertemuan11.model.Albums
import com.example.pertemuan11.network.ApiClient
import com.example.pertemuan11.network.ApiService
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitService = ApiClient.getRetrofitInstance().create(ApiService::class.java)

        val responseLiveData: LiveData<Response<Albums>> =
            liveData {
                val response = retrofitService.getAlbums()
                emit(response)
            }

        responseLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()
            if (albumList != null){
                while (albumList.hasNext()){
                    val albumItem = albumList.next()

                    val albumInfo =
                        "Album ID: ${albumItem.id}\nUser ID: ${albumItem.userId}\nTitle: ${albumItem.title}\n\n"
                    binding.titleTextView.append(albumInfo)
                }
            }
        })
    }
}