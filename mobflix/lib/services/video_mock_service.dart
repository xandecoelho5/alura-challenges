import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:mobflix/services/video_service.dart';

import '../exceptions/video_already_registered_exception.dart';
import '../mocks/video_mocks.dart';
import '../models/video.dart';

class VideoMockService extends ChangeNotifier implements IVideoService {
  VideoMockService() {
    _videos.addAll(videoMocks);
  }

  final List<Video> _videos = [];

  @override
  UnmodifiableListView<Video> getVideos() => UnmodifiableListView(_videos);

  @override
  Future<void> addVideo(Video video) async {
    _verifyIfVideoExists(video);
    await Future.delayed(const Duration(milliseconds: 350));
    _videos.add(video);
    notifyListeners();
  }

  @override
  Future<void> updateVideo(Video video) async {
    await Future.delayed(const Duration(milliseconds: 350));
    final index = _videos.indexWhere((element) => element.id == video.id);
    _videos[index] = video;
    notifyListeners();
  }

  @override
  Future<void> deleteVideo(Video video) async {
    await Future.delayed(const Duration(milliseconds: 350));
    _videos.remove(video);
    notifyListeners();
  }

  _verifyIfVideoExists(Video video) {
    if (_videos.contains(video)) {
      throw const VideoAlreadyRegisteredException();
    }
  }
}
