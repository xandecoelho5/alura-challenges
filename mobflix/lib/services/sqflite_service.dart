import 'package:mobflix/services/database_service.dart';
import 'package:sqflite/sqflite.dart';

class SqfliteService implements IDatabaseService {
  final String _path = 'mobflix.db';
  Database? database;

  @override
  Future<void> init() async {
    // await deleteDatabase(_path);

    database ??= await openDatabase(
      _path,
      onCreate: (db, version) async {
        await db.execute(
            'CREATE TABLE categories (id INTEGER PRIMARY KEY autoincrement, name TEXT, color TEXT)');
        await db.execute(
            'CREATE TABLE videos (id INTEGER PRIMARY KEY autoincrement, url TEXT, thumbnail TEXT, category_id INTEGER, FOREIGN KEY (category_id) REFERENCES categories (id))');
        await db.execute(
            "INSERT INTO categories (name, color) VALUES ('Front End', '0xFF5781EF')");
        await db.execute(
            "INSERT INTO categories (name, color) VALUES ('Programação', '0xFF19940F')");
        await db.execute(
            "INSERT INTO categories (name, color) VALUES ('Mobile', '0xFFD82D2D')");
        await db.execute(
            "INSERT INTO categories (name, color) VALUES ('Data Science', '0xFFFFBA05')");
        await db.execute(
            "INSERT INTO categories (name, color) VALUES ('Devops', '0xFFff9337')");
        await db.execute(
            "INSERT INTO categories (name, color) VALUES ('UX e Design', '0xFFDC6EBE')");
      },
      version: 1,
    );
  }

  @override
  Future<void> close() async {
    await database?.close();
  }

  Future<void> ensureInit() async {
    if (database == null) {
      await init();
    }
  }

  @override
  Future<List<Map<String, dynamic>>> query(String table) async {
    await ensureInit();
    return await database?.query(table) ?? [];
  }

  @override
  Future<List<Map<String, dynamic>>> rawQuery(String query) async {
    await ensureInit();
    return await database?.rawQuery(query) ?? [];
  }

  @override
  Future<void> insert(String table, Map<String, dynamic> data) async {
    await database?.insert(table, data);
  }

  @override
  Future<void> update(String table, Map<String, dynamic> data) async {
    await database?.update(
      table,
      data,
      where: 'id = ?',
      whereArgs: [data['id']],
    );
  }

  @override
  Future<void> delete(String table, dynamic id) async {
    await database?.delete(table, where: 'id = ?', whereArgs: [id]);
  }
}
