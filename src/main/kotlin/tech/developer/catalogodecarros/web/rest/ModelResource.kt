package tech.developer.catalogodecarros.web.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.developer.catalogodecarros.dto.ModelDTO
import tech.developer.catalogodecarros.service.ModelService

@RestController
@RequestMapping("/model")
class ModelResource(private val modelService: ModelService) {

    @PostMapping
    fun createModel(@RequestBody modelDTO: ModelDTO): ResponseEntity<ModelDTO> =
        ResponseEntity(modelService.createModel(modelDTO), HttpStatus.CREATED)

    @GetMapping
    fun getModels(): ResponseEntity<List<ModelDTO>> =
        ResponseEntity.ok(modelService.getModels())


    @GetMapping("make/{makeId}")
    fun getModelsByMakeId(@PathVariable makeId: Int): ResponseEntity<List<ModelDTO>> =
        ResponseEntity.ok(modelService.findModelsByMakeId(makeId))


    @GetMapping("/{id}")
    fun getModel(@PathVariable id: Int): ResponseEntity<ModelDTO> =
        ResponseEntity.ok(modelService.getModel(id))

    @PutMapping()
    fun updateModel(@RequestBody modelDTO: ModelDTO): ResponseEntity<ModelDTO> =
        ResponseEntity.ok(modelService.updateModel(modelDTO))

    @DeleteMapping("/{id}")
    fun deleteModel(@PathVariable id: Int): ResponseEntity<Unit> =
        ResponseEntity(modelService.deleteModel(id), HttpStatus.NO_CONTENT)
}