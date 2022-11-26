import 'package:flutter/material.dart';
import 'package:mobflix/components/category_chip.dart';
import 'package:mobflix/models/category.dart';

import '../utils/constants.dart';

class CategoryList extends StatefulWidget {
  const CategoryList({
    Key? key,
    required this.categories,
    required this.onSelected,
  }) : super(key: key);

  final List<Category> categories;
  final Function(Category?) onSelected;

  @override
  State<CategoryList> createState() => _CategoryListState();
}

class _CategoryListState extends State<CategoryList> {
  int? selectedIndex;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 50,
      child: ListView.separated(
        padding: const EdgeInsets.symmetric(
          vertical: 12,
          horizontal: kMainScreenHorizontalPadding,
        ),
        scrollDirection: Axis.horizontal,
        itemCount: widget.categories.length,
        itemBuilder: (context, i) => CategoryChip(
          category: widget.categories[i],
          selected: i == selectedIndex,
          selectable: true,
          onSelected: (v) {
            setState(() => selectedIndex = v ? i : null);
            widget.onSelected(
              selectedIndex == null ? null : widget.categories[i],
            );
          },
        ),
        separatorBuilder: (context, index) => const SizedBox(width: 16),
      ),
    );
  }
}
