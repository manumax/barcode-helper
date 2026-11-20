package it.manumax78.barcodehelper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import it.manumax78.barcodehelper.resources.Res
import it.manumax78.barcodehelper.resources.scanning
import org.jetbrains.compose.resources.stringResource

@Composable
fun App() {
    MaterialTheme {
        val scan = remember { ScanState() }
        val guess = scan.guess
        if (guess != null) {
            ResultScreen(guess, onDismiss = scan::dismiss)
        } else {
            // Restarting the stub on each new Scan (guess back to null).
            LaunchedEffect(Unit) { ReadingStub.emit(onReading = scan::onReading) }
            Column(
                modifier = Modifier.fillMaxSize().safeContentPadding(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(stringResource(Res.string.scanning), style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}
