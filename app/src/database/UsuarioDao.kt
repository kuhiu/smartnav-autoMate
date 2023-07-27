package database

import androidx.room.*
import Entities.Usuarios


@Dao
public interface UsuarioDao {

    @Query("SELECT * FROM Usuarios ORDER BY nombre")
    fun loadAllPersons(): MutableList<Usuarios?>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPerson(user: Usuarios?) : Long

    @Update
    fun updatePerson(user: Usuarios?)

    @Delete
    fun deletePerson(user: Usuarios?)

    @Query("SELECT * FROM Usuarios WHERE nombre = :nombre")
    fun loadPersonByName(nombre: String): Usuarios?

}