package tech.developer.catalogodecarros.web.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.developer.catalogodecarros.dto.MakeDTO
import tech.developer.catalogodecarros.service.MakeService

@RestController
@RequestMapping("/make")
class MakeResource(private val makeService: MakeService) {

    @PostMapping
    fun createMake(@RequestBody makeDTO: MakeDTO): ResponseEntity<MakeDTO> =
        ResponseEntity(makeService.createMake(makeDTO), HttpStatus.CREATED)

    @GetMapping
    fun getMakes(): ResponseEntity<List<MakeDTO>> =
        ResponseEntity.ok(makeService.getMakes())

    @GetMapping("/{id}")
    fun getMake(@PathVariable id: Int): ResponseEntity<MakeDTO> =
        ResponseEntity.ok(makeService.getMake(id))

    @PutMapping()
    fun updateMake(@RequestBody makeDTO: MakeDTO): ResponseEntity<MakeDTO> =
        ResponseEntity.ok(makeService.updateMake(makeDTO))

    @DeleteMapping("/{id}")
    fun deleteMake(@PathVariable id: Int): ResponseEntity<Unit> =
        ResponseEntity(makeService.deleteMake(id), HttpStatus.NO_CONTENT)
}