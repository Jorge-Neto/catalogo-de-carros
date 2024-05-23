package tech.developer.catalogodecarros.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import tech.developer.catalogodecarros.entity.Model

interface ModelRepository : CrudRepository<Model, Int> {

    @Query("SELECT mo FROM Model as mo")
    fun getAllModels(): List<Model>

    @Query("SELECT mo FROM Model mo WHERE mo.makeId = :makeId")
    fun findModelsByMakeId(@Param("makeId") makeId: Int): List<Model>

    @Query("SELECT CASE WHEN count(mo) > 0 THEN true ELSE false END FROM Model mo WHERE mo.makeId = :makeId")
    fun existsByMakeId(@Param("makeId") makeId: Int): Boolean
}