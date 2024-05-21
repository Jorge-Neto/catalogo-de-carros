package tech.developer.catalogodecarros

import jakarta.transaction.Transactional
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*
import tech.developer.catalogodecarros.entity.Make
import tech.developer.catalogodecarros.repository.MakeRepository

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class MakeResourceIntTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var repository: MakeRepository

    private val requestPath: String = "/make"

    @BeforeEach
    fun setUp() {
        repository.deleteAll()
        val savedMake = repository.save(buildMake("BYD"))
        assert(savedMake.id != 0) { "Saved Make should have a generated ID." }
    }

    @Test
    @Order(1)
    fun contextLoads() {
    }

    @Test
    @Order(2)
    fun `create a Make`() {
        val body = """{ "name": "Mazda" }""".trimIndent()

        mockMvc.post(requestPath) {
            contentType = MediaType.APPLICATION_JSON
            content = body
        }.andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.id") { isNumber() }
            jsonPath("$.name") { value("Mazda") }
        }//.andDo { print() }
    }

    @Test
    @Order(3)
    fun `get all Makes`() {
        repository.save(buildMake("Mazda"))

        mockMvc.get(requestPath)
            .andExpect {
                status { isOk() }
                jsonPath("$[0].name") { value("BYD") }
                jsonPath("$[1].name") { value("Mazda") }
            }//.andDo { print() }
    }

    @Test
    @Order(4)
    fun `get one Make`() {
        val make = repository.findAll().first()
        mockMvc.get("$requestPath/${make.id}")
            .andExpect {
                status { isOk() }
                jsonPath("$.id") { value(make.id) }
                jsonPath("$.name") { value("BYD") }
            }//.andDo { print() }
    }

    @Test
    @Order(5)
    fun `update a Make`() {
        val make = repository.findAll().first()
        val body = """{ "id": ${make.id}, "name": "Cherry" }""".trimIndent()
        mockMvc.put(requestPath) {
            contentType = MediaType.APPLICATION_JSON
            content = body
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.id") { value(make.id) }
            jsonPath("$.name") { value("Cherry") }
        }//.andDo { print() }
    }

    @Test
    @Order(6)
    fun `delete one Make`() {
        val make = repository.findAll().first()
        mockMvc.delete("$requestPath/${make.id}")
            .andExpect {
                status { isNoContent() }
            }//.andDo { print() }

        mockMvc.get("/make/${make.id}")
            .andExpect {
                status { isBadRequest() }
            }//.andDo { print() }
    }

    @Test
    @Order(7)
    fun `create a Make with empty name should fail`() {
        val body = """{ "name": "" }""".trimIndent()

        mockMvc.post(requestPath) {
            contentType = MediaType.APPLICATION_JSON
            content = body
        }.andExpect {
            status { isBadRequest() }
        }
    }

    @Test
    @Order(8)
    fun `update a non-existent Make should fail`() {
        val body = """{ "id": 9999, "name": "NonExistent" }""".trimIndent()
        mockMvc.put(requestPath) {
            contentType = MediaType.APPLICATION_JSON
            content = body
        }.andExpect {
            status { isBadRequest() }
        }
    }

    private fun buildMake(name: String, id: Int = -1) = Make(id, name)
}