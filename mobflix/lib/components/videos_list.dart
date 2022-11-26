import 'package:flutter/material.dart';
import 'package:mobflix/components/video_card.dart';
import 'package:mobflix/models/category.dart';
import 'package:mobflix/models/video.dart';

import '../utils/constants.dart';

class VideosList extends StatelessWidget {
  const VideosList({
    Key? key,
    required this.videos,
    this.category,
  }) : super(key: key);

  final List<Video> videos;
  final Category? category;

  @override
  Widget build(BuildContext context) {
    final filteredVideos = category == null
        ? videos
        : videos.where((v) => v.category == category).toList();
    return ListView.separated(
      padding: const EdgeInsets.symmetric(
        vertical: 16,
        horizontal: kMainScreenHorizontalPadding,
      ),
      itemCount: filteredVideos.length,
      itemBuilder: (ctx, index) => VideoCard(
        video: filteredVideos[index],
        canLongPress: true,
        showFavorite: true,
      ),
      separatorBuilder: (_, __) => const SizedBox(height: 16),
    );
  }
}
