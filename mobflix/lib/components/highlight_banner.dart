import 'package:flutter/material.dart';
import 'package:mobflix/components/filled_button.dart';
import 'package:url_launcher/url_launcher.dart';

import '../utils/assets.dart';
import '../utils/constants.dart';

class HighlightBanner extends StatelessWidget {
  const HighlightBanner({Key? key}) : super(key: key);

  Future<void> _launchUrl() async {
    final Uri url = Uri.parse('https://www.youtube.com/watch?v=pcnfjJG3jY4');
    if (!await launchUrl(url)) {
      throw 'Could not launch $url';
    }
  }

  @override
  Widget build(BuildContext context) {
    return Stack(
      alignment: Alignment.center,
      children: [
        Image.asset(
          Assets.highlight,
          fit: BoxFit.fill,
          width: double.infinity,
          // height: MediaQuery.of(context).size.height * 0.16,
          height: 124,
        ),
        Positioned(
          bottom: 15,
          child: FilledButton(
            onPressed: _launchUrl,
            text: 'Assistir agora',
            color: kMainBlueColor,
          ),
        ),
      ],
    );
  }
}
