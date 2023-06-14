package kenali77.projects.hwchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kenali77.projects.hwchallenge.ui.home.HomeScreen
import kenali77.projects.hwchallenge.ui.navigation.MainNavGraph
import kenali77.projects.hwchallenge.ui.theme.HWChallengeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HWChallengeTheme {
                val navHostController = rememberNavController()
                MainNavGraph(navHostController = navHostController )
            }
        }
    }
}