import 'package:flutter/material.dart';
import 'package:mobflix/components/app_name.dart';
import 'package:mobflix/components/category_list.dart';
import 'package:mobflix/models/category.dart';
import 'package:mobflix/models/video.dart';
import 'package:mobflix/screens/register_video_screen.dart';
import 'package:mobflix/services/category_sqflite_service.dart';
import 'package:mobflix/services/video_sqflite_service.dart';
import 'package:provider/provider.dart';

import '../components/highlight_banner.dart';
import '../components/videos_list.dart';
import '../utils/constants.dart';

class MainScreen extends StatefulWidget {
  const MainScreen({Key? key}) : super(key: key);

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  @override
  void initState() {
    super.initState();
  }

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
          FutureBuilder<List<Category>>(
            future:
                Provider.of<CategorySqfliteService>(context).getCategories(),
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                if (snapshot.data!.isEmpty) {
                  return const Center(
                    child: Text('Nenhuma categoria encontrada'),
                  );
                }
                return CategoryList(categories: snapshot.data!);
              } else if (snapshot.hasError) {
                return Text(snapshot.error.toString());
              }
              return const Center(child: CircularProgressIndicator());
            },
          ),
          FutureBuilder<List<Video>>(
            future: Provider.of<VideoSqfliteService>(context).getVideos(),
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                if (snapshot.data!.isEmpty) {
                  return const Center(child: Text('Nenhum vídeo encontrado'));
                }
                return VideosList(videos: snapshot.data!);
              } else if (snapshot.hasError) {
                return Text(snapshot.error.toString());
              }
              return const Center(child: CircularProgressIndicator());
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
