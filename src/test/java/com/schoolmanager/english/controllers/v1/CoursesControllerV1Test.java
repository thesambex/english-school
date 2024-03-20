package com.schoolmanager.english.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoolmanager.english.application.services.v1.CoursesServiceV1;
import com.schoolmanager.english.controller.v1.CoursesControllerV1;
import com.schoolmanager.english.domain.dtos.courses.CourseDTO;
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

@WebMvcTest(controllers = CoursesControllerV1.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CoursesControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoursesServiceV1 coursesService;

    @Test
    void createCourseShouldReturnCreated() throws Exception {
        CourseDTO course = new CourseDTO(null, "English", "ENGLISH");

        when(coursesService.createCourse(course)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).build());
        this.mockMvc.perform(post("/api/v1/courses/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(new ObjectMapper().writeValueAsBytes(course))
                ).andDo(print())
                .andExpect(status().isCreated());
    }

}
