package com.developer.android.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developer.android.businesscard.ui.theme.BusinessCardTheme
import com.developer.android.businesscard.ui.theme.GreenAndroid
import com.developer.android.businesscard.ui.theme.BlueLogo
import com.developer.android.businesscard.ui.theme.GreenBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = GreenBackground
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(modifier = Modifier)
            Spacer(modifier = Modifier)
            Title()
            Spacer(modifier = Modifier)
            Contact(modifier = Modifier.padding(top = 32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    BusinessCardTheme {
        MyApp()
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.android_logo)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.background(color = BlueLogo)
        ) {
            Image(
                painter = image,
                contentDescription = "Android logo",
                modifier = Modifier.size(120.dp)
            )
        }
        Text(
            text = stringResource(R.string.name_text),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
        )
        Text(
            text = stringResource(R.string.title_text),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier,
            color = GreenAndroid
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitlePreview() {
    BusinessCardTheme {
        Title()
    }
}

@Composable
fun Contact(modifier: Modifier = Modifier) {
    Column {
        Row {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Github Icon",
                modifier = Modifier.size(24.dp).padding(end = 8.dp),
                tint = GreenAndroid
            )
            Text(text = "@abe-rb")
        }
        Row {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email Icon",
                modifier = Modifier.size(24.dp).padding(end = 8.dp),
                tint = GreenAndroid
            )
            Text(text = "abrahamrb@outlook.com.au")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactPreview() {
    BusinessCardTheme {
        Contact()
    }
}