package com.example.scannerocr.data;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ScanDao_Impl implements ScanDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ScanResult> __insertionAdapterOfScanResult;

  private final EntityDeletionOrUpdateAdapter<ScanResult> __deletionAdapterOfScanResult;

  public ScanDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfScanResult = new EntityInsertionAdapter<ScanResult>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `scans` (`id`,`imagePath`,`recognizedText`,`timestamp`,`folderName`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ScanResult value) {
        stmt.bindLong(1, value.getId());
        if (value.getImagePath() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getImagePath());
        }
        if (value.getRecognizedText() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getRecognizedText());
        }
        stmt.bindLong(4, value.getTimestamp());
        if (value.getFolderName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getFolderName());
        }
      }
    };
    this.__deletionAdapterOfScanResult = new EntityDeletionOrUpdateAdapter<ScanResult>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `scans` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ScanResult value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public Object insert(final ScanResult scan, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfScanResult.insert(scan);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object delete(final ScanResult scan, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfScanResult.handle(scan);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public LiveData<List<ScanResult>> getAllScans() {
    final String _sql = "SELECT * FROM scans ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"scans"}, false, new Callable<List<ScanResult>>() {
      @Override
      public List<ScanResult> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
          final int _cursorIndexOfRecognizedText = CursorUtil.getColumnIndexOrThrow(_cursor, "recognizedText");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfFolderName = CursorUtil.getColumnIndexOrThrow(_cursor, "folderName");
          final List<ScanResult> _result = new ArrayList<ScanResult>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ScanResult _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpImagePath;
            if (_cursor.isNull(_cursorIndexOfImagePath)) {
              _tmpImagePath = null;
            } else {
              _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            }
            final String _tmpRecognizedText;
            if (_cursor.isNull(_cursorIndexOfRecognizedText)) {
              _tmpRecognizedText = null;
            } else {
              _tmpRecognizedText = _cursor.getString(_cursorIndexOfRecognizedText);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpFolderName;
            if (_cursor.isNull(_cursorIndexOfFolderName)) {
              _tmpFolderName = null;
            } else {
              _tmpFolderName = _cursor.getString(_cursorIndexOfFolderName);
            }
            _item = new ScanResult(_tmpId,_tmpImagePath,_tmpRecognizedText,_tmpTimestamp,_tmpFolderName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<ScanResult>> getScansByFolder(final String folderName) {
    final String _sql = "SELECT * FROM scans WHERE folderName = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (folderName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, folderName);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"scans"}, false, new Callable<List<ScanResult>>() {
      @Override
      public List<ScanResult> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
          final int _cursorIndexOfRecognizedText = CursorUtil.getColumnIndexOrThrow(_cursor, "recognizedText");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfFolderName = CursorUtil.getColumnIndexOrThrow(_cursor, "folderName");
          final List<ScanResult> _result = new ArrayList<ScanResult>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ScanResult _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpImagePath;
            if (_cursor.isNull(_cursorIndexOfImagePath)) {
              _tmpImagePath = null;
            } else {
              _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            }
            final String _tmpRecognizedText;
            if (_cursor.isNull(_cursorIndexOfRecognizedText)) {
              _tmpRecognizedText = null;
            } else {
              _tmpRecognizedText = _cursor.getString(_cursorIndexOfRecognizedText);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpFolderName;
            if (_cursor.isNull(_cursorIndexOfFolderName)) {
              _tmpFolderName = null;
            } else {
              _tmpFolderName = _cursor.getString(_cursorIndexOfFolderName);
            }
            _item = new ScanResult(_tmpId,_tmpImagePath,_tmpRecognizedText,_tmpTimestamp,_tmpFolderName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
