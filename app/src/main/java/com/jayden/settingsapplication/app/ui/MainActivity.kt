package com.jayden.settingsapplication.app.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Row
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jayden.settingsapplication.app.MainApplication
import com.jayden.settingsapplication.app.viewmodel.MainViewModel
import com.jayden.settingsapplication.copy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

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
        Row(modifier = Modifier.safeDrawingPadding()
        ) {
            val state by viewModel.settingsUiState.collectAsStateWithLifecycle()
            SettingToggle(
                title = "Allow tracking?",
                state = state.tracking,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onCheck = viewModel::setTracking
            )
        }

    }

    @Composable
    fun SettingToggle(
        title: String,
        state: Boolean,
        onCheck: ((Boolean) -> Unit)? = null,
        modifier: Modifier = Modifier,
    ) {

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
                        onCheck?.invoke(!state)
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
}