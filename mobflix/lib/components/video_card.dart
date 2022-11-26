import 'package:flutter/material.dart';
import 'package:mobflix/components/category_chip.dart';
import 'package:mobflix/screens/edit_video_screen.dart';
import 'package:provider/provider.dart';
import 'package:url_launcher/url_launcher.dart';

import '../controllers/video_controller.dart';
import '../models/video.dart';
import '../utils/assets.dart';
import '../utils/snackbar_utils.dart';

class VideoCard extends StatefulWidget {
  const VideoCard({
    Key? key,
    required this.video,
    this.canLongPress = false,
    this.showFavorite = false,
  }) : super(key: key);

  final Video video;
  final bool canLongPress;
  final bool showFavorite;

  @override
  State<VideoCard> createState() => _VideoCardState();
}

class _VideoCardState extends State<VideoCard> {
  Future<void> _launchUrl() async {
    final Uri url = Uri.parse(widget.video.url);
    if (!await launchUrl(url)) {
      throw 'Erro ao tentar abrir $url';
    }
  }

  void _onLongPress() {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => EditVideoScreen(video: widget.video),
      ),
    );
  }

  void _toggleFavorite() {
    try {
      context.read<VideoController>().editVideo(
            widget.video.copyWith(isFavorite: !widget.video.isFavorite),
          );
      SnackBarUtils.showSuccess(
        context,
        widget.video.isFavorite
            ? 'Removido dos favoritos'
            : 'Adicionado aos favoritos',
      );
    } catch (e) {
      SnackBarUtils.showError(context, e.toString());
    }
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            CategoryChip(category: widget.video.category!),
            if (widget.showFavorite)
              Icon(
                widget.video.isFavorite
                    ? Icons.favorite
                    : Icons.favorite_border,
                color: widget.video.isFavorite ? Colors.red : Colors.grey,
              )
          ],
        ),
        const SizedBox(height: 8),
        GestureDetector(
          onTap: _launchUrl,
          onLongPress: widget.canLongPress ? _onLongPress : null,
          onDoubleTap: widget.canLongPress ? _toggleFavorite : null,
          child: Container(
            height: 165,
            width: double.infinity,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(8),
              boxShadow: kElevationToShadow[4],
            ),
            child: FadeInImage(
              fadeInDuration: const Duration(milliseconds: 200),
              image: NetworkImage(widget.video.thumbnail),
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
