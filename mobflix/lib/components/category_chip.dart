import 'package:flutter/material.dart';
import 'package:mobflix/models/category.dart';

class CategoryChip extends StatelessWidget {
  const CategoryChip({
    Key? key,
    required this.category,
    this.selected = false,
    this.selectable = false,
    this.onSelected,
  }) : super(key: key);

  final Category category;
  final bool selected;
  final bool selectable;
  final Function(bool)? onSelected;

  @override
  Widget build(BuildContext context) {
    final isSelected = selectable && selected;
    return Material(
      color: isSelected ? category.color.withOpacity(0.25) : category.color,
      borderRadius: BorderRadius.circular(12),
      elevation: 4,
      shadowColor: Colors.black,
      child: InkWell(
        borderRadius: BorderRadius.circular(12),
        splashColor: category.color.withOpacity(0.25),
        highlightColor: category.color.withOpacity(0.25),
        onTap: () {
          if (selectable) onSelected!(!selected);
        },
        child: Container(
          padding: const EdgeInsets.fromLTRB(12, 4, 12, 4),
          child: Text(
            category.name,
            style: const TextStyle(
              fontFamily: 'Roboto',
              color: Colors.white,
              fontSize: 16,
            ),
          ),
        ),
      ),
    );
  }
}
