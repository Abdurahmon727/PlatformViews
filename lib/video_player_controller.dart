import 'package:flutter/services.dart';

class VideoPlayerViewController {
  VideoPlayerViewController._(int id)
      : _channel = MethodChannel('plugins.udevs/video_player_view_$id');

  final MethodChannel _channel;

  Future<void> setUrl({
    required String url,
  }) async =>
      _channel.invokeMethod(
        'setUrl',
        {
          'url': url,
        },
      );

  Future<void> setAssets({
    required String assets,
  }) async {
    await _channel.invokeMethod(
      'setAssets',
      {
        'url': assets,
      },
    );
  }
}
