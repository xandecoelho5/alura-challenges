import 'package:flutter/material.dart';
import 'package:mobflix/components/category_chip.dart';
import 'package:url_launcher/url_launcher.dart';

import '../models/video.dart';

class VideoCard extends StatelessWidget {
  const VideoCard({Key? key, required this.video}) : super(key: key);

  final Video video;

  Future<void> _launchUrl() async {
    final Uri url = Uri.parse(video.url);
    if (!await launchUrl(url)) {
      throw 'Erro ao tentar abrir $url';
    }
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        CategoryChip(category: video.category),
        const SizedBox(height: 8),
        GestureDetector(
          onTap: _launchUrl,
          child: Container(
            height: 180,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(8),
              image: DecorationImage(
                image: AssetImage(video.thumbnail),
                fit: BoxFit.fill,
              ),
              boxShadow: kElevationToShadow[4],
            ),
          ),
        ),
      ],
    );
  }
}
