package com.genius.musicapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingwithjks.musicplayer.ViewModel.DataViewModel
import com.genius.musicapp.data.Data
import com.genius.musicapp.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    lateinit var editText: EditText
    lateinit var dataViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        myRecyclerView = findViewById(R.id.recyclerView)

        editText = findViewById(R.id.se)

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

                if (p0.toString() == "") {
                    return
                }

                filter(p0.toString())

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })


    }
    fun filter(text: String) {
        GlobalScope.launch(Dispatchers.IO) {

            getMusic(text)

        }
    }

    private fun getMusic(text: String) {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData(text)
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(
                call: Call<MyData?>,
                response: Response<MyData?>
            ) {

                val dataList = response.body()?.data!!

                dataViewModel = ViewModelProvider(this@MainActivity).get(DataViewModel::class.java)

                dataViewModel.insert(this@MainActivity, ArrayList(dataList));

                myAdapter = MyAdapter(this@MainActivity, dataList)
                myRecyclerView.adapter = myAdapter
                myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("Tag: onResponse ", "onResponse: " + response.body())

            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {

                Log.d("Tag: onFailure", "onFailure" + t.message)
            }
        })

    }

    private fun initViewModel() {

        dataViewModel.getAllData(this)?.observe(this, androidx.lifecycle.Observer {
            myAdapter.apply {
                setData(it as ArrayList<Data>)
                notifyDataSetChanged()
            }

        })
    }
}

