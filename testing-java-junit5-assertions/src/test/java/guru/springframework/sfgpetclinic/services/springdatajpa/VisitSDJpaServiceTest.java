package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    private final Visit expectedVisit = new Visit();

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitSDJpaService underTest;
    private Set<Visit> expectedVisits;

    @BeforeEach
    public void setUp() {

    }

    @Test
    void findAll() {

        expectedVisits = Stream.of(new Visit(), new Visit()).collect(Collectors.toSet());

        when(visitRepository.findAll()).thenReturn(expectedVisits);

        final Set<Visit> visits = underTest.findAll();

        verify(visitRepository).findAll();

        assertEquals(expectedVisits, visits);
        assertEquals(2, visits.size());

    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(expectedVisit));

        final Visit visit = underTest.findById(anyLong());

        verify(visitRepository).findById(anyLong());

        assertEquals(expectedVisit, visit);
    }

    @Test
    public void findByIdShouldReturnNull() {
        when(visitRepository.findById(anyLong())).thenReturn(null);
        assertThrows(NullPointerException.class, () -> underTest.findById(1L));
    }

    @Test
    void save() {

        when(visitRepository.save(any(Visit.class))).thenReturn(new Visit());

        final Visit visit = underTest.save(new Visit());

        verify(visitRepository).save(any(Visit.class));
        Assertions.assertNotNull(visit);

    }

    @Test
    void delete() {

        underTest.delete(new Visit());

        verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    void deleteById() {

        underTest.deleteById(1L);

        verify(visitRepository).deleteById(anyLong());
    }
}