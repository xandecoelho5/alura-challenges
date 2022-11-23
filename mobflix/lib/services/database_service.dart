abstract class IDatabaseService {
  Future<void> init();

  Future<void> close();

  Future<void> insert(String table, Map<String, dynamic> data);

  Future<void> update(String table, Map<String, dynamic> data);

  Future<List<Map<String, dynamic>>> query(String table);

  Future<List<Map<String, dynamic>>> rawQuery(String query);

  Future<void> delete(String table, dynamic id);
}
