package tech.developer.catalogodecarros.service

import org.springframework.stereotype.Service
import tech.developer.catalogodecarros.dto.MakeDTO
import tech.developer.catalogodecarros.repository.MakeRepository
import tech.developer.catalogodecarros.repository.ModelRepository
import tech.developer.catalogodecarros.utils.exceptions.MakeException
import tech.developer.catalogodecarros.utils.mapper.MakeMapper

@Service
class MakeServiceImpl(
    private val makeRepository: MakeRepository,
    private val modelRepository: ModelRepository,
    private var makeMapper: MakeMapper
) : MakeService {
    override fun createMake(makeDTO: MakeDTO): MakeDTO {
        if (makeDTO.id != -1) throw IllegalArgumentException("ID is not required in the request")

        if (makeDTO.name.isEmpty()) throw IllegalArgumentException("Name cannot be empty")

        val make = makeRepository.save(makeMapper.toEntity(makeDTO))
        return makeMapper.fromEntity(make)
    }

    override fun getMakes(): List<MakeDTO> {
        val makesList = makeRepository.getAllMakes()

        if (makesList.isEmpty()) throw MakeException("List of makes is empty")

        return makesList.map {
            makeMapper.fromEntity(it)
        }
    }

    override fun getMake(id: Int): MakeDTO {
        val optionalMake = makeRepository.findById(id)
        val make = optionalMake.orElseThrow { MakeException("Make with id $id is not present") }
        return makeMapper.fromEntity(make)
    }

    override fun updateMake(makeDTO: MakeDTO): MakeDTO {
        val exists = makeRepository.existsById(makeDTO.id)
        if (!exists)
            throw MakeException("Make with id ${makeDTO.id} is not present")

        if (makeDTO.name == "Default Make")
            throw MakeException("Complete make object is expected")

        makeRepository.save(makeMapper.toEntity(makeDTO))
        return makeDTO
    }

    override fun deleteMake(id: Int) {
        val exists = makeRepository.existsById(id)
        if (!exists)
            throw MakeException("Make with id $id is not present")

        val existsInModel = modelRepository.existsByMakeId(id)
        if (existsInModel)
            throw MakeException("Make with id $id is present in models list")

        makeRepository.deleteById(id)
    }
}