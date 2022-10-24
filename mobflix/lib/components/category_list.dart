import 'package:flutter/material.dart';
import 'package:mobflix/components/category_chip.dart';
import 'package:mobflix/models/category.dart';

import '../utils/constants.dart';

class CategoryList extends StatelessWidget {
  const CategoryList({Key? key, required this.categories}) : super(key: key);

  final List<Category> categories;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 60,
      child: ListView.separated(
        padding: const EdgeInsets.symmetric(
          vertical: 14,
          horizontal: kMainScreenHorizontalPadding,
        ),
        scrollDirection: Axis.horizontal,
        itemCount: categories.length,
        itemBuilder: (context, i) => CategoryChip(category: categories[i]),
        separatorBuilder: (context, index) => const SizedBox(width: 16),
      ),
    );
  }
}
