package org.springframework.samples.petclinic.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;

import java.util.Collection;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClinicServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private VetRepository vetRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    ClinicServiceImpl underTest;

    @Test
    public void findPetTypes() {
        when(petRepository.findPetTypes()).thenReturn(Collections.emptyList());

        Collection<PetType> petTypesResult = underTest.findPetTypes();

        verify(petRepository).findPetTypes();
        Assertions.assertThat(petTypesResult).isNotNull();
    }

    @Test
    public void findOwnerById() {
        when(ownerRepository.findById(anyInt())).thenReturn(new Owner());

        Owner ownerResponse = ownerRepository.findById(anyInt());

        verify(ownerRepository).findById(anyInt());
        Assertions.assertThat(ownerResponse).isNotNull();

    }


}