import 'package:flutter/material.dart';

import '../mocks/category_mock.dart';
import '../models/category.dart';
import '../utils/constants.dart';

class CategoryDropdown extends StatefulWidget {
  const CategoryDropdown({
    Key? key,
    required this.onSelectCategory,
  }) : super(key: key);

  final Function(Category?) onSelectCategory;

  @override
  State<CategoryDropdown> createState() => _CategoryDropdownState();
}

class _CategoryDropdownState extends State<CategoryDropdown> {
  Category? _selectedCategory;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 10),
      decoration: BoxDecoration(
        color: kTextFieldColor,
        borderRadius: BorderRadius.circular(8),
      ),
      child: DropdownButton<Category>(
        value: _selectedCategory,
        items: categoryMocks
            .map(
              (e) => DropdownMenuItem<Category>(
                value: e,
                child: Text(e.name),
              ),
            )
            .toList(),
        onChanged: (value) {
          setState(() => _selectedCategory = value);
          widget.onSelectCategory(value);
        },
        borderRadius: BorderRadius.circular(8),
        isExpanded: true,
        dropdownColor: kTextFieldColor,
        underline: Container(),
        hint: const Text(
          'Mobile, Front-end...',
          style: TextStyle(fontFamily: 'Roboto', color: kHintTextColor),
        ),
        icon: Container(),
        isDense: true,
      ),
    );
  }
}
