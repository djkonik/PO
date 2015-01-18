package uaiContacts.controller;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uaiContacts.service.RozwiazanieService;
import uaiContacts.vo.RozwiazanieListVO;

@Controller
@RequestMapping(value = "/protected/rozwiazania")
public class RozwiazaniaController {

    private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";

    @Autowired
    private RozwiazanieService rozwiazanieService;

    @Autowired
    private MessageSource messageSource;

    @Value("5")
    private int maxResults;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome() {
        return new ModelAndView("rozwiazaniaList");
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listAll(@RequestParam int page, Locale locale) {
        return createListAllResponse(page, locale);
    }


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
    }

    private RozwiazanieListVO listAll(int page) {
        return rozwiazanieService.findAll(page, maxResults);
    }

    private ResponseEntity<RozwiazanieListVO> returnListToUser(RozwiazanieListVO contactList) {
        return new ResponseEntity<RozwiazanieListVO>(contactList, HttpStatus.OK);
    }

    private ResponseEntity<?> createListAllResponse(int page, Locale locale) {
        return createListAllResponse(page, locale, null);
    }

    private ResponseEntity<?> createListAllResponse(int page, Locale locale, String messageKey) {
        RozwiazanieListVO rozwiazanieListVO = listAll(page);

        addActionMessageToVO(rozwiazanieListVO, locale, messageKey, null);

        return returnListToUser(rozwiazanieListVO);
    }

    private RozwiazanieListVO addActionMessageToVO(RozwiazanieListVO rozwiazanieListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return rozwiazanieListVO;
        }

        rozwiazanieListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return rozwiazanieListVO;
    }

    private RozwiazanieListVO addSearchMessageToVO(RozwiazanieListVO rozwiazanieListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return rozwiazanieListVO;
        }

        rozwiazanieListVO.setSearchMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return rozwiazanieListVO;
    }

    private boolean isSearchActivated(String searchFor) {
        return !StringUtils.isEmpty(searchFor);
    }
}