package it.manumax78.barcodehelper

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultScreen(number: String, onDismiss: () -> Unit) {
    val modules = Ean13.modules(number)
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).clickable(onClick = onDismiss).safeContentPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Canvas(Modifier.fillMaxWidth().aspectRatio(modules.size / 40f)) {
            val w = size.width / modules.size
            modules.forEachIndexed { i, dark ->
                if (dark) drawRect(Color.Black, Offset(i * w, 0f), Size(w + 0.5f, size.height))
            }
        }
        Text(
            number,
            color = Color.Black,
            fontSize = 32.sp,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(top = 16.dp).height(48.dp),
        )
    }
}
