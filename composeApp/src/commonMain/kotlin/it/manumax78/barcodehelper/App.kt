package it.manumax78.barcodehelper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import it.manumax78.barcodehelper.resources.Res
import it.manumax78.barcodehelper.resources.placeholder_body
import it.manumax78.barcodehelper.resources.placeholder_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().safeContentPadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(stringResource(Res.string.placeholder_title), style = MaterialTheme.typography.headlineMedium)
            Text(stringResource(Res.string.placeholder_body))
        }
    }
}
