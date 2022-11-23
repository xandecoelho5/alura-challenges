import 'package:flutter/material.dart';
import 'package:mobflix/models/video.dart';
import 'package:provider/provider.dart';

import '../components/filled_button.dart';
import '../components/video_view.dart';
import '../controllers/video_controller.dart';
import '../utils/constants.dart';
import '../utils/snackbar_utils.dart';

class EditVideoScreen extends StatefulWidget {
  const EditVideoScreen({Key? key, required this.video}) : super(key: key);

  final Video video;

  @override
  State<EditVideoScreen> createState() => _EditVideoScreenState();
}

class _EditVideoScreenState extends State<EditVideoScreen> {
  bool _isLoading = false;
  late Video _video = widget.video;

  Future<void> _doAction(
    Future<void> Function() action,
    String successMessage,
  ) async {
    setState(() => _isLoading = true);
    try {
      await action();
      if (!mounted) return;

      SnackBarUtils.showSuccess(context, successMessage);
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
      title: 'Edite o vídeo',
      video: _video,
      buttonWidget: _isLoading
          ? const Center(
              child: CircularProgressIndicator(color: kMainBlueColor),
            )
          : Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                FilledButton(
                  text: 'Alterar',
                  color: kMainBlueColor,
                  onPressed: () {
                    _doAction(
                      () => context.read<VideoController>().editVideo(_video),
                      'Vídeo editado com sucesso!',
                    );
                  },
                ),
                const SizedBox(height: 4),
                FilledButton(
                  text: 'Remover',
                  color: kMainRedColor,
                  onPressed: () {
                    _doAction(
                      () => context.read<VideoController>().removeVideo(_video),
                      'Vídeo removido com sucesso!',
                    );
                  },
                ),
              ],
            ),
      onChanged: _onChangedVideo,
    );
  }
}
