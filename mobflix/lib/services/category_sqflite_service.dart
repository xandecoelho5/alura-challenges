import 'package:flutter/cupertino.dart';
import 'package:mobflix/models/category.dart';

import 'category_service.dart';
import 'database_service.dart';

class CategorySqfliteService extends ChangeNotifier
    implements ICategoryService {
  final IDatabaseService databaseService;
  final String _table = 'categories';
  final List<Category> _categories = [];

  CategorySqfliteService(this.databaseService);

  @override
  Future<List<Category>> getCategories() async {
    final databaseCategories = await databaseService.query(_table);
    final categories = databaseCategories.map(Category.fromMap).toList();
    _categories.clear();
    _categories.addAll(categories);
    return categories;
  }
}
