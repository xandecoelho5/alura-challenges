import 'package:mobflix/exceptions/empty_url_exception.dart';
import 'package:mobflix/exceptions/invalid_url_exception.dart';
import 'package:mobflix/exceptions/none_category_selected_exception.dart';
import 'package:mobflix/models/video.dart';
import 'package:mobflix/services/video_service.dart';

import '../services/http_service.dart';

class VideoController {
  final IVideoService _videoService;
  final IHttpService _httpService;

  VideoController(this._videoService, this._httpService);

  Future<void> registerVideo(Video video) async {
    await _validateVideo(video);
    await _videoService.addVideo(video);
  }

  Future<void> _validateVideo(Video video) async {
    if (video.url.isEmpty) {
      throw const EmptyUrlException();
    }
    if (video.category == null) {
      throw const NoneCategorySelectedException();
    }
    try {
      await _httpService.get(video.thumbnail);
    } catch (e) {
      throw const InvalidUrlException();
    }
  }
}
