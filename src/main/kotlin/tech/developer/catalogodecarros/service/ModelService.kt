package tech.developer.catalogodecarros.service

import tech.developer.catalogodecarros.dto.ModelDTO

interface ModelService {
    fun createModel(modelDTO: ModelDTO): ModelDTO

    fun getModels(): List<ModelDTO>

    fun findModelsByMakeId(makeId: Int): List<ModelDTO>

    fun getModel(id: Int): ModelDTO

    fun updateModel(modelDTO: ModelDTO): ModelDTO

    fun deleteModel(id: Int)
}