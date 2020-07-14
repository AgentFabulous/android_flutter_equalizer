import 'dart:async';

import 'package:effectsplugin/effectsplugin.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class MiApp extends StatefulWidget {
  @override
  _MiAppState createState() => _MiAppState();
}

class _MiAppState extends State<MiApp> {
  bool music = false;
  bool movie = false;
  int headsetType;
  int movieMode;
  int hifiMode;
  int stereoMode;
  Timer timer;
  List<double> b = new List(7);
  List<String> profiles = new List();
  bool headsetRunning = false;
  bool profileRunning = false;

  @override
  void initState() {
    super.initState();
    initData();
    profiles.addAll([
      "0,0,0,0,0,0,0",
      "4,2,-2,0,-2,-2,4",
      "0,0,0,-2,-3,0,0",
      "0,-3,-5,0,0,-3,0",
      "0,0,0,0,3,6,6",
      "3,3,-3,0,-3,0,2",
      "2,4,-6,4,0,1,2",
      "3,3,-1,0,-3,0,0",
      "0,0,-2,-2,2,2,0",
      "0,4,2,0,-2,-2,4",
      "2,0,0,-2,-4,0,0"
    ]);
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initData() async {
    try {
      print(await FX.mMi.isMiSupported());
      music = await FX.mMi.getMusic();
      movie = await FX.mMi.getMovie();
      headsetType = await FX.mMi.getHeadsetType();
      movieMode = await FX.mMi.getMovieMode();
      hifiMode = await FX.mMi.getHifiMode();
      stereoMode = await FX.mMi.getSpeakerStereoMode();
      for (int i = 0; i < 7; i++) b[i] = await FX.mMi.getLevel(i);
    } on PlatformException {}

    if (!mounted) return;
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text('FX.mMi enabled: $music'),
            Text('Movie: $movie'),
            Text('HeadsetType: $headsetType'),
            Text('MovieMode: $movieMode'),
            Text('HifiMode: $hifiMode'),
            Text('speakerStereoMode: $stereoMode'),
            Text('Band values:'),
            ListView.builder(
                shrinkWrap: true,
                itemCount: 7,
                itemBuilder: (context, i) {
                  return Center(child: Text("band $i: ${b[i]}"));
                }),
            Text("Headset test: $headsetRunning"),
            Text("Profile test: $profileRunning"),
            MaterialButton(
              child: Text("Headset test"),
              onPressed: () {
                setState(() {
                  headsetRunning = true;
                });
                int i = 0;
                timer = Timer.periodic(Duration(seconds: 3), (Timer t) {
                  if (i == 23) timer.cancel();
                  FX.mMi.setHeadsetType(i++).then((value) {
                    setState(() {
                      FX.mMi.getHeadsetType().then((value) {
                        headsetType = value;
                      });
                    });
                  });
                  if (!timer.isActive) {
                    setState(() {
                      headsetRunning = false;
                    });
                  }
                });
              },
            ),
            MaterialButton(
              child: Text("Profile Test"),
              onPressed: () {
                setState(() {
                  profileRunning = true;
                });
                int i = 0;
                timer = Timer.periodic(Duration(seconds: 3), (Timer t) {
                  if (i == profiles.length - 1) timer.cancel();
                  print("Enabling profile $i");
                  List<String> bands = profiles[i++].split(',');
                  for (int j = 0; j < 7; j++) {
                    FX.mMi
                        .setLevel(j, double.parse(bands[j]))
                        .then((value) {});
                    if (!mounted) continue;
                    FX.mMi.getLevel(j).then((value) {
                      setState(() {
                        b[j] = value;
                      });
                    });
                  }
                  if (!timer.isActive) {
                    setState(() {
                      profileRunning = false;
                    });
                  }
                });
              },
            ),
            MaterialButton(
              child: Text("Movie test"),
              onPressed: () {
                print("movie: ${!movie}");
                FX.mMi.setMovie(!movie).then((value) {
                  FX.mMi.getMovie().then((value) {
                    setState(() {
                      movie = value;
                    });
                  });
                });
              },
            ),
            Builder(
              builder: (context) => MaterialButton(
                child: Text("Stereo test"),
                onPressed: () {
                  FX.mMi
                      .setSpeakerStereoMode(stereoMode == 0 ? 1 : 0)
                      .then((value) {
                    FX.mMi.getSpeakerStereoMode().then((value) {
                      setState(() {
                        if (value == stereoMode)
                          showSnackBar(context,
                              "Test failure! Possibly unsupported.");
                        else
                          stereoMode = value;
                      });
                    });
                  });
                },
              ),
            ),
            Builder(
              builder: (context) => MaterialButton(
                child: Text("HifiMode test"),
                onPressed: () {
                  FX.mMi
                      .setHifiMode(hifiMode == 0 ? 1 : 0)
                      .then((value) {
                    FX.mMi.getHifiMode().then((value) {
                      setState(() {
                        if (value == hifiMode)
                          showSnackBar(context,
                              "Test failure! Possibly unsupported.");
                        else
                          hifiMode = value;
                      });
                    });
                  });
                },
              ),
            )
          ],
        ),
        floatingActionButton: FloatingActionButton(
            child: music ? Text("Disable") : Text("Enable"),
            onPressed: () {
              print("Setting ${!music}");
              FX.mMi.setMusic(!music);
              initData();
            }),
      ),
    );
  }

  void showSnackBar(BuildContext context, String message) {
    Scaffold.of(context).showSnackBar(SnackBar(content: Text(message)));
  }
}