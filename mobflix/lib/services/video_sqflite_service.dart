import 'package:flutter/cupertino.dart';
import 'package:mobflix/models/video.dart';
import 'package:mobflix/services/database_service.dart';
import 'package:mobflix/services/video_service.dart';

class VideoSqfliteService extends ChangeNotifier implements IVideoService {
  final IDatabaseService databaseService;
  final String _table = 'videos';
  final List<Video> _videos = [];

  VideoSqfliteService(this.databaseService);

  @override
  Future<void> addVideo(Video video) async {
    await databaseService.insert(_table, video.toMap());
    await _notifyVideos();
  }

  @override
  Future<void> deleteVideo(Video video) async {
    await databaseService.delete(_table, video.id);
    await _notifyVideos();
  }

  @override
  Future<void> updateVideo(Video video) async {
    await databaseService.update(_table, video.toMap());
    await _notifyVideos();
  }

  @override
  Future<List<Video>> getVideos() async {
    final videos = await databaseService.rawQuery(
      'SELECT v.id, v.url, v.thumbnail, v.is_favorite, v.category_id, c.name, c.color '
      'FROM $_table v INNER JOIN categories c ON v.category_id = c.id',
    );
    return videos.map(Video.fromMap).toList();
  }

  Future<List<Video>> getFavoriteVideos() async {
    final videos = await databaseService.rawQuery(
      'SELECT v.id, v.url, v.thumbnail, v.is_favorite, v.category_id, c.name, c.color '
      'FROM $_table v INNER JOIN categories c ON v.category_id = c.id '
      'WHERE v.is_favorite = 1',
    );
    return videos.map(Video.fromMap).toList();
  }

  Future<void> _notifyVideos() async {
    _videos.clear();
    _videos.addAll(await getVideos());
    notifyListeners();
  }
}
