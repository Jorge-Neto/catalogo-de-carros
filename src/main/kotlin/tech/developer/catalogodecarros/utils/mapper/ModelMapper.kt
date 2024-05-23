package tech.developer.catalogodecarros.utils.mapper

import org.springframework.stereotype.Service
import tech.developer.catalogodecarros.dto.ModelDTO
import tech.developer.catalogodecarros.entity.Model
import tech.developer.catalogodecarros.repository.MakeRepository

@Service
class ModelMapper(
    private val makeRepository: MakeRepository,
) : Mapper<ModelDTO, Model> {
    override fun fromEntity(entity: Model): ModelDTO = ModelDTO(
        entity.id,
        entity.name,
        entity.makeId,
    )

    override fun toEntity(domain: ModelDTO): Model {
        val make = makeRepository.findById(domain.makeId)
            .orElseThrow { IllegalArgumentException("Make not found") }

        return Model(
            domain.id,
            domain.name,
            make.id
        )
    }

}