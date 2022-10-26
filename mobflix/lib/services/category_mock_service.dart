import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:mobflix/models/category.dart';
import 'package:mobflix/services/category_service.dart';

import '../mocks/category_mock.dart';

class CategoryMockService extends ChangeNotifier implements ICategoryService {
  // só pra inicializar com os valores mockados
  CategoryMockService() {
    _categories.addAll(categoryMocks);
  }

  final List<Category> _categories = [];

  @override
  UnmodifiableListView<Category> getCategories() =>
      UnmodifiableListView(_categories);
}
