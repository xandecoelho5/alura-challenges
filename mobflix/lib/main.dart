import 'package:flutter/material.dart';
import 'package:mobflix/screens/main_screen.dart';
import 'package:mobflix/utils/constants.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Mobflix',
      themeMode: ThemeMode.dark,
      theme: ThemeData.dark().copyWith(
        scaffoldBackgroundColor: kBackgroundColor,
      ),
      home: const MainScreen(),
      debugShowCheckedModeBanner: false,
    );
  }
}
