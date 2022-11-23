import 'package:flutter/material.dart';
import 'package:mobflix/components/video_view.dart';
import 'package:mobflix/models/video.dart';
import 'package:provider/provider.dart';

import '../components/filled_button.dart';
import '../controllers/video_controller.dart';
import '../utils/constants.dart';
import '../utils/snackbar_utils.dart';

class RegisterVideoScreen extends StatefulWidget {
  const RegisterVideoScreen({Key? key}) : super(key: key);

  @override
  State<RegisterVideoScreen> createState() => _RegisterVideoScreenState();
}

class _RegisterVideoScreenState extends State<RegisterVideoScreen> {
  bool _isLoading = false;
  Video _video = const Video.empty();

  Future<void> _onRegisterVideo() async {
    setState(() => _isLoading = true);
    try {
      await context.read<VideoController>().registerVideo(_video);
      if (!mounted) return;

      SnackBarUtils.showSuccess(context, 'Vídeo cadastrado com sucesso!');
      Navigator.of(context).pop();
    } on Exception catch (e) {
      SnackBarUtils.showError(context, e.toString());
    } finally {
      setState(() => _isLoading = false);
    }
  }

  void _onChangedVideo(Video video) => _video = video;

  @override
  Widget build(BuildContext context) {
    return VideoView(
      title: 'Cadastre um vídeo',
      buttonWidget: _isLoading
          ? const Center(
              child: CircularProgressIndicator(color: kMainBlueColor),
            )
          : FilledButton(
              text: 'Cadastrar',
              color: kMainBlueColor,
              onPressed: _onRegisterVideo,
            ),
      video: _video,
      onChanged: _onChangedVideo,
      flex: 1,
    );
  }
}
