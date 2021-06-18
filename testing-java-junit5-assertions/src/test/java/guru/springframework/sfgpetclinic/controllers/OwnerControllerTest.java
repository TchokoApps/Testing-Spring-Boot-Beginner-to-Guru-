package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.fauxspring.ModelAndView;
import guru.springframework.sfgpetclinic.fauxspring.WebDataBinder;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    private Model model;

    @Mock
    private OwnerService ownerService;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private WebDataBinder webDataBinder;

    @InjectMocks
    private OwnerController underTest;

    @Captor
    private ArgumentCaptor<String> strCaptor;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void processCreationFormHasErrors() {
        // given
        final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
        final Owner owner = new Owner(1L, "Charles", "Tientcheu");
        when(bindingResult.hasErrors()).thenReturn(true);

        // when
        final String result = underTest.processCreationForm(owner, bindingResult);

        // then
        assertEquals(VIEWS_OWNER_CREATE_OR_UPDATE_FORM, result);
        verify(bindingResult, times(1)).hasErrors();
        verify(ownerService, never()).save(any(Owner.class));
    }

    @Test
    public void processCreationFormHasNoErrors() {

        // given
        final Owner owner = new Owner(1L, "Charles", "Tientcheu");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(ownerService.save(owner)).thenReturn(owner);

        // when
        final String result = underTest.processCreationForm(owner, bindingResult);

        // then
        verify(ownerService).save(owner);
        assertEquals("redirect:/owners/1", result);
    }

    @Test
    public void processFindForm_OwerLastNameIsNull() {

        // give
        final Owner owner = new Owner(1L, null, null);
        final Model model = mock(Model.class);
        final BindingResult bResult = mock(BindingResult.class);

        // when
        underTest.processFindForm(owner, bResult, model);

        // then
        assertEquals("", owner.getLastName());
    }

    @Test
    public void processFindWildcardString() {

        // given
        final Owner owner = new Owner(1L, "Charles", "Tchokouaha");
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        when(ownerService.findAllByLastNameLike(captor.capture())).thenReturn(anyList());
        // when
        underTest.processFindForm(owner, bindingResult, null);

        // then
        verify(ownerService).findAllByLastNameLike(anyString());
        assertEquals("%Tchokouaha%", captor.getValue());
    }

    @Test
    public void processFindFormWithStringArgumentCaptor() {

        // given
        final Owner owner = new Owner(1L, "Charles", "Tchokouaha");
        when(ownerService.findAllByLastNameLike(strCaptor.capture())).thenReturn(anyList());
        // when
        underTest.processFindForm(owner, bindingResult, null);

        // then
        verify(ownerService).findAllByLastNameLike(anyString());
        assertEquals("%Tchokouaha%", strCaptor.getValue());
    }

    @Test
    public void processFind_FormWithEmptyList() {

        // given
        final List<Owner> owners = new ArrayList<>();
        final Owner owner = new Owner(1L, "Charles", "Tientcheu");
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners);

        // when
        final String result = underTest.processFindForm(owner, bindingResult, model);

        // then
        verifyZeroInteractions(model);
        verify(ownerService, Mockito.times(1)).findAllByLastNameLike(anyString());
        assertEquals("owners/findOwners", result);

    }

    @Test
    public void processFindForm_WithOneElementInTheList() {

        // given
        final Owner owner = new Owner(1L, "Charles", "Tientcheu");
        final List<Owner> owners = Collections.singletonList(owner);
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners);

        // when
        final String result = underTest.processFindForm(owner, bindingResult, model);

        // then
        assertEquals("redirect:/owners/1", result);

    }

    @Test
    public void processFindForm_WithMoreThanOneElementInTheList() {

        final Owner owner = new Owner(1L, "Charles", "Tientcheu");
        final List<Owner> owners = Arrays.asList(new Owner(1L, "Alain", "Tchokonte"),
                new Owner(2L, "Patrick", "Bebga"));
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners);

        InOrder inOrder = inOrder(ownerService, model);

        // when
        final String result = underTest.processFindForm(owner, bindingResult, model);

        // then
        assertEquals("owners/ownersList", result);
        inOrder.verify(ownerService).findAllByLastNameLike(anyString());
        inOrder.verify(model).addAttribute(anyString(), anyList());

    }

    @Test
    public void setAllowedFields() {
        underTest.setAllowedFields(webDataBinder);

        verify(webDataBinder).setDisallowedFields("id");
    }

    @Test
    public void findOwners() {

        String owners = underTest.findOwners(model);
        verify(model).addAttribute(anyString(), any(Owner.class));
        assertEquals("owners/findOwners", owners);
    }

    @Test
    public void showOwner() {
        when(ownerService.findById(anyLong())).thenReturn(any());

        ModelAndView modelAndView = underTest.showOwner(1L);

        assertNotNull(modelAndView);
    }

    @Test
    public void initCreationForm() {

        final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

        String result = underTest.initCreationForm(model);

        verify(model).addAttribute(anyString(), any(Owner.class));
        assertEquals(VIEWS_OWNER_CREATE_OR_UPDATE_FORM, result);

    }

    @Test
    public void initUpdateOwnerForm() {

        final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

        when(ownerService.findById(1L)).thenReturn(new Owner(1L, "x", "y"));

        final String result = underTest.initUpdateOwnerForm(1L, model);

        verify(model).addAttribute(any(Owner.class));
        assertEquals(VIEWS_OWNER_CREATE_OR_UPDATE_FORM, result);

    }

    @Test
    public void processUpdateOwnerForm_WithBindingResultHasErrors() {
        final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
        when(bindingResult.hasErrors()).thenReturn(true);
        final Owner owner = new Owner(1L, "Foo", "Bar");

        String result = underTest.processUpdateOwnerForm(owner, bindingResult, 1L);

        verify(bindingResult).hasErrors();
        assertEquals(VIEWS_OWNER_CREATE_OR_UPDATE_FORM, result);

    }

    @Test
    public void processUpdateOwnerForm_WithBindingResultHasNoErrors() {
        final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
        when(bindingResult.hasErrors()).thenReturn(false);
        final Owner owner = new Owner(1L, "Foo", "Bar");
        when(ownerService.save(owner)).thenReturn(owner);

        String result = underTest.processUpdateOwnerForm(owner, bindingResult, 1L);

        verify(bindingResult).hasErrors();
        verify(ownerService).save(owner);
        assertEquals("redirect:/owners/1", result);

    }


}