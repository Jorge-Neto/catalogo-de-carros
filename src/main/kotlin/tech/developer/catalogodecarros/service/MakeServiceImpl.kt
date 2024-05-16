package tech.developer.catalogodecarros.service

import org.springframework.stereotype.Service
import tech.developer.catalogodecarros.dto.MakeDTO

@Service
class MakeServiceImpl: MakeService {
    override fun createMake(makeDTO: MakeDTO): MakeDTO {
        TODO("Not yet implemented")
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