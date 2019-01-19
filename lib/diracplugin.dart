import 'dart:async';

import 'package:flutter/services.dart';

class Dirac {
  static const MethodChannel _channel = const MethodChannel('dirac');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<void> diracInit() async {
    await _channel.invokeMethod('diracInit');
  }

  static Future<bool> getMusic() async {
    final int enabled = await _channel.invokeMethod('getMusic');
    return enabled == 1;
  }

  static Future<int> getHeadsetType() async {
    final int type = await _channel.invokeMethod('getHeadsetType');
    return type;
  }

  static Future<int> getHifiMode() async {
    final int mode = await _channel.invokeMethod('getHifiMode');
    return mode;
  }

  static Future<bool> getMovie() async {
    final int enable = await _channel.invokeMethod('getMovie');
    return enable == 1;
  }

  static Future<int> getMovieMode() async {
    final int mode = await _channel.invokeMethod('getMovieMode');
    return mode;
  }

  static Future<int> getSpeakerStereoMode() async {
    final int mode = await _channel.invokeMethod('getSpeakerStereoMode');
    return mode;
  }

  static Future<double> getLevel(int band) async {
    final double level = await _channel
        .invokeMethod('getLevel', <String, dynamic>{'band': band});
    return level;
  }

  static Future<void> setMusic(bool enable) async {
    await _channel
        .invokeMethod('setMusic', <String, dynamic>{'enable': enable});
  }

  static Future<void> setLevel(int band, double level) async {
    await _channel.invokeMethod(
        'setLevel', <String, dynamic>{'band': band, 'level': level});
  }

  static Future<void> setHeadsetType(int type) async {
    await _channel
        .invokeMethod('setHeadsetType', <String, dynamic>{'type': type});
  }

  static Future<void> setMovie(bool enable) async {
    await _channel
        .invokeMethod('setMovie', <String, dynamic>{'enable': enable});
  }

  static Future<void> setMovieMode(int mode) async {
    await _channel
        .invokeMethod('setMovieMode', <String, dynamic>{'mode': mode});
  }

  static Future<void> setSpeakerStereoMode(int mode) async {
    await _channel
        .invokeMethod('setSpeakerStereoMode', <String, dynamic>{'mode': mode});
  }

  static Future<void> setHifiMode(int mode) async {
    await _channel.invokeMethod('setHifiMode', <String, dynamic>{'mode': mode});
  }
}
