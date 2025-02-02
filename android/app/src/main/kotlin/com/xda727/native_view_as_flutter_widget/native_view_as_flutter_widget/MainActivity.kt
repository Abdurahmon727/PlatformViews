package com.xda727.native_view_as_flutter_widget.native_view_as_flutter_widget

import android.os.Build
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class MainActivity : FlutterActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        flutterEngine
            .platformViewsController
            .registry
            .registerViewFactory(
                "@views/native-view",
                NativeViewFactory()
            )
    }

    override fun onPause() {
        super.onPause()
        val isPip = if (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                isInPictureInPictureMode
            else
                false){

        }else{

        }


    }
}
