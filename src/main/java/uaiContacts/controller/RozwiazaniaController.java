package uaiContacts.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uaiContacts.repository.UserRepository;
import uaiContacts.service.RozwiazanieService;
import uaiContacts.vo.RozwiazanieListVO;

@Controller
@RequestMapping(value = "/protected/rozwiazania")
public class RozwiazaniaController {

/*    private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";*/

    @Autowired
    private RozwiazanieService rozwiazanieService;
    
    @Autowired
    private UserRepository userRepository;

    @Value("5")
    private int maxResults;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome() {
        return new ModelAndView("rozwiazaniaList");
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listAll(@RequestParam int page, Locale locale) {
    	//Zwróæ rozwi¹zania, których autorem jest zalogowany uzytkownik
        RozwiazanieListVO rozwiazanieListVO = null;
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof User) {
        	rozwiazanieListVO = rozwiazanieService.findByAutorLike(page, maxResults, userRepository.findByEmail(((User)user).getUsername()).getId());
        }
        return new ResponseEntity<RozwiazanieListVO>(rozwiazanieListVO, HttpStatus.OK);
    }

}