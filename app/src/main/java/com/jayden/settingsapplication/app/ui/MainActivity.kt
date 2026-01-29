package com.jayden.settingsapplication.app.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jayden.settingsapplication.app.MainApplication
import com.jayden.settingsapplication.app.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels(
        factoryProducer = { (application as MainApplication).viewModelProvider }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
    @Composable
    fun MainScreen() {
        val exampleCounter by viewModel.counterFlow
            .collectAsState(initial = 0)

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val text = createRef()
            val button = createRef()
            Text(
                text = "Counter $exampleCounter",
                fontSize = 25.sp,
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            )

            Button(
                onClick = { viewModel.incrementCounter() },
                modifier = Modifier.constrainAs(button) {
                    top.linkTo(text.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                Text("increment")
            }
        }
    }
}

@Composable
fun SettingToggle(
    title: String,
    onCheck: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier,
    initial: Boolean = false
) {
    var state by remember { mutableStateOf(initial) }

    Card(modifier = modifier.fillMaxWidth()
    ) {
        ConstraintLayout(Modifier.fillMaxSize()) {
            val (text, divider, switch) = createRefs()
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, 4.dp)
                    bottom.linkTo(parent.bottom)
                }
            )

            Switch(
                checked = state,
                onCheckedChange = {
                    state = !state
                    onCheck?.invoke(state)
                },
                modifier = Modifier.constrainAs(switch) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                    uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                    uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                )
            )

            VerticalDivider(modifier = Modifier.constrainAs(divider) {
                top.linkTo(parent.top)
                end.linkTo(switch.start, 6.dp)
                bottom.linkTo(parent.bottom)
            })
        }

    }
}

@Preview(device = "id:pixel_9", showSystemUi = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewCompose() {
    SettingToggle(
        title = "this is a settings toggle please help me",
        modifier = Modifier
            .height(32.dp)
    )
}