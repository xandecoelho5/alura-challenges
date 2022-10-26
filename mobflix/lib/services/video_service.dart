import '../mocks/video_mocks.dart';
import '../models/video.dart';

abstract class IVideoService {
  Future<List<Video>> getVideos();

  Future<void> addVideo(Video video);
}

class VideosMockService implements IVideoService {
  @override
  Future<List<Video>> getVideos() async {
    await Future.delayed(const Duration(milliseconds: 500));
    return videoMocks;
  }

  @override
  Future<void> addVideo(Video video) async {
    await Future.delayed(const Duration(milliseconds: 500));
    videoMocks.add(video);
    print(videoMocks.length);
  }
}
