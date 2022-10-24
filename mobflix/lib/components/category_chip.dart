import 'package:flutter/material.dart';
import 'package:mobflix/models/category.dart';

class CategoryChip extends StatelessWidget {
  const CategoryChip({Key? key, required this.category}) : super(key: key);

  final Category category;

  @override
  Widget build(BuildContext context) {
    return Material(
      color: category.color,
      borderRadius: BorderRadius.circular(12),
      elevation: 4,
      shadowColor: Colors.black,
      child: Container(
        padding: const EdgeInsets.fromLTRB(20, 6, 18, 6),
        child: Text(
          category.name,
          style: const TextStyle(
            fontFamily: 'Roboto',
            color: Colors.white,
            fontSize: 16,
          ),
        ),
      ),
    );
  }
}
