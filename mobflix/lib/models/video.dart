import 'package:equatable/equatable.dart';
import 'package:mobflix/models/category.dart';

class Video extends Equatable {
  final int? id;
  final String url;
  final Category? category;
  final String thumbnail;

  const Video({
    this.id,
    required this.url,
    this.category,
    required this.thumbnail,
  });

  const Video.empty([this.id, this.category])
      : url = '',
        thumbnail = '';

  Video copyWith({
    String? url,
    Category? category,
    String? thumbnail,
  }) {
    return Video(
      id: id,
      url: url ?? this.url,
      category: category ?? this.category,
      thumbnail: thumbnail ?? this.thumbnail,
    );
  }

  @override
  String toString() {
    return 'Video{id: $id, category: ${category?.name}}';
  }

  @override
  List<Object?> get props => [id, category];

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'url': url,
      'category_id': category?.id,
      'thumbnail': thumbnail,
    };
  }

  factory Video.fromMap(Map<String, dynamic> map) {
    return Video(
      id: map['id'],
      url: map['url'],
      category: _getCategoryFromMap(map),
      thumbnail: map['thumbnail'],
    );
  }

  static _getCategoryFromMap(Map<String, dynamic> map) {
    return Category(
      id: map['category_id'],
      name: map['name'],
      colorString: map['color'],
    );
  }
}
