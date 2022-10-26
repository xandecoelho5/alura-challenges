class InvalidUrlException implements Exception {
  const InvalidUrlException();

  @override
  String toString() => 'URL inválida';
}
