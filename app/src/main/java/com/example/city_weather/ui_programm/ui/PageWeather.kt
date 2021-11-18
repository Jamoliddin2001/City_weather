package com.karimsinouh.onBoarding.ui.theme.onBoarding

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
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
import com.example.city_weather.R
import com.example.city_weather.model.WeatherModel
import com.example.city_weather.ui_programm.navigation.Screen
import com.example.city_weather.viewmodel.WeatherViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.material.Icon as Icon

@ExperimentalPagerApi
@Composable
fun PageWeather(
    navController: NavController,
    viewModel: WeatherViewModel= viewModel()
){
    val getAllWeatherData = viewModel.getWeatherData("Dushanbe").data?.observeAsState()

    val state= rememberPagerState(pageCount = 1)
    Column()
    {
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
            .padding(top = 100.dp)
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

@Composable
fun TopSection(
    navController: NavController
) {
    val context = LocalContext.current
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


@Composable
fun OnPageWeatherItem(
    weatherViewModel: WeatherModel?
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
            text = weatherViewModel?.location?.country.toString(),
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ChangTextTitile(
            (weatherViewModel?.location?.city.toString())
        )
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
                items(7) {
                    Column() {
                        Image(
                            bitmap= ImageBitmap.imageResource(R.drawable.iconca1),
                            contentDescription = null,
                            Modifier.size(40.dp)
                        )
                        Text(
                            text = "OK",
                            color = Color.Black,
                            modifier = Modifier.alpha(0.5f)
                        )
                    }

                }
            }
        }

    }

}

@Preview
@Composable
fun CircularProgressBar() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun ChangTextTitile(
    title: String = "Alimuhammad"
) {
    Text(
        text = title,
        color = Color.White,
        fontSize = 60.sp,
        textAlign = TextAlign.Center
    )

}
