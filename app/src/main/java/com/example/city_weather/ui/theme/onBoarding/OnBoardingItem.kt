package com.karimsinouh.onBoarding.ui.theme.onBoarding

import com.example.city_weather.R


class OnBoardingItem (
    var title:Int,
    var text:Int,
    var image:Int,
    var icon:Int
){

    companion object {

        fun get():List<OnBoardingItem>{
            return listOf(
                OnBoardingItem(R.string.Dushanbe,R.string.Text1,R.color.background_of_city1,R.drawable.iconca1),
                OnBoardingItem(R.string.Hisor,R.string.Text2,R.color.background_of_city2,R.drawable.iconca1)
            )
        }
    }

}