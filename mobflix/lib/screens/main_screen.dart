import 'package:flutter/material.dart';
import 'package:mobflix/components/app_name.dart';
import 'package:mobflix/components/category_list.dart';
import 'package:mobflix/components/videos_list.dart';
import 'package:mobflix/mocks/category_mock.dart';
import 'package:mobflix/mocks/video_mocks.dart';

import '../components/highlight_banner.dart';
import '../utils/constants.dart';

class MainScreen extends StatelessWidget {
  const MainScreen({Key? key}) : super(key: key);

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
          const CategoryList(categories: categoryMocks),
          VideosList(videos: videoMocks),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {},
        backgroundColor: kFloatingButtonColor,
        child: const Icon(Icons.add_sharp, color: Colors.white, size: 40),
      ),
    );
  }
}
