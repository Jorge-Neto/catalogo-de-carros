package tech.developer.catalogodecarros.service

import org.springframework.stereotype.Service
import tech.developer.catalogodecarros.dto.MakeDTO
import tech.developer.catalogodecarros.repository.MakeRepository
import tech.developer.catalogodecarros.utils.mapper.MakeMapper

@Service
class MakeServiceImpl(
    private val makeRepository: MakeRepository,
    private var makeMapper: MakeMapper
) : MakeService {
    @Suppress("DEPRECATED_IDENTITY_EQUALS")
    override fun createMake(makeDTO: MakeDTO): MakeDTO {

        if (makeDTO.id !== -1) throw IllegalArgumentException("Id must be null or -1")

        val make = makeRepository.save(makeMapper.toEntity(makeDTO))
        return makeMapper.fromEntity(make);
    }

    override fun getMakes(): List<MakeDTO> {
        TODO("Not yet implemented")
    }

    override fun getMake(id: Int): MakeDTO {
        TODO("Not yet implemented")
    }

    override fun updateMake(makeDTO: MakeDTO): MakeDTO {
        TODO("Not yet implemented")
    }

    override fun deleteMake(id: Int) {
        TODO("Not yet implemented")
    }
}