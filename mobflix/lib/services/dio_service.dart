import 'package:dio/dio.dart';
import 'package:mobflix/services/http_service.dart';

class DioService implements IHttpService {
  final Dio dio;

  DioService(this.dio);

  @override
  Future<dynamic> get(String url) async {
    return await dio.get(url);
  }
}
