package my.supa.lamp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private CameraManager camera;
    private String cameraId;
    private boolean flashlight;
    private ImageView flashlightImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        camera = getSystemService(CameraManager.class);
        cameraId = findFlash();
        flashlightImage = findViewById(R.id.flashlightImage);
        flashlightImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
    }

    private String findFlash() {
        try {
            for (String id : camera.getCameraIdList()) {
                CameraCharacteristics c = camera.getCameraCharacteristics(id);
                Boolean available = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                if (available != null && available) {
                    return id;
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    private void torch(boolean on) {
        try {
            if (cameraId != null) {
                camera.setTorchMode(cameraId, on);
            }
        } catch (Exception ignored) {
        }
    }

    private void toggle() {
        if (cameraId == null) return;
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 0);
            return;
        }
        flashlight = !flashlight;
        torch(flashlight);
        flashlightImage.setImageResource(flashlight ? R.drawable.flashlight_on : R.drawable.flashlight_off);
    }

    @Override
    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        super.onRequestPermissionsResult(code, permissions, results);
        if (results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED) {
            toggle();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (flashlight) {
            torch(false);
            flashlight = false;
            flashlightImage.setImageResource(R.drawable.flashlight_off);
        }
    }
}
