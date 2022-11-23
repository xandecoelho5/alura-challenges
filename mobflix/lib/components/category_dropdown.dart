import 'package:flutter/material.dart';
import 'package:mobflix/services/category_sqflite_service.dart';
import 'package:provider/provider.dart';

import '../models/category.dart';
import '../utils/constants.dart';

class CategoryDropdown extends StatefulWidget {
  const CategoryDropdown({
    Key? key,
    this.category,
    required this.onSelectCategory,
  }) : super(key: key);

  final Category? category;
  final Function(Category?) onSelectCategory;

  @override
  State<CategoryDropdown> createState() => _CategoryDropdownState();
}

class _CategoryDropdownState extends State<CategoryDropdown> {
  late Category? _selectedCategory = widget.category;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 10),
      decoration: BoxDecoration(
        color: kTextFieldColor,
        borderRadius: BorderRadius.circular(8),
      ),
      child: FutureBuilder<List<Category>>(
        future: Provider.of<CategorySqfliteService>(context).getCategories(),
        builder: (ctx, snapshot) {
          if (snapshot.hasData) {
            return DropdownButton<Category>(
              value: _selectedCategory,
              items: snapshot.data!
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
            );
          }
          if (snapshot.hasError) {
            return Text('Erro ao carregar categorias ${snapshot.error}');
          }
          return const Center(child: CircularProgressIndicator());
        },
      ),
    );
  }
}
