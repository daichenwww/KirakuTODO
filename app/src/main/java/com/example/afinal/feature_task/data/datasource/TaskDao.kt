package com.example.afinal.feature_task.data.datasource

import androidx.room.*
import com.example.afinal.feature_task.domain.model.Task
import kotlinx.coroutines.flow.Flow

/*
        資料來源：TodoDatabase
        查詢前7, 14, 21, 28天的DoneTodo
        查詢所有DoneTodo
     */
/*
    TODO:
        1. 確認date儲存的格式
        2. get_cur_date、ger_day(禮拜幾)
        3. 建立dao
 */

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getTasks(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Query("Delete FROM task WHERE id = :id")
    suspend fun deleteTask(id: Int)

    // edited by: zshzzz
    // 1. searchTaskInRange
    @Query("SELECT COUNT(*) FROM task WHERE planDate >= :start AND planDate < :end AND done=1")
    suspend fun getDoneTaskNumberInRange(start: String, end: String): Int

    // 2. find the task number which has been done
    @Query("SELECT COUNT(*) FROM task WHERE done = 1")
    suspend fun getDoneTaskNumber(): Int

}

/*
Search in a range
SELECT *
  FROM MyTable
  WHERE [dateColumn] > '3/1/2009' AND [dateColumn] <= DATEADD(day,1,'3/31/2009')
 */