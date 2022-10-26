import '../models/video.dart';

abstract class IVideoService {
  List<Video> getVideos();

  Future<void> addVideo(Video video);
}
