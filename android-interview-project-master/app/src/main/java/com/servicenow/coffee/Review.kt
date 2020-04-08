package com.servicenow.coffee

import com.servicenow.exercise.R

class Review(
    var name: String,
    var review: String,
    var rating: Int,
    var location: String
) {

    override fun toString(): String {
        return name
    }

    companion object {
        @JvmStatic
        fun getIconResourceFromName(name: String): Int {
            when (name) {
                "Lofty" -> return R.drawable.bean_bag
                "Zumbar" -> return R.drawable.coffee_cup
                "Blue Bottle" -> return R.drawable.coffee_grinder
                "Bird Rock" -> return R.drawable.coffee_maker
                "Better Buzz Coffee" -> return R.drawable.coffee_shop
            }
            return -1
        }
    }
}