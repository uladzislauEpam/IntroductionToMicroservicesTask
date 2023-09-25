package com.epam.uladzislau.resource;

import com.epam.uladzislau.resource.controller.ResourceController;
import com.epam.uladzislau.resource.repository.ResourceRepository;
import com.epam.uladzislau.resource.service.ResourceService;

import com.amazonaws.services.s3.AmazonS3;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ResourceController.class)
public class ResourceApplicationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ResourceService resourceService;

    @MockBean
    ResourceRepository resourceRepository;

    @MockBean
    AmazonS3 amazonS3;

    @MockBean
    Queue queue;

    @MockBean
    TopicExchange topicExchange;

    @MockBean
    Binding binding;

    @MockBean
    MessageConverter messageConverter;

    @MockBean
    AmqpTemplate amqpTemplate;

    @Test
    public void ifContextUp() throws Exception {
        Mockito.when(resourceService.getAll(0)).thenReturn(Page.empty());
        MvcResult mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/resource/all/0").accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        System.out.println(mvcResult.getResponse());

        Mockito.verify(resourceService).getAll(0);
    }
}
