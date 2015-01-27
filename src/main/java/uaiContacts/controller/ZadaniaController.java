package uaiContacts.controller;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    private ZadanieService zadanieService;
    
    @Autowired
    private OgraniczenieService ograniczenieService;

    @Autowired
    private MessageSource messageSource;

    @Value("5")
    private int maxResults;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome() {
        return new ModelAndView("zadaniaList");
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listAll(@RequestParam int page, Locale locale) {
        return createListAllResponse(page, locale);
    }


    @RequestMapping(value = "/{numer}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listOgraniczenia(@PathVariable("numer") int numer,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
        return listOgraniczenia(numer, page, locale, null);
    }

    private ResponseEntity<?> listOgraniczenia(int numer, int page, Locale locale, String actionMessageKey) {
    	OgraniczenieListVO ograniczenieListVO = ograniczenieService.findByZadanieLike(page, maxResults, numer);

        if (!StringUtils.isEmpty(actionMessageKey)) {
        	addActionMessageToOgraniczenieVO(ograniczenieListVO, locale, actionMessageKey, null);
        }

        Object[] args = {numer};

        addActionMessageToOgraniczenieVO(ograniczenieListVO, locale, "message.search.for.active", args);

        return new ResponseEntity<OgraniczenieListVO>(ograniczenieListVO, HttpStatus.OK);
    }

    private ZadanieListVO listAll(int page) {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof User) {
        	return zadanieService.findByAutorLike(page, maxResults, userRepository.findByEmail(((User)user).getUsername()));
        } else
        	return null;
    }

    private ResponseEntity<ZadanieListVO> returnListToUser(ZadanieListVO zadanieList) {
        return new ResponseEntity<ZadanieListVO>(zadanieList, HttpStatus.OK);
    }

    private ResponseEntity<?> createListAllResponse(int page, Locale locale) {
        return createListAllResponse(page, locale, null);
    }

    private ResponseEntity<?> createListAllResponse(int page, Locale locale, String messageKey) {
        ZadanieListVO zadanieListVO = listAll(page);

        addActionMessageToZadanieVO(zadanieListVO, locale, messageKey, null);

        return returnListToUser(zadanieListVO);
    }

    private ZadanieListVO addActionMessageToZadanieVO(ZadanieListVO zadanieListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return zadanieListVO;
        }

        zadanieListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return zadanieListVO;
    }
    
    private OgraniczenieListVO addActionMessageToOgraniczenieVO(OgraniczenieListVO ograniczenieListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return ograniczenieListVO;
        }

        ograniczenieListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return ograniczenieListVO;
    }

}