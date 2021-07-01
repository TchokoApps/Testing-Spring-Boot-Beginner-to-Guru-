package org.springframework.samples.petclinic.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    private ClinicService clinicService;

    List<Vet> vets = new ArrayList<>();

    Map<String, Object> model = new HashMap<>();

    @InjectMocks
    VetController underTest;

    @BeforeEach
    public void setUp() {

        given(clinicService.findVets()).willReturn(vets);
    }

    @Test
    public void showVetList() {

        vets.add(new Vet());

        // when
        underTest.showVetList(model);

        // then
        verify(clinicService).findVets();
        assertThat(model.size()).isEqualTo(1);
        assertThat(model.get("vets")).isInstanceOf(Vets.class);
    }

    @Test
    public void showResourcesVetList() {

        vets.add(new Vet());
        vets.add(new Vet());

        Vets vetsResponse = underTest.showResourcesVetList();

        verify(clinicService).findVets();
        assertThat(vetsResponse.getVetList().size()).isEqualTo(2);

    }

}