import 'package:flutter/material.dart';

import '../utils/constants.dart';

class CustomTextField extends StatelessWidget {
  const CustomTextField({
    Key? key,
    required this.hint,
    this.onChanged,
    this.onSubmit,
  }) : super(key: key);

  final String hint;
  final Function(String)? onChanged;
  final Function(String)? onSubmit;

  @override
  Widget build(BuildContext context) {
    return TextField(
      decoration: InputDecoration(
        isDense: true,
        contentPadding: const EdgeInsets.symmetric(
          horizontal: 16,
          vertical: 12,
        ),
        hintText: hint,
        hintStyle: const TextStyle(fontFamily: 'Roboto', color: kHintTextColor),
        enabledBorder: kTextFieldBorder,
        focusedBorder: kTextFieldBorder,
        filled: true,
        fillColor: kTextFieldColor,
      ),
      onChanged: onChanged,
      onSubmitted: onSubmit,
    );
  }
}
