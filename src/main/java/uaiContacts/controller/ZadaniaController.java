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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uaiContacts.service.UserService;
import uaiContacts.service.ZadanieService;
import uaiContacts.vo.RozwiazanieListVO;
import uaiContacts.vo.ZadanieListVO;

@Controller
@RequestMapping(value = "/protected/zadania")
public class ZadaniaController {

/*    private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";*/

    @Autowired
    private ZadanieService zadanieService;
    
    @Autowired
    private UserService userService;

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

/*
    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> search(@PathVariable("name") String name,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
        return search(name, page, locale, null);
    }

    private ResponseEntity<?> search(String name, int page, Locale locale, String actionMessageKey) {
        RozwiazanieListVO rozwiazanieListVO = rozwiazanieService.findByJezykLike(page, maxResults, name);

        if (!StringUtils.isEmpty(actionMessageKey)) {
            addActionMessageToVO(rozwiazanieListVO, locale, actionMessageKey, null);
        }

        Object[] args = {name};

        addSearchMessageToVO(rozwiazanieListVO, locale, "message.search.for.active", args);

        return new ResponseEntity<RozwiazanieListVO>(rozwiazanieListVO, HttpStatus.OK);
    }*/

    private ZadanieListVO listAll(int page) {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof User) {
        	return zadanieService.findByAutorLike(page, maxResults, userService.findByEmail(((User)user).getUsername()).getId());
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

        addActionMessageToVO(zadanieListVO, locale, messageKey, null);

        return returnListToUser(zadanieListVO);
    }

    private ZadanieListVO addActionMessageToVO(ZadanieListVO zadanieListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return zadanieListVO;
        }

        zadanieListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return zadanieListVO;
    }
/*
    private RozwiazanieListVO addSearchMessageToVO(RozwiazanieListVO rozwiazanieListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return rozwiazanieListVO;
        }

        rozwiazanieListVO.setSearchMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return rozwiazanieListVO;
    }

    private boolean isSearchActivated(String searchFor) {
        return !StringUtils.isEmpty(searchFor);
    }*/
}