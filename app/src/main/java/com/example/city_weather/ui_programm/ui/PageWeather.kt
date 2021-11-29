package com.karimsinouh.onBoarding.ui.theme.onBoarding

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.load.engine.Resource
import com.example.city_weather.R
import com.example.city_weather.api.Weather
import com.example.city_weather.model.Forecast
import com.example.city_weather.model.WeatherModel
import com.example.city_weather.ui_programm.navigation.Screen
import com.example.city_weather.viewmodel.WeatherViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.material.Icon as Icon

@ExperimentalPagerApi
@Composable
fun PageWeather(
    navController: NavController,
    viewModel: WeatherViewModel = viewModel(),
    city: String = viewModel.searchCity.value
) {
    val getAllWeatherData = viewModel.getWeatherData(city)?.data?.observeAsState()
    val weatherState = viewModel.getWeatherData.value
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val isloading = Weather.loading.value


    val state = rememberPagerState(pageCount = 1)


    if (isloading) {
        CircularProgressBar()
        TopSection(navController)

    }
    if (!isloading) {
        Column {
            HorizontalPager(
                state = state,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.7f)
            ) { page ->
                OnPageWeatherItem(getAllWeatherData?.value)
            }
        }
        TopSection(navController)
        Box(
            modifier = Modifier
                .padding(top = 130.dp)
                .fillMaxWidth()
        ) {
            HorizontalPagerIndicator(
                pagerState = state,
                modifier = Modifier.align(TopCenter),
                activeColor = colorResource(R.color.white),
                inactiveColor = colorResource(android.R.color.darker_gray)
            )
        }
    }
//    }
    if (weatherState.isLoading == true) {
        CircularProgressBar()
    }
    if (!weatherState.error.isNullOrBlank()) {
        Text(text = weatherState.error)
    }

}

@Composable
fun TopSection(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        IconButton(onClick = { navController.navigate(route = Screen.AddNewCity.rout) })
        {
            Icon(
                Icons.Filled.Menu,
                contentDescription = "Меню",
                tint = colorResource(R.color.white)
            )
        }
    }
}


@Composable
fun BottomSectionofHorizontalPager(
    size: Int,
    index: Int,
    onNextClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Indicators(size = size, index = index)

        FloatingActionButton(
            onClick = onNextClicked,
            modifier = Modifier.align(CenterEnd),
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        ) {
            Icon(Icons.Outlined.KeyboardArrowRight, null)
        }
    }
}

@Composable
fun BottomSection(
    item: OnBoardingItem
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        items(7) {
            Row {
                Image(
                    painter = painterResource(item.icon),
                    contentDescription = null,
                    Modifier.size(40.dp)
                )
                Text(
                    text = stringResource(item.text),
                    color = Color.White
                )
            }

        }
    }
}


@Composable
fun BoxScope.Indicators(size: Int, index: Int) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) MaterialTheme.colors.primary
                else MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
            )
    ) {

    }
}


@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState")
@Composable
fun OnPageWeatherItem(
    weatherViewModel: WeatherModel?,
    viewModel: WeatherViewModel = viewModel()
) {

    Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(R.color.background_of_city2),
                        colorResource(R.color.background1)
                    )
                )
            )
            .fillMaxSize()
    )
    {
        Text(
            modifier = Modifier
                .align(TopCenter)
                .padding(top = 35.dp),
            text = "${weatherViewModel?.location?.country}\n ${weatherViewModel?.location?.city}",
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }

    //CenterText
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.padding(end = 14.dp)
        ) {
            Text(
                text = "${weatherViewModel?.currentObservation?.condition?.temperature}°",
                color = Color.White,
                fontSize = 88.sp,
                textAlign = TextAlign.Center
            )
            Column {
                Text(
                    text = weatherViewModel?.currentObservation?.condition?.text.toString(),
                    color = Color.White,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "↑ ${weatherViewModel?.forecasts?.get(0)?.high.toString()}°C",
                    color = Color.White,
                    fontSize = 22.sp
                )
                Text(
                    text = "↓ ${weatherViewModel?.forecasts?.get(0)?.low.toString()}°C",
                    color = Color.White,
                    fontSize = 22.sp
                )
            }
        }
    }


    //Bottom Section
    Box(
        contentAlignment = BottomCenter,
        modifier = Modifier.fillMaxSize()
    )
    {
        Column(modifier = Modifier.background(Color.White)) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                weatherViewModel?.forecasts?.size?.let {
                    items(it) { index ->
                        if (index == 0) weatherViewModel.forecasts[0].day = "Today"
                        WeatherListItem(forecast = weatherViewModel.forecasts[index])
                    }
                }
            }
        }

    }

}


@Composable
fun CircularProgressBar() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(R.color.background_of_city2),
                        colorResource(R.color.background1)
                    )
                )
            )
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = Color.White
        )

        Text(
            text = "Loading...",
            color = Color.White,
            fontSize = 26.sp
        )
    }
}


@Composable
fun WeatherListItem(
    forecast: Forecast,
    viewModel: WeatherViewModel = viewModel()
) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {

        Text(
            text = forecast.day,
            color = Color.Black,
            modifier = Modifier
                .alpha(0.6f)
                .align(Alignment.CenterHorizontally)
        )

        Image(
            bitmap = ImageBitmap.imageResource(Weather.getWeatherText(forecast.text)),
            contentDescription = "Text",
            Modifier
                .size(50.dp)
                .align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = forecast.high.toString() + "°  ",
                color = Color.Black,
                modifier = Modifier.alpha(0.9f)
            )
            Text(
                text = forecast.low.toString() + "°",
                color = Color.Black,
                modifier = Modifier.alpha(0.5f)
            )
        }
    }
}