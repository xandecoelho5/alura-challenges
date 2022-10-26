class EmptyUrlException implements Exception {
  const EmptyUrlException();

  @override
  String toString() {
    return 'Informe a URL do vídeo';
  }
}
