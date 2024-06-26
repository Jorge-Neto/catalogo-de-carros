package tech.developer.catalogodecarros.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import tech.developer.catalogodecarros.entity.Make

interface MakeRepository: CrudRepository<Make, Int> {

    @Query("SELECT ma FROM Make as ma")
    fun getAllMakes(): List<Make>
}