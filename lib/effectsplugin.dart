import 'dart:async';

import 'package:flutter/services.dart';

enum EFFECT_TYPE {
  NONE,
  DIRAC,
  MI,
}

class FX {
  static Effect mDirac = new Effect(type: EFFECT_TYPE.DIRAC);
  static Effect mMi = new Effect(type: EFFECT_TYPE.MI);
  static Effect mEqualizer = new Effect(type: EFFECT_TYPE.NONE);
}

class Effect {
  static const MethodChannel _channel =
      const MethodChannel('audio_effects_flutter/methods');
  final EFFECT_TYPE type;

  Effect({this.type});

  Future<void> init() async {
    await _channel.invokeMethod('init', <String, dynamic>{'type': type.index});
  }

  Future<bool> getMusic() async {
    final int enabled = await _channel
        .invokeMethod('getMusic', <String, dynamic>{'type': type.index});
    return enabled == 1;
  }

  Future<double> getLevel(int band) async {
    final double level = await _channel.invokeMethod(
        'getLevel', <String, dynamic>{'band': band, 'type': type.index});
    return level;
  }

  Future<int> getNumBands() async {
    return await _channel
        .invokeMethod('getNumBands', <String, dynamic>{'type': type.index});
  }

  Future<void> setMusic(bool enable) async {
    await _channel.invokeMethod(
        'setMusic', <String, dynamic>{'enable': enable, 'type': type.index});
  }

  Future<void> setLevel(int band, double level) async {
    await _channel.invokeMethod('setLevel',
        <String, dynamic>{'band': band, 'level': level, 'type': type.index});
  }

  // Custom FX Additions
  Future<bool> isDiracSupported() async {
    if (type == EFFECT_TYPE.NONE) return false;
    final bool supported = await _channel.invokeMethod("isDiracSupported");
    return supported;
  }
  Future<bool> isMiSupported() async {
    if (type == EFFECT_TYPE.NONE) return false;
    final bool supported = await _channel.invokeMethod("isMiSupported");
    return supported;
  }

  Future<int> getHeadsetType() async {
    if (type == EFFECT_TYPE.NONE) return 0;
    final int hType = await _channel
        .invokeMethod('getHeadsetType', <String, dynamic>{'type': type.index});
    return hType;
  }

  Future<int> getHifiMode() async {
    if (type == EFFECT_TYPE.NONE) return 0;
    final int mode = await _channel
        .invokeMethod('getHifiMode', <String, dynamic>{'type': type.index});
    return mode;
  }

  Future<bool> getMovie() async {
    if (type == EFFECT_TYPE.NONE) return false;
    final int enable = await _channel
        .invokeMethod('getMovie', <String, dynamic>{'type': type.index});
    return enable == 1;
  }

  Future<int> getMovieMode() async {
    if (type == EFFECT_TYPE.NONE) return 0;
    final int mode = await _channel
        .invokeMethod('getMovieMode', <String, dynamic>{'type': type.index});
    return mode;
  }

  Future<int> getSpeakerStereoMode() async {
    if (type == EFFECT_TYPE.NONE) return 0;
    final int mode = await _channel
        .invokeMethod('getSpeakerStereoMode', <String, dynamic>{'type': type.index});
    return mode;
  }

  Future<void> setHeadsetType(int hType) async {
    if (type == EFFECT_TYPE.NONE) return;
    await _channel.invokeMethod(
        'setHeadsetType', <String, dynamic>{'hType': hType, 'type': type.index});
  }

  Future<void> setMovie(bool enable) async {
    if (type == EFFECT_TYPE.NONE) return;
    await _channel.invokeMethod(
        'setMovie', <String, dynamic>{'enable': enable, 'type': type.index});
  }

  Future<void> setMovieMode(int mode) async {
    if (type == EFFECT_TYPE.NONE) return;
    await _channel.invokeMethod(
        'setMovieMode', <String, dynamic>{'mode': mode, 'type': type.index});
  }

  Future<void> setSpeakerStereoMode(int mode) async {
    if (type == EFFECT_TYPE.NONE) return;
    await _channel.invokeMethod(
        'setSpeakerStereoMode', <String, dynamic>{'mode': mode, 'type': type.index});
  }

  Future<void> setHifiMode(int mode) async {
    if (type == EFFECT_TYPE.NONE) return;
    await _channel.invokeMethod(
        'setHifiMode', <String, dynamic>{'mode': mode, 'type': type.index});
  }

  Future<void> release() async {
    await _channel.invokeMethod('release', <String, dynamic>{'type': type.index});
  }
}
