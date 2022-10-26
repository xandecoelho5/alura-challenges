class NoneCategorySelectedException implements Exception {
  const NoneCategorySelectedException();

  @override
  String toString() {
    return 'Selecione uma categoria';
  }
}
