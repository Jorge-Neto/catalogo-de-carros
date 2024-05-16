package tech.developer.catalogodecarros.utils.mapper

import org.springframework.stereotype.Service
import tech.developer.catalogodecarros.dto.MakeDTO
import tech.developer.catalogodecarros.entity.Make

@Service
class MakeMapper: Mapper<MakeDTO, Make> {
    override fun fromEntity(entity: Make): MakeDTO = MakeDTO(
        entity.id,
        entity.name,
    )

    override fun toEntity(domain: MakeDTO): Make = Make(
        domain.id,
        domain.name,
    )
}