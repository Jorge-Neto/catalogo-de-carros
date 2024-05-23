package tech.developer.catalogodecarros.service

import org.springframework.stereotype.Service
import tech.developer.catalogodecarros.dto.ModelDTO
import tech.developer.catalogodecarros.repository.MakeRepository
import tech.developer.catalogodecarros.repository.ModelRepository
import tech.developer.catalogodecarros.utils.exceptions.MakeException
import tech.developer.catalogodecarros.utils.exceptions.ModelException
import tech.developer.catalogodecarros.utils.mapper.ModelMapper

@Service
class ModelServiceImpl(
    private val modelRepository: ModelRepository,
    private val makeRepository: MakeRepository,
    private var modelMapper: ModelMapper
) : ModelService {
    override fun createModel(modelDTO: ModelDTO): ModelDTO {
        @Suppress("DEPRECATED_IDENTITY_EQUALS") if (modelDTO.id !== -1) throw IllegalArgumentException("ID is not required in the request")

        if (modelDTO.name.isEmpty()) throw IllegalArgumentException("Name cannot be empty")

        val makeExists = makeRepository.existsById(modelDTO.makeId)
        if (!makeExists) throw MakeException("Make with id ${modelDTO.makeId} is not present")

        val model = modelRepository.save(modelMapper.toEntity(modelDTO))
        return modelMapper.fromEntity(model)
    }

    override fun getModels(): List<ModelDTO> {
        val modelsList = modelRepository.getAllModels()
        if (modelsList.isEmpty()) throw ModelException("List of models is empty")

        return modelsList.map {
            modelMapper.fromEntity(it)
        }
    }

    override fun findModelsByMakeId(makeId: Int): List<ModelDTO> {
        val modelsList = modelRepository.findModelsByMakeId(makeId)
        if (modelsList.isEmpty()) throw ModelException("There is no model with make id $makeId")

        return modelsList.map {
            modelMapper.fromEntity(it)
        }
    }

    override fun getModel(id: Int): ModelDTO {
        val optionalModel = modelRepository.findById(id)
        val model = optionalModel.orElseThrow { ModelException("Model with id $id is not present") }
        return modelMapper.fromEntity(model)
    }

    override fun updateModel(modelDTO: ModelDTO): ModelDTO {
        val exists = modelRepository.existsById(modelDTO.id)
        if (!exists)
            throw ModelException("Model with id ${modelDTO.id} is not present")

        if (modelDTO.name == "Default Model" || modelDTO.makeId == 0)
            throw ModelException("Complete model object is expected")

        modelRepository.save(modelMapper.toEntity(modelDTO))
        return modelDTO
    }

    override fun deleteModel(id: Int) {
        val exists = modelRepository.existsById(id)
        if (!exists) throw ModelException("Model with id $id is not present")

        modelRepository.deleteById(id)
    }
}