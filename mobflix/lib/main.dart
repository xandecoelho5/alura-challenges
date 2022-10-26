import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:mobflix/controllers/video_controller.dart';
import 'package:mobflix/screens/main_screen.dart';
import 'package:mobflix/services/category_mock_service.dart';
import 'package:mobflix/services/dio_service.dart';
import 'package:mobflix/services/video_mock_service.dart';
import 'package:mobflix/utils/constants.dart';
import 'package:provider/provider.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        Provider(create: (_) => Dio()),
        Provider(
          create: (ctx) => DioService(Provider.of<Dio>(ctx, listen: false)),
        ),
        ChangeNotifierProvider(create: (_) => VideoMockService()),
        ChangeNotifierProvider(create: (_) => CategoryMockService()),
        Provider(
          create: (ctx) => VideoController(
            Provider.of<VideoMockService>(ctx, listen: false),
            Provider.of<DioService>(ctx, listen: false),
          ),
        ),
      ],
      child: MaterialApp(
        title: 'Mobflix',
        themeMode: ThemeMode.dark,
        theme: ThemeData.dark().copyWith(
          scaffoldBackgroundColor: kBackgroundColor,
        ),
        home: const MainScreen(),
        debugShowCheckedModeBanner: false,
      ),
    );
  }
}
