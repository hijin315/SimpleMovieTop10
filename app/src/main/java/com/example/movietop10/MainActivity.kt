package com.example.movietop10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.movietop10.movieApi.MovieDto
import com.example.movietop10.movieApi.MovieResponse
import com.example.movietop10.movieApi.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button

    companion object {
        const val KEY = "54b408a67510f640f635378b77536444"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.btn1)
        button2 = findViewById(R.id.btn2)
        button3 = findViewById(R.id.btn3)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn1 -> {
                // 오늘 날짜에서 하루를 뺀 날짜를 "yyyyMMdd" 형식으로 만든다.
                val cal: Calendar = Calendar.getInstance()
                cal.add(Calendar.DAY_OF_MONTH, -1)
                val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
                val targetDt = dateFormat.format(cal.time)
                getResult(targetDt)
            }
            R.id.btn2 -> {
                val cal: Calendar = Calendar.getInstance()
                cal.add(Calendar.DAY_OF_MONTH, -14)
                val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
                val targetDt = dateFormat.format(cal.time)
                getResult(targetDt)

            }
            R.id.btn3 -> {
                val cal: Calendar = Calendar.getInstance()
                cal.add(Calendar.DAY_OF_MONTH, -30)
                val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
                val targetDt = dateFormat.format(cal.time)
                getResult(targetDt)
            }
        }

        }
    fun getResult(targetDt:String){
        RetrofitBuilder.api
            .getMovieList(targetDt, KEY)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    val movieResponse = response.body()
                    val list : List<MovieDto> = movieResponse!!.boxofficeResult!!.dailyBoxOfficeList
                    Log.d("MY", "$list")

                    val intent: Intent = Intent(this@MainActivity, RankActivity::class.java)
                    //bundle -> 보따리 느낌
                    val bundle = Bundle()
                    bundle.putSerializable(
                        "movieList",
                        (list as Serializable)
                    ) //list를 강제 형변환
                    //bundle.putString("name","홍길동")
                    //bundle.putInt("age",10)  이 두줄과 같이 put 뒤에 전달하려는 값의 자료형을 쓰면된다.
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            })
    }


}