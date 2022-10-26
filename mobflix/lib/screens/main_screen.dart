import 'package:flutter/material.dart';
import 'package:mobflix/components/app_name.dart';
import 'package:mobflix/components/category_list.dart';
import 'package:mobflix/screens/register_video_screen.dart';
import 'package:mobflix/services/category_mock_service.dart';
import 'package:provider/provider.dart';

import '../components/highlight_banner.dart';
import '../components/videos_list.dart';
import '../services/video_mock_service.dart';
import '../utils/constants.dart';

class MainScreen extends StatefulWidget {
  const MainScreen({Key? key}) : super(key: key);

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const AppName(),
        elevation: 0,
        backgroundColor: Colors.transparent,
        centerTitle: true,
        toolbarHeight: 45,
      ),
      body: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          const HighlightBanner(),
          const SizedBox(height: 10),
          Consumer<CategoryMockService>(
            builder: (context, service, child) {
              return CategoryList(categories: service.getCategories());
            },
          ),
          Consumer<VideoMockService>(
            builder: (context, service, child) {
              return VideosList(videos: service.getVideos());
            },
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => Navigator.of(context).push(
          MaterialPageRoute(builder: (context) => const RegisterVideoScreen()),
        ),
        backgroundColor: kFloatingButtonColor,
        child: const Icon(Icons.add_sharp, color: Colors.white, size: 40),
      ),
    );
  }
}
