package com.itssagnikmukherjee.splashscreen.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.itssagnikmukherjee.splashscreen.R
import com.itssagnikmukherjee.splashscreen.ui.theme.myGrey
import com.itssagnikmukherjee.splashscreen.ui.theme.outfit

@Composable
fun ComplaintSubmitted(name: String,location:String,pin: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.submitted))
        Column {
        Text("Thank You ! \n$name", fontSize = 36.sp, fontFamily = outfit, fontWeight = FontWeight.Bold, color = myGrey, modifier = Modifier
            .height(120.dp)
            .padding(top = 20.dp))
        Text(text = "Your complaint has been submitted to the local councillor", fontFamily = outfit, fontSize = 18.sp, color = myGrey)
        Row (
            modifier = Modifier.padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(painter = painterResource(id = R.drawable.location), contentDescription = "", tint = myGrey, modifier = Modifier.size(50.dp).padding(top = 25.dp))
            Text(text = "$location | $pin", fontFamily = outfit, fontSize = 18.sp, color = myGrey, textAlign = TextAlign.End, modifier = Modifier.padding(top = 30.dp))
        }
        }
        LottieAnimation(composition = composition, modifier = Modifier.size(500.dp))
        androidx.compose.material3.Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = myGrey),
            modifier = Modifier
                .fillMaxWidth(.9f)
                .height(60.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = "BACK TO HOME", color = Color.White, fontFamily = outfit, fontSize = 16.sp)
        }
    }
}