import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class PlatformView extends StatelessWidget {
  const PlatformView({super.key});

  @override
  Widget build(BuildContext context) {
    const String viewType = '@views/native-view';
    const Map<String, dynamic> creationParams = <String, dynamic>{'text': 'Value from flutter'};

    if (Platform.isAndroid) {
      return AndroidView(
        viewType: viewType,
        layoutDirection: TextDirection.ltr,
        creationParams: creationParams,
        creationParamsCodec: const StandardMessageCodec(),
      );
    }

    return Text('Not available in this platform');
  }
}
