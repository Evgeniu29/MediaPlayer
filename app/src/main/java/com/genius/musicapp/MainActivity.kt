package com.genius.musicapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.genius.musicapp.ui.theme.MusicAppTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    lateinit var  myRecyclerView:RecyclerView
    lateinit var myAdapter: MyAdapter
    lateinit var editText :EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        myRecyclerView = findViewById(R.id.recyclerView)

        getMusic("Eminem")

        editText  = findViewById(R.id.se)

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })





    }

    fun filter(text: String) {

        getMusic(text)


    }

fun getMusic(text:String){
    val  retrofitBuilder = Retrofit.Builder()
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

            myAdapter = MyAdapter(this@MainActivity, dataList )
            myRecyclerView.adapter = myAdapter
            myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity )
            Log.d("Tag: onResponse ", "onResponse: " + response.body())



        }

        override fun onFailure(call: Call<MyData?>, t: Throwable) {

            TODO("Not yet implemented")
            Log.d("Tag: onFailure", "onFailure" + t.message)
        }


    })

}
}



