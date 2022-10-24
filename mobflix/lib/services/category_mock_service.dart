import 'package:mobflix/models/category.dart';
import 'package:mobflix/services/category_service.dart';

import '../mocks/category_mock.dart';

class CategoryMockService implements ICategoryService {
  @override
  Future<List<Category>> getCategories() {
    return Future.delayed(
      const Duration(seconds: 1),
      () => categoryMocks,
    );
  }
}
