package uaiContacts.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uaiContacts.model.Ograniczenie;
import uaiContacts.model.SlowoKluczowe;
import uaiContacts.repository.OgraniczenieRepository;
import uaiContacts.repository.UserRepository;
import uaiContacts.service.OgraniczenieService;
import uaiContacts.service.ZadanieService;
import uaiContacts.vo.OgraniczenieListVO;
import uaiContacts.vo.ZadanieListVO;


@Controller
@RequestMapping(value = "/protected/zadania")
public class ZadaniaController {

    private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "1";
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OgraniczenieRepository ograniczenieRepository;

    @Autowired
    private ZadanieService zadanieService;
    
    @Autowired
    private OgraniczenieService ograniczenieService;

    @Value("5")
    private int maxResults;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome() {
        return new ModelAndView("zadaniaList");
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listAll(@RequestParam int page, Locale locale) {
        ZadanieListVO zadanieList = null;
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof User) {
        	zadanieList = zadanieService.findByAutorLike(page, maxResults, userRepository.findByEmail(((User)user).getUsername()));
        }
        return new ResponseEntity<ZadanieListVO>(zadanieList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{numer}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listOgraniczenia(@PathVariable("numer") int numer,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
    	OgraniczenieListVO ograniczenieListVO = ograniczenieService.findByZadanieLike(page, maxResults, numer);
        return new ResponseEntity<OgraniczenieListVO>(ograniczenieListVO, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> create(@ModelAttribute("ograniczenie") Ograniczenie ograniczenie,
    								@RequestParam(required = true, defaultValue = "") String slowa,
    								@RequestParam(required = true) int idZadania,
                                    Locale locale) {
    	String[] slowaKluczowe = slowa.split(";");
    	if (ograniczenie.getJezyk().isEmpty() || ograniczenie.getNazwa().isEmpty() || slowaKluczowe.length == 0) {
    		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
    	} else {
    		ograniczenie.setZadanie(zadanieService.findByIdLike(idZadania));
    		List<SlowoKluczowe> listSlowaKluczowe = new LinkedList<SlowoKluczowe>();
    		for (String s : slowaKluczowe) {
    			SlowoKluczowe slowoKluczowe = new SlowoKluczowe();
    			slowoKluczowe.setOgraniczenie(ograniczenie);
    			slowoKluczowe.setSlowo(s);
    			listSlowaKluczowe.add(slowoKluczowe);
    		}
    		ograniczenie.setSlowaKluczowe(listSlowaKluczowe);
    		ograniczenieRepository.save(ograniczenie);
    	}
    	

        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

}