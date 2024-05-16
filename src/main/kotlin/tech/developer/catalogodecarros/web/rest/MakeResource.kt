package tech.developer.catalogodecarros.web.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MakeResource {

    @PostMapping
    fun createMake(): ResponseEntity {}
}