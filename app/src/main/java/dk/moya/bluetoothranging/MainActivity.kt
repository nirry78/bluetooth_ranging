package dk.moya.bluetoothranging

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dk.moya.bluetoothranging.ui.theme.BluetoothRangingTheme

class MainActivity : ComponentActivity() {
    private var clicks: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BluetoothRangingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BluetoothRangingApp(Message("Android", "Compose"), clicks, onClick = {
                        clicks++
                    })
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun BluetoothRangingApp(
    msg: Message,
    clicks: Int,
    onClick: () -> Unit
) {
    val navController = rememberNavController()
    BluetoothRandingNavHost(
        navController,
        msg,
        clicks,
        onClick
    )
}

@Composable
fun BluetoothRandingNavHost(
    navController: NavHostController,
    msg: Message,
    clicks: Int,
    onClick: () -> Unit
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Greeting(msg, clicks, onClick)
        }
    }
}

@Composable
fun Greeting(msg: Message, clicks: Int, onClick: () -> Unit) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.zac),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp)
            {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.body2
                )
            }

            Button(onClick = onClick) {
                Text("I've been clicked $clicks times")
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun DefaultPreview() {
    BluetoothRangingTheme {
        Greeting(Message("Test", "This is preview test"), 0, onClick = {

        })
    }
}