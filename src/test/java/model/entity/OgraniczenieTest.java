package model.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class OgraniczenieTest {
	
	private Ograniczenie ograniczenie;
	
	@Before
	public void init() {
		ograniczenie = new Ograniczenie();
	}

	@Test
	public void ograniczenieIsValid() {
		//given
		ograniczenie.setNazwa("nazwa");
		ograniczenie.setJezyk("jezyk");
		ograniczenie.setSlowaKluczowe(new ArrayList<SlowoKluczowe>() {{
			   add(new SlowoKluczowe());
			   add(new SlowoKluczowe());
			}});
		//when then
		assertTrue(ograniczenie.isValid());
	}
	
	@Test
	public void ograniczenieWithoutNazwaIsNotValid() {
		//given
		ograniczenie.setNazwa("");
		ograniczenie.setJezyk("jezyk");
		ograniczenie.setSlowaKluczowe(new ArrayList<SlowoKluczowe>() {{
			   add(new SlowoKluczowe());
			   add(new SlowoKluczowe());
			}});
		ograniczenie.setNazwa("");
		//when then
		assertFalse(ograniczenie.isValid());
	}
	
	@Test
	public void ograniczenieWithoutJezykIsNotValid() {
		//given
		ograniczenie.setNazwa("nazwa");
		ograniczenie.setJezyk(null);
		ograniczenie.setSlowaKluczowe(new ArrayList<SlowoKluczowe>() {{
			   add(new SlowoKluczowe());
			   add(new SlowoKluczowe());
			}});
		ograniczenie.setJezyk(null);
		//when then
		assertFalse(ograniczenie.isValid());
	}
	
	@Test
	public void ograniczenieWithoutSlowaKluczoweIsNotValid() {
		//given
		ograniczenie.setNazwa("nazwa");
		ograniczenie.setJezyk("jezyk");
		ograniczenie.setSlowaKluczowe(new ArrayList<SlowoKluczowe>());

		//when then
		assertFalse(ograniczenie.isValid());
	}
}
