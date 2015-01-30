package controller;

import java.util.Locale;

import model.entity.Rozwiazanie;
import model.entity.User;
import model.repository.RozwiazanieRepository;
import model.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import view.dto.RozwiazanieDTO;

@Controller
@RequestMapping(value = "/protected/rozwiazania")
public class RozwiazaniaController {

/*    private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";*/

    @Autowired
    private RozwiazanieRepository rozwiazanieRepository;
    
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
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        RozwiazanieDTO rozwiazanieListVO = findByAutorLike(page, maxResults, userRepository.findByEmail(user));
        return new ResponseEntity<RozwiazanieDTO>(rozwiazanieListVO, HttpStatus.OK);
    }
    
    @Transactional(readOnly = true)
    private RozwiazanieDTO findAll(int page, int maxResults) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByCzasPrzeslaniaDESC());
        Page<Rozwiazanie> result =  rozwiazanieRepository.findAll(pageRequest);
        return buildResult(result);
    }
    
    @Transactional(readOnly = true)
    private RozwiazanieDTO findByAutorLike(int page, int maxResults, User autor) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByCzasPrzeslaniaDESC());
        Page<Rozwiazanie> result =  rozwiazanieRepository.findByAutorLike(pageRequest, autor);
        return buildResult(result);
    }

    private Sort sortByCzasPrzeslaniaDESC() {
        return new Sort(Sort.Direction.DESC, "czasPrzeslania");
    }

    private RozwiazanieDTO buildResult(Page<Rozwiazanie> result) {
        return new RozwiazanieDTO(result.getTotalPages(), result.getTotalElements(), result.getContent());
    }

}