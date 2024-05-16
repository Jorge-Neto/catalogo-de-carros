package tech.developer.catalogodecarros.web.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import tech.developer.catalogodecarros.dto.MakeDTO
import tech.developer.catalogodecarros.service.MakeService

@RestController
class MakeResource(private val makeService: MakeService) {

    @PostMapping
    fun createMake(@RequestBody makeDTO: MakeDTO): ResponseEntity<MakeDTO> =
        ResponseEntity(makeService.createMake(makeDTO), HttpStatus.CREATED)
}