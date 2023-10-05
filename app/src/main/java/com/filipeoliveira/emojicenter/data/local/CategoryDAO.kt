package com.filipeoliveira.emojicenter.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.filipeoliveira.emojicenter.data.local.model.CategoryDB
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDAO {
    @Query("SELECT * FROM categorydb")
    fun getAllCategories() : Flow<List<CategoryDB>>

    @Insert
    fun insertAll(categoryDB: CategoryDB)

    @Delete
    fun delete(categoryDB: CategoryDB)
}