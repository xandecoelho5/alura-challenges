import 'package:flutter/material.dart';
import 'package:mobflix/components/category_dropdown.dart';
import 'package:mobflix/components/custom_text_field.dart';
import 'package:mobflix/components/filled_button.dart';
import 'package:mobflix/components/video_card.dart';
import 'package:mobflix/controllers/video_controller.dart';
import 'package:mobflix/models/category.dart';
import 'package:mobflix/models/video.dart';
import 'package:mobflix/utils/assets.dart';
import 'package:provider/provider.dart';

import '../utils/constants.dart';

class RegisterVideoScreen extends StatefulWidget {
  const RegisterVideoScreen({Key? key}) : super(key: key);

  @override
  State<RegisterVideoScreen> createState() => _RegisterVideoScreenState();
}

class _RegisterVideoScreenState extends State<RegisterVideoScreen> {
  bool _isRegistering = false;
  Video video = Video.empty();

  _onSelectCategory(Category? category) =>
      setState(() => video = video.copyWith(category: category));

  _onFocusLost(String url) => setState(() {
        video = video.copyWith(
          url: '$kYoutubeBaseUrl$url',
          thumbnail: '$kYoutubeThumbnailBaseUrl$url/0.jpg',
        );
      });

  _showError(String message) => ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text(message),
          backgroundColor: Theme.of(context).errorColor,
          duration: const Duration(seconds: 2),
        ),
      );

  _showSuccess(String message) => ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text(message),
          backgroundColor: Colors.green,
          duration: const Duration(seconds: 2),
        ),
      );

  Future<void> _onRegisterVideo() async {
    setState(() => _isRegistering = true);
    try {
      await context.read<VideoController>().registerVideo(video);
      _showSuccess('Vídeo cadastrado com sucesso!');

      if (!mounted) return;
      Navigator.of(context).pop();
    } on Exception catch (e) {
      _showError(e.toString());
    } finally {
      setState(() => _isRegistering = false);
    }
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
                const Text('URL:', style: kTextFieldLabelStyle),
                const SizedBox(height: 8),
                CustomTextField(
                  hint: 'Ex: N3h5A0oAzsk',
                  onFocusLost: _onFocusLost,
                ),
                const SizedBox(height: 28),
                const Text('Categoria:', style: kTextFieldLabelStyle),
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
                if (!_isRegistering)
                  FilledButton(
                    text: 'Cadastrar',
                    color: kMainBlueColor,
                    onPressed: _onRegisterVideo,
                  )
                else
                  const Center(
                    child: CircularProgressIndicator(color: kMainBlueColor),
                  )
              ],
            ),
          ),
        ),
      ),
    );
  }
}
