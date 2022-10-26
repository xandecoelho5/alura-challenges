class VideoAlreadyRegisteredException implements Exception {
  const VideoAlreadyRegisteredException();

  @override
  String toString() => 'Vídeo já cadastrado para esta categoria!';
}
