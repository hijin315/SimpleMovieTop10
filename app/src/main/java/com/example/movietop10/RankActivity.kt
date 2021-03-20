package com.example.movietop10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.movietop10.movieApi.MovieDto

class RankActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)

        val bundle = intent.extras
        val list : List<MovieDto> = bundle?.getSerializable("movieList") as List<MovieDto>
        Log.d("My","RankActivity의 MovieList : $list")

        val name = bundle.getString("name")
        val age = bundle.getInt("age")
        Log.d("MY",""+name+age)
    }
}
//TODO Rank 페이지 완성 하기 상게 페이지 구현하기
//TODO Retrofir2 살펴보기