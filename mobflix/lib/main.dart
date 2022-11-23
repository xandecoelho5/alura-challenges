import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:mobflix/controllers/video_controller.dart';
import 'package:mobflix/screens/main_screen.dart';
import 'package:mobflix/services/category_sqflite_service.dart';
import 'package:mobflix/services/dio_service.dart';
import 'package:mobflix/services/sqflite_service.dart';
import 'package:mobflix/services/video_sqflite_service.dart';
import 'package:mobflix/utils/constants.dart';
import 'package:provider/provider.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        Provider(create: (_) => Dio()),
        Provider(create: (_) => SqfliteService()),
        Provider(
          create: (ctx) => DioService(Provider.of<Dio>(ctx, listen: false)),
        ),
        ChangeNotifierProvider(
          create: (ctx) => VideoSqfliteService(
            Provider.of<SqfliteService>(ctx, listen: false),
          ),
        ),
        ChangeNotifierProvider(
          create: (ctx) => CategorySqfliteService(
            Provider.of<SqfliteService>(ctx, listen: false),
          ),
        ),
        Provider(
          create: (ctx) => VideoController(
            Provider.of<VideoSqfliteService>(ctx, listen: false),
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
