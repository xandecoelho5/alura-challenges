import 'package:mobflix/mocks/category_mock.dart';
import 'package:mobflix/models/video.dart';

import '../utils/assets.dart';

final videoMocks = [
  Video(
    id: 1,
    url: 'https://www.youtube.com/watch?v=pcnfjJG3jY4',
    category: categoryMocks[2],
    thumbnail: Assets.mobile1,
  ),
  Video(
    id: 2,
    url: 'https://www.youtube.com/watch?v=fmu1LQvZhms',
    category: categoryMocks[2],
    thumbnail: Assets.mobile2,
  ),
  Video(
    id: 3,
    url: 'https://www.youtube.com/watch?v=4AH9H_dx_7g',
    category: categoryMocks[2],
    thumbnail: Assets.mobile3,
  ),
];
