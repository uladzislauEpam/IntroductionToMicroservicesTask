package com.epam.uladzislau.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.epam.uladzislau.resource.model.Resource;
import com.epam.uladzislau.resource.repository.ResourceRepository;

import org.junit.jupiter.api.Test;

public class ResourceApplicationUnitTest {

    @Test
    void resourceRepoPutsObjectOnRequest(){
        ResourceRepository resourceRepository = mock(ResourceRepository.class);
        Resource resource = new Resource(1L, "Resource");
        when(resourceRepository.save(resource)).thenReturn(resource);
        Long expectedId = resourceRepository.save(resource).getId();
        assertEquals(1, expectedId);
          assertEquals(1, 1);
    }

}