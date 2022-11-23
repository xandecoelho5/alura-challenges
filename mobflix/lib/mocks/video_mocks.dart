import 'package:mobflix/mocks/category_mock.dart';
import 'package:mobflix/models/video.dart';
import 'package:mobflix/utils/constants.dart';

final videoMocks = [
  Video(
    id: 1,
    url: '${kYoutubeBaseUrl}pcnfjJG3jY4',
    category: categoryMocks[2],
    thumbnail: '${kYoutubeThumbnailBaseUrl}pcnfjJG3jY4/0.jpg',
  ),
  Video(
    id: 2,
    url: '${kYoutubeBaseUrl}fmu1LQvZhms',
    category: categoryMocks[2],
    thumbnail: '${kYoutubeThumbnailBaseUrl}fmu1LQvZhms/0.jpg',
  ),
  Video(
    id: 3,
    url: '${kYoutubeBaseUrl}4AH9H_dx_7g',
    category: categoryMocks[2],
    thumbnail: '${kYoutubeThumbnailBaseUrl}4AH9H_dx_7g/0.jpg',
  ),
];
