package edu.temple.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.temple.counterapp.ui.theme.CounterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val counterViewModel by viewModels<CounterViewModel>() //does lazy instantiation for us

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Counter(
                        modifier = Modifier.padding(innerPadding),
                        counterViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Counter(modifier: Modifier = Modifier, viewModel: CounterViewModel) {

//    var counterValue by rememberSaveable() { mutableIntStateOf(0) }
        //by access directly, if you do = then the value is internal, so counterValue.value
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Button({ viewModel.counterValue.value++ }) {
            Text("Increase")
        }
        Text(
            text = viewModel.counterValue.value.toString(),
            modifier = modifier,
            fontSize = 30.sp
        )
        Button({ if(viewModel.counterValue.value>0) viewModel.counterValue.value--}) {
            Text("Decrease")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CounterAppTheme {
        Counter()
    }
}