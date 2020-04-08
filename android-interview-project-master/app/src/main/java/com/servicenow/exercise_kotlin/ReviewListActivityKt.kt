package com.servicenow.exercise_kotlin

import android.app.ListActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.servicenow.coffee.CoffeeShopReviews
import com.servicenow.coffee.Review
import com.servicenow.exercise.R

class ReviewListActivityKt : ListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAdapter = ReviewAdapter()
    }

    inner class ReviewAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return CoffeeShopReviews.list.size
        }

        override fun getItem(position: Int): Any {
            return CoffeeShopReviews.list[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val row: View = convertView ?: LayoutInflater.from(this@ReviewListActivityKt).inflate(
                R.layout.review_item,
                parent,
                false
            )

            val reviewImage: ImageView = row.findViewById(R.id.image) as ImageView
            val shopName = row.findViewById(R.id.text1) as TextView
            val reviewText = row.findViewById(R.id.text2) as TextView

            val review: Review = CoffeeShopReviews.list[position]
            shopName.text = review.name
            reviewText.text = review.review
            reviewImage.setImageResource(Review.getIconResourceFromName(review.name))
            return row
        }
    }
}
