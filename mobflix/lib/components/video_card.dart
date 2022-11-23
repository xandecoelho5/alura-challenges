import 'package:flutter/material.dart';
import 'package:mobflix/components/category_chip.dart';
import 'package:mobflix/screens/edit_video_screen.dart';
import 'package:url_launcher/url_launcher.dart';

import '../models/video.dart';
import '../utils/assets.dart';

class VideoCard extends StatelessWidget {
  const VideoCard({Key? key, required this.video}) : super(key: key);

  final Video video;

  Future<void> _launchUrl() async {
    final Uri url = Uri.parse(video.url);
    if (!await launchUrl(url)) {
      throw 'Erro ao tentar abrir $url';
    }
  }

  void _onLongPress(context) {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => EditVideoScreen(video: video)),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        CategoryChip(category: video.category!),
        const SizedBox(height: 8),
        GestureDetector(
          onTap: _launchUrl,
          onLongPress: () => _onLongPress(context),
          child: Container(
            height: 165,
            width: double.infinity,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(8),
              boxShadow: kElevationToShadow[4],
            ),
            child: FadeInImage(
              fadeInDuration: const Duration(milliseconds: 200),
              image: NetworkImage(video.thumbnail),
              placeholder: const AssetImage(Assets.preview),
              imageErrorBuilder: (c, e, s) {
                // WidgetsBinding.instance.addPostFrameCallback((timeStamp) {
                //   ScaffoldMessenger.of(c).hideCurrentSnackBar();
                //   ScaffoldMessenger.of(c).showSnackBar(
                //     SnackBar(
                //       content: const Text(
                //         'Não encontrado thumbnail para esta URL',
                //       ),
                //       backgroundColor: Theme.of(c).errorColor,
                //       duration: const Duration(seconds: 2),
                //     ),
                //   );
                // });
                return Image.asset(Assets.preview);
              },
              fit: BoxFit.cover,
            ),
          ),
        ),
      ],
    );
  }
}
