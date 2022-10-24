import 'package:flutter/material.dart';
import 'package:mobflix/components/video_card.dart';
import 'package:mobflix/models/video.dart';

import '../utils/constants.dart';

class VideosList extends StatelessWidget {
  const VideosList({Key? key, required this.videos}) : super(key: key);

  final List<Video> videos;

  @override
  Widget build(BuildContext context) {
    return Flexible(
      child: ListView.separated(
        padding: const EdgeInsets.symmetric(
          vertical: 16,
          horizontal: kMainScreenHorizontalPadding,
        ),
        itemCount: videos.length,
        itemBuilder: (ctx, index) => VideoCard(video: videos[index]),
        separatorBuilder: (_, __) => const SizedBox(height: 16),
      ),
    );
  }
}
