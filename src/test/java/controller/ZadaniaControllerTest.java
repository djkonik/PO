package controller;

import static org.junit.Assert.assertEquals;
import model.entity.Ograniczenie;
import model.entity.Zadanie;
import model.repository.OgraniczenieRepository;
import model.repository.ZadanieRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class ZadaniaControllerTest {


    @Mock
    private OgraniczenieRepository ograniczenieRepository;
    
    @Mock
    private ZadanieRepository zadanieRepository;

    @InjectMocks
    private static ZadaniaController zadaniaControler = new ZadaniaController();

    private Ograniczenie ograniczenie;
    private Zadanie zadanie;
    private String slowaKluczowe;
    
    @Before
    public void init() {
        zadanie = new Zadanie();
        zadanie.setId(1);
        
        Mockito.when(zadanieRepository.findByIdLike(zadanie.getId())).thenReturn(zadanie);
    }

    @Test
    public void testCreateOgraniczenie() {
    	// given
        ograniczenie = new Ograniczenie();
        ograniczenie.setJezyk("Jezyk");
        ograniczenie.setNazwa("Nazwa");
        
        Ograniczenie zapisaneOgraniczenie = new Ograniczenie();
        zapisaneOgraniczenie.setJezyk("Jezyk");
        zapisaneOgraniczenie.setNazwa("Nazwa");
        zapisaneOgraniczenie.setId(1);
        
        slowaKluczowe = "testowe. slowa kluczowe;";
        
        Mockito.when(ograniczenieRepository.save(ograniczenie)).thenReturn(zapisaneOgraniczenie);
        
        // when
        ResponseEntity<?> serverResponse = zadaniaControler.create(ograniczenie, slowaKluczowe, zadanie.getId(), null);
        
        // then
        assertEquals(HttpStatus.OK, serverResponse.getStatusCode());
        assertEquals(zapisaneOgraniczenie.getId(), serverResponse.getBody());
    }
    
    @Test
    public void testDontCreateOgraniczenieWithoutSlowaKluczowe() {
    	// given
        ograniczenie = new Ograniczenie();
        ograniczenie.setJezyk("Jezyk");
        ograniczenie.setNazwa("Nazwa");
        
        slowaKluczowe = "   ";

        // when
        ResponseEntity<?> serverResponse = zadaniaControler.create(ograniczenie, slowaKluczowe, zadanie.getId(), null);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, serverResponse.getStatusCode());
        assertEquals(0, serverResponse.getBody());
    }
    
    @Test
    public void testDontCreateOgraniczenieWithoutJezyk() {
    	// given
        ograniczenie = new Ograniczenie();
        ograniczenie.setJezyk("");
        ograniczenie.setNazwa("Nazwa");
        
        slowaKluczowe = "testowe. slowa kluczowe;";
        
        // when
        ResponseEntity<?> serverResponse = zadaniaControler.create(ograniczenie, slowaKluczowe, zadanie.getId(), null);
        
        
        // then
        assertEquals(HttpStatus.BAD_REQUEST, serverResponse.getStatusCode());
        assertEquals(0, serverResponse.getBody());
    }
    
    @Test
    public void testDontCreateOgraniczenieWithoutNazwa() {
    	// given
        ograniczenie = new Ograniczenie();
        ograniczenie.setJezyk("Jezyk");
        ograniczenie.setNazwa(null);
        
        slowaKluczowe = "testowe. slowa kluczowe;";
        
        // when
        ResponseEntity<?> serverResponse = zadaniaControler.create(ograniczenie, slowaKluczowe, zadanie.getId(), null);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, serverResponse.getStatusCode());
        assertEquals(0, serverResponse.getBody());
    }

}