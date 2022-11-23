import 'package:flutter/material.dart';

class SnackBarUtils {
  SnackBarUtils._();

  static void showError(BuildContext context, String message) =>
      _show(context, message, Theme.of(context).errorColor);

  static void showSuccess(BuildContext context, String message) =>
      _show(context, message, Colors.green);

  static void _show(BuildContext context, String message, Color color) =>
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text(message),
          backgroundColor: color,
          duration: const Duration(seconds: 2),
        ),
      );
}
