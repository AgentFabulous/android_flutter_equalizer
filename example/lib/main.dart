import 'package:flutter/material.dart';
import 'dirac.dart';
import 'mi.dart';

var EX_TYPE = 1;
void main() => runApp(EX_TYPE == 1?MiApp():DiracApp());