import 'package:flutter/material.dart';
import 'package:mobflix/components/category_dropdown.dart';
import 'package:mobflix/components/custom_text_field.dart';
import 'package:mobflix/components/filled_button.dart';
import 'package:mobflix/components/video_card.dart';
import 'package:mobflix/models/category.dart';
import 'package:mobflix/models/video.dart';
import 'package:mobflix/utils/assets.dart';

import '../services/video_service.dart';
import '../utils/constants.dart';

class RegisterVideoScreen extends StatefulWidget {
  const RegisterVideoScreen({Key? key}) : super(key: key);

  @override
  State<RegisterVideoScreen> createState() => _RegisterVideoScreenState();
}

class _RegisterVideoScreenState extends State<RegisterVideoScreen> {
  final _videoService = VideosMockService();
  Video video = Video.empty();

  _onSelectCategory(Category? category) =>
      setState(() => video = video.copyWith(category: category));

  _onSubmitUrl(String url) => setState(() {
        video = video.copyWith(
          url: '$kYoutubeBaseUrl$url',
          thumbnail: '$kYoutubeThumbnailBaseUrl$url/0.jpg',
        );
      });

  Future<void> _onRegisterVideo() async {
    if (video.url.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: const Text('Informe a URL do vídeo'),
          backgroundColor: Theme.of(context).errorColor,
        ),
      );
      return;
    }
    if (video.category == null) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: const Text('Informe a categoria do vídeo'),
          backgroundColor: Theme.of(context).errorColor,
        ),
      );
      return;
    }
    await _videoService.addVideo(video);
    Navigator.pop(context);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      body: SafeArea(
        child: SingleChildScrollView(
          child: Padding(
            padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 28),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                const Text(
                  'Cadastre um vídeo',
                  style: TextStyle(
                    fontFamily: 'Roboto',
                    fontSize: 28,
                    color: Colors.white,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                const SizedBox(height: 28),
                const Text(
                  'URL:',
                  style: kTextFieldLabelStyle,
                ),
                const SizedBox(height: 8),
                CustomTextField(
                  hint: 'Ex: N3h5A0oAzsk',
                  onSubmit: _onSubmitUrl,
                ),
                const SizedBox(height: 28),
                const Text(
                  'Categoria:',
                  style: kTextFieldLabelStyle,
                ),
                const SizedBox(height: 8),
                CategoryDropdown(onSelectCategory: _onSelectCategory),
                const SizedBox(height: 28),
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
                if (video.url.isNotEmpty && video.category != null)
                  VideoCard(video: video)
                else
                  Image.asset(Assets.preview),
                const SizedBox(height: 24),
                FilledButton(
                  text: 'Cadastrar',
                  color: kMainBlueColor,
                  onPressed: _onRegisterVideo,
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
