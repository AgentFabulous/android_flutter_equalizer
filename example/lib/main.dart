import 'dart:async';

import 'package:dirac/dirac.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
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
      music = await Dirac.getMusic();
      movie = await Dirac.getMovie();
      headsetType = await Dirac.getHeadsetType();
      movieMode = await Dirac.getMovieMode();
      hifiMode = await Dirac.getHifiMode();
      stereoMode = await Dirac.getSpeakerStereoMode();
      for (int i = 0; i < 7; i++) b[i] = await Dirac.getLevel(i);
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
            Text('Dirac enabled: $music'),
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
                  Dirac.setHeadsetType(i++).then((value) {
                    setState(() {
                      Dirac.getHeadsetType().then((value) {
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
                    Dirac.setLevel(j, double.parse(bands[j])).then((value) {});
                    if (!mounted) continue;
                    Dirac.getLevel(j).then((value) {
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
                Dirac.setMovie(!movie).then((value) {
                  Dirac.getMovie().then((value) {
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
                      Dirac.setSpeakerStereoMode(stereoMode == 0 ? 1 : 0)
                          .then((value) {
                        Dirac.getSpeakerStereoMode().then((value) {
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
                      Dirac.setHifiMode(hifiMode == 0 ? 1 : 0).then((value) {
                        Dirac.getHifiMode().then((value) {
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
              Dirac.setMusic(!music);
              initData();
            }),
      ),
    );
  }

  void showSnackBar(BuildContext context, String message) {
    Scaffold.of(context).showSnackBar(SnackBar(content: Text(message)));
  }
}
