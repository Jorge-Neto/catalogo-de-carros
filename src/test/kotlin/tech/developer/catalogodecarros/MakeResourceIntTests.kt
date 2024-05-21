package tech.developer.catalogodecarros

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
class MakeResourceIntTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var repository: MakeRepository

    @Test
    fun contextLoads() {
    }

    @BeforeAll
    fun insertData() {
        repository.save(buildMake("BYD"))
    }

    @Test
    fun `get one Make`() {
        val getPath = "/make"
        mockMvc.get("$getPath/1")
            .andExpect {
                status { isOk() }
                jsonPath("$.id") { value(1) }
            }//.andDo { print() }
    }

    @Test
    fun `update a Make`() {
        val putPath = "/make"
        val body = """{ "id": 1, "name": "Cherry" }""".trimIndent()
        mockMvc.put(putPath) {
            contentType = MediaType.APPLICATION_JSON
            content = body
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json("""{"id":1,"name":"Cherry"}""") }
        }//.andDo { print() }
    }

    @Test
    fun `delete one Make`() {
        val deletePath = "/make"
        mockMvc.delete("$deletePath/1")
            .andExpect {
                status { isNoContent() }
            }//.andDo { print() }
    }

    @Test
    fun `create a Make`() {
        val postPath = "/make"
        val body = """
			{
				"name": "Mazda"
			}
		""".trimIndent()

        mockMvc.post(postPath) {
            contentType = MediaType.APPLICATION_JSON
            content = body
        }.andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }//.andDo { print() }
    }

    @AfterAll
    fun `get all Makes`() {
        val getPath = "/make"

        mockMvc.get(getPath) {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$[0].name") { value("Mazda") }
        }.andDo { print() }
    }

    private fun buildMake(
        name: String,
        id: Int = -1,
    ) = Make(id, name)
}
