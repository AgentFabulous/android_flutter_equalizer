import 'dart:async';

import 'package:flutter/services.dart';

class FX {
  static Effect mDirac = new Effect(dirac: true);
  static Effect mEqualizer = new Effect(dirac: false);
}

class Effect {
  static const MethodChannel _channel = const MethodChannel('potatoEffects');
  bool dirac = false;

  Effect({this.dirac});

  Future<void> init() async {
    await _channel.invokeMethod('init', <String, dynamic>{'dirac': dirac});
  }

  Future<bool> getMusic() async {
    final int enabled = await _channel
        .invokeMethod('getMusic', <String, dynamic>{'dirac': dirac});
    return enabled == 1;
  }

  Future<double> getLevel(int band) async {
    final double level = await _channel.invokeMethod(
        'getLevel', <String, dynamic>{'band': band, 'dirac': dirac});
    return level;
  }

  Future<int> getNumBands() async {
    return await _channel
        .invokeMethod('getNumBands', <String, dynamic>{'dirac': dirac});
  }

  Future<void> setMusic(bool enable) async {
    await _channel.invokeMethod(
        'setMusic', <String, dynamic>{'enable': enable, 'dirac': dirac});
  }

  Future<void> setLevel(int band, double level) async {
    await _channel.invokeMethod('setLevel',
        <String, dynamic>{'band': band, 'level': level, 'dirac': dirac});
  }

  // Dirac Additions
  Future<bool> isDiracSupported() async {
    if (!dirac) return false;
    final bool supported = await _channel.invokeMethod("isDiracSupported");
    return supported;
  }

  Future<int> getHeadsetType() async {
    if (!dirac) return 0;
    final int type = await _channel.invokeMethod('getHeadsetType');
    return type;
  }

  Future<int> getHifiMode() async {
    if (!dirac) return 0;
    final int mode = await _channel.invokeMethod('getHifiMode');
    return mode;
  }

  Future<bool> getMovie() async {
    if (!dirac) return false;
    final int enable = await _channel.invokeMethod('getMovie');
    return enable == 1;
  }

  Future<int> getMovieMode() async {
    if (!dirac) return 0;
    final int mode = await _channel.invokeMethod('getMovieMode');
    return mode;
  }

  Future<int> getSpeakerStereoMode() async {
    if (!dirac) return 0;
    final int mode = await _channel.invokeMethod('getSpeakerStereoMode');
    return mode;
  }

  Future<void> setHeadsetType(int type) async {
    if (!dirac) return;
    await _channel
        .invokeMethod('setHeadsetType', <String, dynamic>{'type': type});
  }

  Future<void> setMovie(bool enable) async {
    if (!dirac) return;
    await _channel
        .invokeMethod('setMovie', <String, dynamic>{'enable': enable});
  }

  Future<void> setMovieMode(int mode) async {
    if (!dirac) return;
    await _channel
        .invokeMethod('setMovieMode', <String, dynamic>{'mode': mode});
  }

  Future<void> setSpeakerStereoMode(int mode) async {
    if (!dirac) return;
    await _channel
        .invokeMethod('setSpeakerStereoMode', <String, dynamic>{'mode': mode});
  }

  Future<void> setHifiMode(int mode) async {
    if (!dirac) return;
    await _channel.invokeMethod('setHifiMode', <String, dynamic>{'mode': mode});
  }

  Future<void> release() async {
    await _channel.invokeMethod('release', <String, dynamic>{'dirac': dirac});
  }
}
