package com.itssagnikmukherjee.splashscreen.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.itssagnikmukherjee.splashscreen.MainActivity
import com.itssagnikmukherjee.splashscreen.R
import com.itssagnikmukherjee.splashscreen.ui.theme.btnColor
import com.itssagnikmukherjee.splashscreen.ui.theme.mukta
import com.itssagnikmukherjee.splashscreen.ui.theme.myBGC
import com.itssagnikmukherjee.splashscreen.ui.theme.txtColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(nav: NavController,context: MainActivity) {
    val pagerState = rememberPagerState()
    val list = getList()
    val scope = rememberCoroutineScope()
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .offset(x = -10.dp, y = 40.dp)
            .zIndex(2f),
        horizontalArrangement = Arrangement.End,
    ){
        Button(onClick = {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage+2)
            }
        }, colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ), modifier = Modifier.zIndex(2f)){
        Text(text = "SKIP", fontFamily = mukta, fontSize = 16.sp, fontWeight = FontWeight.Light, color = btnColor)
        }
    }
    Column (
        modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        HorizontalPager(count = list.size, state = pagerState, modifier = Modifier
            .weight(1f)
            .padding(10.dp).background(Color.White)) {
            val composition by rememberLottieComposition(list[it].image)
            Column(
                modifier = Modifier.fillMaxSize(.8f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = "${list[it].title}", fontFamily = mukta, fontWeight = FontWeight.ExtraBold, fontSize = 38.sp, modifier = Modifier.fillMaxHeight(0.2f), lineHeight = 40.sp, color = txtColor)
                LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever, modifier = Modifier.fillMaxHeight(0.5f))
                Text(text = "${list[it].description}", fontFamily = mukta, modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .padding(top = 50.dp), style = TextStyle(
                    textAlign = TextAlign.Justify,
                    fontSize = 16.sp,
                        color = txtColor
                ))

            }

        }
        Button(onClick = {
            scope.launch {
                if (pagerState.currentPage == 3){
                    onBoardingIsFinished(context)
                nav.navigate("loginreg")
                }else{
                pagerState.animateScrollToPage(pagerState.currentPage+1)
                }
            }
        }, modifier = Modifier.offset(y = -130.dp).width(150.dp), colors = ButtonDefaults.buttonColors(
            containerColor = btnColor,
            contentColor = Color.White
        )) {
            if (pagerState.currentPage==list.size-1){
                Icon(imageVector = Icons.Filled.Check, contentDescription = "",Modifier.offset(-5.dp))
                Text(text = "Continue", fontSize = 16.sp, modifier = Modifier.offset(5.dp))
            }
            else{
                Text(text = "Next", fontSize = 16.sp, modifier = Modifier.offset(-5.dp))
                Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "", modifier = Modifier.offset(5.dp))
            }
        }
        HorizontalPagerIndicator(
            activeColor = btnColor,
            pagerState=pagerState,
            pageCount = list.size,
            indicatorWidth = 20.dp,
            indicatorHeight = 15.dp,
            modifier = Modifier.offset(0.dp,-70.dp)
        )
    }
}

data class PagerContent(
    val image: LottieCompositionSpec,
    val title: String,
    val description: String,
    val bgColor: Color
)

fun getList():List<PagerContent>{
    return listOf(
        PagerContent(
            LottieCompositionSpec.RawRes(R.raw.onboarding1),
            "Unified Dashboard",
            "A centralized dashboard that provides a comprehensive overview of all ongoing projects, status updates, deadlines, and inter-departmental communications",
            myBGC
        ),
        PagerContent(LottieCompositionSpec.RawRes(R.raw.onboarding2), "Collaborative Planning", "Tools that enable multiple departments to collaborate on project planning, including Gantt charts, task management, and timeline planning for synchronized implementation",
            myBGC
        ),
        PagerContent(LottieCompositionSpec.RawRes(R.raw.onboarding3), "Blockchain Storage", "for secure and immutable record-keeping of data and transactions, ensures transparency, integrity, and tamper-proof documentation of all inter-departmental activities",
            myBGC
        ),
        PagerContent(LottieCompositionSpec.RawRes(R.raw.onboarding4), "AI Insights & Analytics", "AI-powered analytics that provide insights on project performance, potential delays, and resource utilization, helping in proactive decision-making and problem-solving.",
            myBGC
        ),
    )
}

private fun onBoardingIsFinished(context:MainActivity){
    val sharedPref = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    val editor = sharedPref.edit()
    editor.putBoolean("isFinished",true)
    editor.apply()
}