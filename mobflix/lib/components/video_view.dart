import 'package:flutter/material.dart';
import 'package:mobflix/components/video_card.dart';

import '../models/category.dart';
import '../models/video.dart';
import '../utils/assets.dart';
import '../utils/constants.dart';
import 'category_dropdown.dart';
import 'custom_text_field.dart';

class VideoView extends StatefulWidget {
  const VideoView({
    Key? key,
    required this.title,
    required this.buttonWidget,
    required this.video,
    required this.onChanged,
    this.flex,
  }) : super(key: key);

  final String title;
  final Widget buttonWidget;
  final Video video;
  final Function(Video) onChanged;
  final int? flex;

  @override
  State<VideoView> createState() => _VideoViewState();
}

class _VideoViewState extends State<VideoView> {
  late Video _video = widget.video;

  _onSelectCategory(Category? category) => setState(() {
        _video = _video.copyWith(category: category);
        widget.onChanged(_video);
      });

  _onFocusLost(String url) => setState(() {
        _video = _video.copyWith(
          url: '$kYoutubeBaseUrl$url',
          thumbnail: '$kYoutubeThumbnailBaseUrl$url/0.jpg',
        );
        widget.onChanged(_video);
      });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      body: SafeArea(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Expanded(
              flex: 7,
              child: SingleChildScrollView(
                child: Padding(
                  padding: const EdgeInsets.symmetric(
                    horizontal: 16,
                    vertical: 12,
                  ),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.stretch,
                    children: [
                      Text(
                        widget.title,
                        style: const TextStyle(
                          fontFamily: 'Roboto',
                          fontSize: 28,
                          color: Colors.white,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      const SizedBox(height: 20),
                      const Text('URL:', style: kTextFieldLabelStyle),
                      const SizedBox(height: 8),
                      CustomTextField(
                        hint: 'Ex: N3h5A0oAzsk',
                        data: _video.url.split('=').last,
                        onFocusLost: _onFocusLost,
                      ),
                      const SizedBox(height: 20),
                      const Text('Categoria:', style: kTextFieldLabelStyle),
                      const SizedBox(height: 8),
                      CategoryDropdown(
                        onSelectCategory: _onSelectCategory,
                        category: _video.category,
                      ),
                      const SizedBox(height: 20),
                      const Text(
                        'Preview',
                        style: TextStyle(
                          fontFamily: 'Roboto',
                          fontSize: 24,
                          color: Colors.white,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      const SizedBox(height: 8),
                      if (_video.url.isNotEmpty && _video.category != null)
                        VideoCard(video: _video, canLongPress: false)
                      else
                        Image.asset(Assets.preview),
                    ],
                  ),
                ),
              ),
            ),
            Expanded(
              flex: widget.flex ?? 2,
              child: Padding(
                padding: EdgeInsets.symmetric(
                  horizontal: 16,
                  vertical: widget.flex != null ? 12 : 0,
                ),
                child: widget.buttonWidget,
              ),
            ),
          ],
        ),
      ),
    );
  }
}
