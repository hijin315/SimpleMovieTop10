package com.example.movietop10

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietop10.movieApi.MovieDto

class RankActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var list: List<MovieDto>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)

        recyclerView = findViewById(R.id.rv_result)
        recyclerView.adapter = ResultRecyclerViewAdpater()
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        val bundle = intent.extras
        list = bundle?.getSerializable("movieList") as List<MovieDto>
        Log.d("My", "RankActivity의 MovieList : $list")

    }

    inner class ResultRecyclerViewAdpater :
        RecyclerView.Adapter<ResultRecyclerViewAdpater.ResultViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ResultRecyclerViewAdpater.ResultViewHolder {
            //마지막 인자 -> parent에 합성(?)을 시킬지
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
            return ResultViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        @SuppressLint("ResourceAsColor")
        override fun onBindViewHolder(
            holder: ResultRecyclerViewAdpater.ResultViewHolder,
            position: Int
        ) {
            holder.apply {
                rankTextView.text = list[position].rank
                val rankInten = list[position].rankInten
                rankIntenTextView.text = rankInten
                movieNameTextView.text = list[position].movieNm
                if (rankInten?.toInt()!! < 0) {
                    rankIntenImageView.setImageResource(R.drawable.ic_sort_down_solid)
                    rankIntenTextView.setTextColor(R.color.red)
                }
            }
        }

        inner class ResultViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            //위의 onCreateViewHolder에서 생성된 view를 가지고 실행함
            //클래스 안의 클래스
            //item_detail의 아이들을 가지고 옴 findviewbyid 로
            val rankTextView: TextView = view.findViewById(R.id.rank)
            val movieNameTextView: TextView = view.findViewById(R.id.movie_name)
            val rankIntenTextView: TextView = view.findViewById(R.id.tv_rankInten)
            val rankIntenImageView: ImageView = view.findViewById(R.id.iv_rankInten)
        }
    }
}