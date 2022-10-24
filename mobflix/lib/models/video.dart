import 'package:mobflix/models/category.dart';

class Video {
  final int id;
  final String url;
  final Category category;
  final String thumbnail;

  const Video({
    required this.id,
    required this.url,
    required this.category,
    required this.thumbnail,
  });
}
