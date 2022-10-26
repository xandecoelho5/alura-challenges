import 'package:equatable/equatable.dart';
import 'package:mobflix/models/category.dart';

class Video extends Equatable {
  static int _id = 0;

  final int id;
  final String url;
  final Category? category;
  final String thumbnail;

  const Video({
    required this.id,
    required this.url,
    this.category,
    required this.thumbnail,
  });

  Video.empty([this.category])
      : id = _id++,
        url = '',
        thumbnail = '';

  Video copyWith({
    int? id,
    String? url,
    Category? category,
    String? thumbnail,
  }) {
    return Video(
      id: id ?? this.id,
      url: url ?? this.url,
      category: category ?? this.category,
      thumbnail: thumbnail ?? this.thumbnail,
    );
  }

  @override
  List<Object?> get props => [url, category];
}
