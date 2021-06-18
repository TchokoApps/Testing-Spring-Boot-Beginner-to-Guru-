package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    private SpecialitySDJpaService underTest;

    @BeforeEach
    void setUp() {
    }

    @Test
    void delete() {
        underTest.delete(new Speciality());
    }

    @Test
    void deleteById() {
        underTest.deleteById(1L);
        underTest.deleteById(1L);
        verify(specialtyRepository, times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast() {
        underTest.deleteById(1L);
        underTest.deleteById(1L);
        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    public void findById() {

        // Arrange - Given
        final Speciality expectedSpeciality = new Speciality();
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(expectedSpeciality));

        // Act - When
        final Speciality speciality = underTest.findById(1L);

        // Verify - Then
        verify(specialtyRepository).findById(anyLong());
        Assertions.assertEquals(expectedSpeciality, speciality);
        Assertions.assertNotNull(speciality);
    }

    @Test
    public void deleteByObject() {
        Speciality speciality = new Speciality();

        underTest.delete(speciality);

        verify(specialtyRepository).delete(any(Speciality.class));
        
    }
}