package tech.developer.catalogodecarros.service

import tech.developer.catalogodecarros.dto.MakeDTO

interface MakeService {
    fun createMake(makeDTO: MakeDTO): MakeDTO

    fun getMakes(): List<MakeDTO>

    fun getMake(id: Int): MakeDTO

    fun updateMake(makeDTO: MakeDTO): MakeDTO

    fun deleteMake(id: Int)
}