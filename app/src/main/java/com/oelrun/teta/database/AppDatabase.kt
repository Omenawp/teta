package com.oelrun.teta.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.oelrun.teta.database.dao.ActorDao
import com.oelrun.teta.database.dao.GenreDao
import com.oelrun.teta.database.dao.MovieDao
import com.oelrun.teta.database.dao.ProfileDao
import com.oelrun.teta.database.entities.*
import com.oelrun.teta.database.entities.relations.MovieActorCrossRef
import com.oelrun.teta.database.entities.relations.MovieGenreCrossRef
import com.oelrun.teta.database.entities.relations.ProfileGenreCrossRef

@Database(
    entities = [
        Movie::class,
        Actor::class,
        Genre::class,
        Profile::class,
        MovieActorCrossRef::class,
        MovieGenreCrossRef::class,
        ProfileGenreCrossRef::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun actorDao(): ActorDao
    abstract fun genreDao(): GenreDao
    abstract fun profileDao(): ProfileDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val migration1_2 = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE profile ADD COLUMN password TEXT NOT NULL DEFAULT '' ")
            }
        }

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "teta_database"
                    ).addMigrations(migration1_2).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}