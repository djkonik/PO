package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import model.entity.Ograniczenie;
import model.entity.SlowoKluczowe;
import model.entity.User;
import model.entity.Zadanie;
import model.repository.OgraniczenieRepository;
import model.repository.UserRepository;
import model.repository.ZadanieRepository;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uaiContacts.vo.ZadanieListVO;


@Controller
@RequestMapping(value = "/protected/zadania")
public class ZadaniaController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OgraniczenieRepository ograniczenieRepository;

    @Autowired
    private ZadanieRepository zadanieRepository;

    @Value("5")
    private int maxResults;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome() {
        return new ModelAndView("zadaniaList");
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listAll(@RequestParam int page, Locale locale) {
        ZadanieListVO zadanieList = null;
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        zadanieList = findByAutorLike(page, maxResults, userRepository.findByEmail(user));

        return new ResponseEntity<ZadanieListVO>(zadanieList, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> create(@ModelAttribute("ograniczenie") Ograniczenie ograniczenie,
    								@RequestParam(required = true, defaultValue = "") String slowa,
    								@RequestParam(required = true) int idZadania,
                                    Locale locale) {
		ograniczenie.setZadanie(findByIdLike(idZadania));
		List<SlowoKluczowe> listSlowaKluczowe = new LinkedList<SlowoKluczowe>();
		String[] slowaKluczowe = slowa.split(";");
		for (String s : slowaKluczowe) {
			if (!s.isEmpty()) {
    			SlowoKluczowe slowoKluczowe = new SlowoKluczowe();
    			slowoKluczowe.setOgraniczenie(ograniczenie);
    			slowoKluczowe.setSlowo(s);
    			listSlowaKluczowe.add(slowoKluczowe);
			}
		}
		ograniczenie.setSlowaKluczowe(listSlowaKluczowe);
		if (ograniczenie.isValid()) {
			ograniczenieRepository.save(ograniczenie);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
    }

    @Transactional(readOnly = true)
    public ZadanieListVO findAll(int page, int maxResults) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByNumerASC());
        Page<Zadanie> result = zadanieRepository.findAll(pageRequest);
        return buildResult(result);
    }
    
    @Transactional(readOnly = true)
    public ZadanieListVO findByAutorLike(int page, int maxResults, User autor) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByNumerASC());
        Page<Zadanie> result = zadanieRepository.findByAutorLike(pageRequest, autor);
        return buildResult(result);
    }
    
    @Transactional(readOnly = true)
    public Zadanie findByIdLike(int id) {
        return zadanieRepository.findByIdLike(id);
    }

    private Sort sortByNumerASC() {
        return new Sort(Sort.Direction.ASC, "numer");
    }

    private ZadanieListVO buildResult(Page<Zadanie> result) {
        return new ZadanieListVO(result.getTotalPages(), result.getTotalElements(), result.getContent());
    }
}