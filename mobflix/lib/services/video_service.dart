import '../models/video.dart';

abstract class IVideoService {
  Future<List<Video>> getVideos();

  Future<void> addVideo(Video video);

  Future<void> updateVideo(Video video);

  Future<void> deleteVideo(Video video);
}
