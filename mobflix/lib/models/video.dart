import 'package:equatable/equatable.dart';
import 'package:mobflix/models/category.dart';

class Video extends Equatable {
  final int? id;
  final String url;
  final Category? category;
  final String thumbnail;
  final bool isFavorite;

  const Video({
    this.id,
    required this.url,
    this.category,
    required this.thumbnail,
    this.isFavorite = false,
  });

  const Video.empty([this.id, this.category])
      : url = '',
        thumbnail = '',
        isFavorite = false;

  Video copyWith({
    String? url,
    Category? category,
    String? thumbnail,
    bool? isFavorite,
  }) {
    return Video(
      id: id,
      url: url ?? this.url,
      category: category ?? this.category,
      thumbnail: thumbnail ?? this.thumbnail,
      isFavorite: isFavorite ?? this.isFavorite,
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
      'is_favorite': isFavorite ? 1 : 0,
    };
  }

  factory Video.fromMap(Map<String, dynamic> map) {
    return Video(
      id: map['id'],
      url: map['url'],
      category: _getCategoryFromMap(map),
      thumbnail: map['thumbnail'],
      isFavorite: map['is_favorite'] == 1,
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
