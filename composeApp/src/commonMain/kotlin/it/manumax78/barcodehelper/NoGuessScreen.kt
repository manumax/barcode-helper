package it.manumax78.barcodehelper

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import it.manumax78.barcodehelper.resources.Res
import it.manumax78.barcodehelper.resources.no_guess
import org.jetbrains.compose.resources.stringResource

/** The No Guess Result Screen. Tap anywhere to start a new Scan. */
@Composable
fun NoGuessScreen(onDismiss: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).clickable(onClick = onDismiss).safeContentPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.no_guess), color = Color.Black, fontSize = 28.sp, textAlign = TextAlign.Center)
    }
}
