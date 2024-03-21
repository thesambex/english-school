package com.schoolmanager.english.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoolmanager.english.application.services.v1.ClassesServiceV1;
import com.schoolmanager.english.controller.v1.ClassesControllerV1;
import com.schoolmanager.english.domain.dtos.courses.CreateClassDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

@WebMvcTest(controllers = ClassesControllerV1.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ClassesControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClassesServiceV1 classesService;

    @Test
    void createCourseClassShouldReturnCreated() throws Exception {
        CreateClassDTO createClass = new CreateClassDTO(
                null,
                "English 1",
                "ENTRY",
                "NOCTURNAL",
                "MONDAY",
                UUID.fromString("6a23ee6c-20ab-4df2-96b7-1ed5336b7815"),
                UUID.fromString("61f5a31d-f922-4b6d-8603-6270b1fed1a4"));

        when(classesService.createClass(createClass)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).build());
        this.mockMvc.perform(post("/api/v1/classes/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(new ObjectMapper().writeValueAsBytes(createClass)
                        )
                ).andDo(print())
                .andExpect(status().isCreated());
    }

}
