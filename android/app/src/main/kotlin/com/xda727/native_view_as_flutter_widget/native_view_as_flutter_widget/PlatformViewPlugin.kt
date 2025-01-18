package com.xda727.native_view_as_flutter_widget.native_view_as_flutter_widget

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding

class PlatformViewPlugin : FlutterPlugin {
    override fun onAttachedToEngine(binding: FlutterPluginBinding) {
        binding.platformViewRegistry.registerViewFactory(
                "@views/native-view",
                NativeViewFactory()
            )
    }

    override fun onDetachedFromEngine(binding: FlutterPluginBinding) {}
}