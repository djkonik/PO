<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<style>
	body{padding:0;}
	.row {margin-left:0px; width: 99%;}
	.big-input {width:70%;}
	.fill {width: 100%;}
	.code-area {width: 100%; height:100%}
	.code-area-container {width: 100%; height:150px;}
	input[readonly] {cursor: pointer;}
	
	.table-small {margin-top: 20px;}
	.table-small a.btn{padding: 2px 6px;}
	.table-small th, .table-small td {padding: 2px;}
	#gridContainer button {padding: 3px 8px;}
	.create-button {float: right; margin-top: -30px;}
	.create-button-noresult {text-align:center; margin-top: 30px;}
	label {font-style: bold;}
	.result-list {border: thin solid black; overflow-y: scroll; height:220px;}
	.small-details-dialog{height:450px;};

</style>

<div id="editZadaniaModal"
     class="modal hide fade in centering detailsDialog"
     role="dialog"
     aria-labelledby="updateContactsModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <div class="pull-right">
            <button class="btn btn-inverse"
                  data-dismiss="modal"
                  ng-click="exit('#editZadaniaModal');"
                  aria-hidden="true">
            <spring:message code="cancel"/></button>
     	</div>
        <h3 id="updateContactsModalLabel" class="displayInLine">
            <spring:message code="edit"/>&nbsp;<spring:message code="zadanie"/>
        </h3>
    </div>
    <div class="modal-body">
            <input type="hidden"
                   required
                   ng-model="zadanie.id"
                   name="id"
                   value="{{zadanie.id}}"/>
                   
            <div class="row">
                <div class="span4">
                    <div class="input-append">
                        <label><spring:message code="zadania.nr_zadania"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="text"
                               required
                               readonly
                               ng-model="zadanie.numer"
                               name="nrZadania" />
                    </div>
                </div>
                <div class="span4">
                    <div class="input-append">
                        <label><spring:message code="zadania.max_czas"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="text"
                               required
                               ng-model="zadanie.maxCzasWykonania"
                               name="maxCzasWykonania" />
                    </div>
                </div>
                <div class="span4" style="text-align:center;">
                    <div class="input-append">
                        <label><spring:message code="zadania.caseSensitive"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="checkbox"
                               required
                               ng-model="zadanie.caseSensitive"
                               name="caseSensitive" />
                    </div>
                </div>
            </div>
            <div class="row">
               <div class="span12">
	               <div class="input-append">
	                   <label><spring:message code="zadania.tytul"/>:</label>
	               </div>
	               <div class="input-append big-input">
	                   <input class="fill"
	                   		  type="text"
	                          required
	                          ng-model="zadanie.tytul"
	                          name="tytul" />
	               </div>
               </div>
            </div>
             <div class="row">
	             <div class="span12">
	                 <div class="input-append code-area-container">
						<textarea class="form-control code-area" 
								required
								ng-model="zadanie.tresc"
			                    name="tresc" >
						</textarea>
	                 </div>
                 </div>
             </div>     
		<div class="row">
			<div class="span6">
		        <div class = "create-button-noresult">
		            <a href="#"
		               role="button"
		               ng-click="todo();"
		               title="<spring:message code='add'/>&nbsp;<spring:message code='test'/>"
		               class="btn btn-inverse"
		               data-toggle="modal">
		                <i class="icon-plus"></i>
		                &nbsp;&nbsp;<spring:message code="add"/>&nbsp;<spring:message code="test"/>
		            </a>
		        </div>
			</div>
			<div class="span6">
		        <div id="gridContainer" ng-class="{'': ograniczenia.state == 'list', 'none': ograniczenia.state != 'list'}">
		            <table class="table table-bordered table-striped table-small">
		                <thead>
		                <tr>
		                    <th scope="col"><spring:message code="ograniczenia.nazwa"/></th>
		                    <th scope="col"><spring:message code="ograniczenia.jezyk"/></th>
		                    <th scope="col"></th>
		                </tr>
		                </thead>
		                <tbody>
		                <tr ng-repeat="ograniczenie in ograniczenia.page.source">
		                    <td class="tdContactsCentered">{{ograniczenie.nazwa}}</td>
		                    <td class="tdContactsCentered">{{ograniczenie.jezyk}}</td>
		                    <td class="width15">
		                        <div class="text-center">
		                            <input type="hidden" value="{{contact.id}}"/>
		                            <a href="#"
		                               ng-click="todo();"
		                               role="button"
		                               title="<spring:message code='edit'/>&nbsp;<spring:message code='ograniczenie'/>"
		                               class="btn btn-inverse" data-toggle="modal">
		                                <i class="icon-edit"></i>
		                            </a>
		                            <a href="#"
		                               ng-click="todo();"
		                               role="button"
		                               title="Usuń zadanie"
		                               class="btn btn-inverse" data-toggle="modal">
		                                <i class="icon-minus"></i>
		                            </a>
		                        </div>
		                    </td>
		                </tr>
		                </tbody>
		            </table>
		
		            <div class="text-center">
		                <button href="#" class="btn btn-inverse"
		                        ng-class="{'btn-inverse': ograniczenia.page.currentPage != 0, 'disabled': ograniczenia.page.currentPage == 0}"
		                        ng-disabled="ograniczenia.page.currentPage == 0" ng-click="ograniczenia.changePage(0)"
		                        title='<spring:message code="pagination.first"/>'
		                        >
		                    <spring:message code="pagination.first"/>
		                </button>
		                <button href="#"
		                        class="btn btn-inverse"
		                        ng-class="{'btn-inverse': ograniczenia.page.currentPage != 0, 'disabled': ograniczenia.page.currentPage == 0}"
		                        ng-disabled="ograniczenia.page.currentPage == 0" class="btn btn-inverse"
		                        ng-click="ograniczenia.changePage(ograniczenia.page.currentPage - 1)"
		                        title='<spring:message code="pagination.back"/>'
		                        >&lt;</button>
		                <span>{{ograniczenia.page.currentPage + 1}} <spring:message code="pagination.of"/> {{ograniczenia.page.pagesCount}}</span>
		                <button href="#"
		                        class="btn btn-inverse"
		                        ng-class="{'btn-inverse': ograniczenia.page.pagesCount - 1 != ograniczenia.page.currentPage, 'disabled': ograniczenia.page.pagesCount - 1 == ograniczenia.page.currentPage}"
		                        ng-click="ograniczenia.changePage(ograniczenia.page.currentPage + 1)"
		                        ng-disabled="ograniczenia.page.pagesCount - 1 == ograniczenia.page.currentPage"
		                        title='<spring:message code="pagination.next"/>'
		                        >&gt;</button>
		                <button href="#"
		                        class="btn btn-inverse"
		                        ng-class="{'btn-inverse': ograniczenia.page.pagesCount - 1 != ograniczenia.page.currentPage, 'disabled': ograniczenia.page.pagesCount - 1 == ograniczenia.page.currentPage}"
		                        ng-disabled="ograniczenia.page.pagesCount - 1 == ograniczenia.page.currentPage"
		                        ng-click="ograniczenia.changePage(ograniczenia.page.pagesCount - 1)"
		                        title='<spring:message code="pagination.last"/>'
		                        >
		                    <spring:message code="pagination.last"/>
		                </button>
		            </div>
		        </div>
		        <div ng-class="{'create-button-noresult': ograniczenia.state == 'noresult', '': ograniczenia.state != 'noresult'}">
		            <a href="#addOgraniczenie"
		               role="button"
		               ng-click="openNoweOgraniczenie();"
		               ng-class="{'': ograniczenia.state == 'noresult', 'create-button': ograniczenia.state != 'noresult'}"
		               title="<spring:message code='add'/>&nbsp;<spring:message code='ograniczenie'/>"
		               class="btn btn-inverse"
		               data-toggle="modal">
		                <i class="icon-plus"></i>
		                &nbsp;&nbsp;<spring:message code="add"/>&nbsp;<spring:message code="ograniczenie"/>
		            </a>
		        </div>
	        </div>
		</div>      
             
    </div>
</div>

<div id="addOgraniczenie"
     class="modal hide fade in centering small-details-dialog"
     role="dialog"
     aria-labelledby="updateContactsModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <div class="pull-right">
            <a href="#editZadaniaModal"
               role="button"
               ng-click="saveOgraniczenie();"
               data-dismiss="modal"
                aria-hidden="true"
               title="<spring:message code='cancel'/>"
               class="btn btn-inverse"
               data-toggle="modal">
               <spring:message code="save"/>&nbsp;
            </a>
            <a href="#editZadaniaModal"
               role="button"
               ng-click="exit('#addOgraniczenie');"
               data-dismiss="modal"
               aria-hidden="true"
               title="<spring:message code='cancel'/>"
               class="btn btn-inverse"
               data-toggle="modal">
               <spring:message code="cancel"/>&nbsp;
            </a>
            
     	</div>
        <h3 id="updateContactsModalLabel" class="displayInLine">
            <spring:message code="add"/>&nbsp;<spring:message code="ograniczenie"/>
        </h3>
    </div>
   <div class="modal-body">
        <form name="noweOgraniczenieForm" novalidate >
            <div class="row">
               <div class="span12">
	               <div class="input-append">
	                   <label><spring:message code="ograniczenia.nazwa"/>:</label>
	               </div>
	               <div class="input-append big-input">
	                   <input class="fill"
	                   		  type="text"
	                          required
	                          ng-model="ograniczenia.nowe.nazwa"
	                          name="nazwa" />
	               </div>
               </div>
            </div>
            <div class="row">
               <div class="input-append">
                   <label><spring:message code="ograniczenia.jezyk"/>:</label>
               </div>
               <div class="input-append big-input">
               	   <select class="fill" required ng-model="ograniczenia.nowe.jezyk" name="jezyk" >
               	   		<option value="Java">Java</option>
               	   		<option value="C#">C#</option>
               	   		<option value="C++">C++</option>
               	   </select>
               </div>
            </div>
            <div class="row">
               <div class="input-append">
                   <label><spring:message code="ograniczenia.slowa_kluczowe"/>:</label>
               </div>    
               <div class="input-append">
                   <input class="fill"
                   		  type="text"
                          ng-model="ograniczenia.nowe.noweSlowoKluczowe"
                          name="noweSlowoKluczowe" />
               </div> 
               <div class="input-append">
		            <a href="#"
		               role="button"
		               ng-click="dodajSlowoKluczowe();"
		               title="<spring:message code='add'/>"
		               class="btn btn-inverse"
		               data-toggle="modal">
		                <i class="icon-plus"></i>
		                &nbsp;&nbsp;<spring:message code="add"/>
		            </a>
               </div>    
	        </div>
            <div class="row">
               <div class="span12">
					<div class="panel panel-default result-list">
					  <table class="table">
		                  <tr ng-repeat="slowoKluczowe in ograniczenia.nowe.slowaKluczowe">
		                    <td class="tdContactsCentered">{{slowoKluczowe}}</td>
		                    <td class="width15">
		                        <div class="text-center">
		                            <a href="#"
		                               ng-click="usunSlowoKluczowe(slowoKluczowe);"
		                               role="button"
		                               title="Usuń słowo kluczowe"
		                               class="btn btn-inverse" data-toggle="modal">
		                                <i class="icon-minus"></i>
		                            </a>
		                        </div>
		                    </td>
		                  </tr>
					  </table>
					</div>
               </div>
            </div>
		</form>
    </div>
</div>