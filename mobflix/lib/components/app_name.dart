import 'package:flutter/material.dart';
import 'package:mobflix/utils/constants.dart';

class AppName extends StatelessWidget {
  const AppName({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const Text(
      'MOBFLIX',
      style: TextStyle(
        fontFamily: 'Bebas Neue',
        color: kMainBlueColor,
        fontSize: 32,
      ),
    );
  }
}
