import '../models/category.dart';

abstract class ICategoryService {
  Future<List<Category>> getCategories();
}
