package my.supa.lamp

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private fun CameraManager.findFlash(): String? = runCatching {
    cameraIdList.firstOrNull { id ->
        getCameraCharacteristics(id)
            .get(CameraCharacteristics.FLASH_INFO_AVAILABLE) == true
    }
}.getOrNull()

private fun CameraManager.torch(id: String, on: Boolean) {
    runCatching { setTorchMode(id, on) }
}

class MainActivity : ComponentActivity() {
    private val camera by lazy { getSystemService(CameraManager::class.java) }
    private var cameraId: String? = null
    private var flashlight by mutableStateOf(false)

    private val permission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> if (granted) toggle() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraId = camera.findFlash()

        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .clickable { toggle() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (flashlight) Icons.Filled.FlashOn else Icons.Filled.FlashOff,
                    contentDescription = "Flashlight",
                    modifier = Modifier.size(200.dp),
                    tint = if (flashlight) Color(0xFFFFD600) else Color(0xFF757575)
                )
            }
        }
    }

    private fun toggle() {
        val id = cameraId ?: return
        if (checkSelfPermission(Manifest.permission.CAMERA) != PERMISSION_GRANTED) {
            permission.launch(Manifest.permission.CAMERA)
            return
        }
        flashlight = !flashlight
        camera.torch(id, flashlight)
    }

    override fun onPause() {
        super.onPause()
        if (flashlight) {
            cameraId?.let { camera.torch(it, false) }
            flashlight = false
        }
    }
}
