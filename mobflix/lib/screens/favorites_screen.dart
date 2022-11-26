import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../components/videos_list.dart';
import '../models/video.dart';
import '../services/video_sqflite_service.dart';

class FavoritesScreen extends StatelessWidget {
  const FavoritesScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Vídeos favoritos'),
        elevation: 0,
        backgroundColor: Colors.transparent,
        centerTitle: true,
      ),
      body: FutureBuilder<List<Video>>(
        future: Provider.of<VideoSqfliteService>(context).getFavoriteVideos(),
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            if (snapshot.data!.isEmpty) {
              return const Center(child: Text('Nenhum favorito encontrado'));
            }
            return VideosList(videos: snapshot.data!);
          } else if (snapshot.hasError) {
            return Text(snapshot.error.toString());
          }
          return const Center(child: CircularProgressIndicator());
        },
      ),
    );
  }
}
