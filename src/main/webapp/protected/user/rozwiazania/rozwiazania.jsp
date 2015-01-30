<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row-fluid" ng-controller="rozwiazaniaController">
    <h2>
        <p class="text-center">
            <spring:message code='rozwiazania.header'/>
            <a href="#"
               ng-click="getRozwiazanieList();"
               id="contactsHeaderButton"
               role="button"
               ng-class="{'': displaySearchButton == true, 'none': displaySearchButton == false}"
               title="<spring:message code="search"/>&nbsp;<spring:message code="rozwiazanie"/>"
               class="btn btn-inverse" data-toggle="modal">
                <i class="icon-refresh"></i>
            </a>
        </p>
    </h2>
    <h4>
        <div ng-class="{'': state == 'list', 'none': state != 'list'}">
            <p class="text-center">
                <spring:message code="message.total.records.found"/>:&nbsp;{{page.totalCount}}
            </p>
        </div>
    </h4>

    <div>
        <div id="loadingModal" class="modal hide fade in centering"
             role="dialog"
             aria-labelledby="deleteContactsModalLabel" aria-hidden="true">
            <div id="divLoadingIcon" class="text-center">
                <div class="icon-align-center loading"></div>
            </div>
        </div>

        <div ng-class="{'alert badge-inverse': displayMessageToUser == true, 'none': displayMessageToUser == false}">
            <h4 class="displayInLine">
                <p class="messageToUser displayInLine"><i class="icon-info-sign"></i>&nbsp;{{page.actionMessage}}</p>
            </h4>
        </div>

        <div ng-class="{'alert alert-block alert-error': state == 'error', 'none': state != 'error'}">
            <h4><i class="icon-info-sign"></i> <spring:message code="error.generic.header"/></h4><br/>

            <p><spring:message code="error.generic.text"/></p>
        </div>

        <div ng-class="{'alert alert-info': state == 'noresult', 'none': state != 'noresult'}">
            <h4><i class="icon-info-sign"></i> <spring:message code="rozwiazania.emptyData"/></h4><br/>

            <p><spring:message code="rozwiazania.emptyData.text"/></p>
        </div>

        <div id="gridContainer" ng-class="{'': state == 'list', 'none': state != 'list'}">
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th scope="col"><spring:message code="rozwiazania.nr_zadania"/></th>
                    <th scope="col"><spring:message code="rozwiazania.czas_przeslania"/></th>
                    <th scope="col"><spring:message code="rozwiazania.status"/></th>
                    <th scope="col"><spring:message code="rozwiazania.ocena"/></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="rozwiazanie in page.source">
                    <td class="tdContactsCentered">{{rozwiazanie.zadanie.numer}}</td>
                    <td class="tdContactsCentered">{{rozwiazanie.czasPrzeslania}}</td>
                    <td class="tdContactsCentered">{{rozwiazanie.czySprawdzone}}</td>
                    <td class="tdContactsCentered">{{rozwiazanie.czyZatwierdzone}}</td>
                    <td class="width15">
                        <div class="text-center">
                            <input type="hidden" value="{{rozwiazanie.id}}"/>
                            <a href="#detailsRozwiazaniaModal"
                               ng-click="selectedRozwiazanie(rozwiazanie);"
                               role="button"
                               title="<spring:message code="details"/>&nbsp;rozwiązania"
                               class="btn btn-inverse" data-toggle="modal">
                                <i class="icon-search"></i>
                            </a>
                            <a href="#"
                               ng-click="todo();"
                               role="button"
                               title="Przejdź do zadania"
                               class="btn btn-inverse" data-toggle="modal">
                                <i class="icon-file"></i>
                            </a>
                            <a href="#"
                               ng-click="todo();"
                               role="button"
                               title="Zadaj pytanie"
                               class="btn btn-inverse" data-toggle="modal">
                                <i class="icon-comment"></i>
                            </a>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="text-center">
                <button href="#" class="btn btn-inverse"
                        ng-class="{'btn-inverse': page.currentPage != 0, 'disabled': page.currentPage == 0}"
                        ng-disabled="page.currentPage == 0" ng-click="changePage(0)"
                        title='<spring:message code="pagination.first"/>'
                        >
                    <spring:message code="pagination.first"/>
                </button>
                <button href="#"
                        class="btn btn-inverse"
                        ng-class="{'btn-inverse': page.currentPage != 0, 'disabled': page.currentPage == 0}"
                        ng-disabled="page.currentPage == 0" class="btn btn-inverse"
                        ng-click="changePage(page.currentPage - 1)"
                        title='<spring:message code="pagination.back"/>'
                        >&lt;</button>
                <span>{{page.currentPage + 1}} <spring:message code="pagination.of"/> {{page.pagesCount}}</span>
                <button href="#"
                        class="btn btn-inverse"
                        ng-class="{'btn-inverse': page.pagesCount - 1 != page.currentPage, 'disabled': page.pagesCount - 1 == page.currentPage}"
                        ng-click="changePage(page.currentPage + 1)"
                        ng-disabled="page.pagesCount - 1 == page.currentPage"
                        title='<spring:message code="pagination.next"/>'
                        >&gt;</button>
                <button href="#"
                        class="btn btn-inverse"
                        ng-class="{'btn-inverse': page.pagesCount - 1 != page.currentPage, 'disabled': page.pagesCount - 1 == page.currentPage}"
                        ng-disabled="page.pagesCount - 1 == page.currentPage"
                        ng-click="changePage(page.pagesCount - 1)"
                        title='<spring:message code="pagination.last"/>'
                        >
                    <spring:message code="pagination.last"/>
                </button>
            </div>
        </div>
        <div ng-class="{'text-center': displayCreateContactButton == true, 'none': displayCreateContactButton == false}">
            <br/>
            <a href="#"
               role="button"
               ng-click="todo();"
               title="<spring:message code='create'/>&nbsp;<spring:message code='rozwiazanie'/>"
               class="btn btn-inverse"
               data-toggle="modal">
                <i class="icon-plus"></i>
                &nbsp;&nbsp;<spring:message code="add"/>&nbsp;<spring:message code="rozwiazanie"/>
            </a>
        </div>

        <jsp:include page="dialogs/rozwiazaniaDialogs.jsp"/>

    </div>
</div>

<script src="<c:url value="/resources/js/pages/rozwiazania.js" />"></script>