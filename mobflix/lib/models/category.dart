import 'dart:ui';

import 'package:equatable/equatable.dart';

class Category extends Equatable {
  final int id;
  final String name;
  final String colorString;

  const Category({
    required this.id,
    required this.name,
    required this.colorString,
  });

  Color get color => Color(int.parse(colorString));

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'name': name,
      'color': colorString,
    };
  }

  factory Category.fromMap(Map<String, dynamic> map) {
    return Category(
      id: map['id'],
      name: map['name'],
      colorString: map['color'],
    );
  }

  @override
  List<Object?> get props => [id];
}
