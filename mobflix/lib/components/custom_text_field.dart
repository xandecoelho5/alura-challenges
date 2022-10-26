import 'package:flutter/material.dart';

import '../utils/constants.dart';

class CustomTextField extends StatefulWidget {
  const CustomTextField({
    Key? key,
    required this.hint,
    this.onFocusLost,
    this.onChanged,
  }) : super(key: key);

  final String hint;
  final Function(String)? onFocusLost;
  final Function(String)? onChanged;

  @override
  State<CustomTextField> createState() => _CustomTextFieldState();
}

class _CustomTextFieldState extends State<CustomTextField> {
  late FocusNode _node;
  final _controller = TextEditingController();

  @override
  void initState() {
    super.initState();
    _node = FocusNode();
    _node.addListener(_handleFocusChange);
  }

  void _handleFocusChange() {
    if (!_node.hasFocus) {
      widget.onFocusLost?.call(_controller.text);
    }
  }

  @override
  void dispose() {
    _node.removeListener(_handleFocusChange);
    _node.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return TextField(
      controller: _controller,
      focusNode: _node,
      decoration: InputDecoration(
        isDense: true,
        contentPadding: const EdgeInsets.symmetric(
          horizontal: 16,
          vertical: 12,
        ),
        hintText: widget.hint,
        hintStyle: const TextStyle(fontFamily: 'Roboto', color: kHintTextColor),
        enabledBorder: kTextFieldBorder,
        focusedBorder: kTextFieldBorder,
        filled: true,
        fillColor: kTextFieldColor,
      ),
      onChanged: widget.onChanged,
    );
  }
}
