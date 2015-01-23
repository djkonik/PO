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
            <spring:message code="save"/></button>
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
            
                   
                   
                   
           <%--  <div class="row">
                <div class="span4">
                    <div class="input-append">
                        <label><spring:message code="rozwiazania.nr_zadania"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="text"
                               required
                               readonly
                               ng-model="rozwiazanie.nrZadania"
                               name="nrZadania" />
                    </div>
                </div>
                <div class="span4">
                    <div class="input-append">
                        <label><spring:message code="rozwiazania.czas_przeslania"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="text"
                               required
                               readonly
                               ng-model="rozwiazanie.czasPrzeslania"
                               name="czasPrzeslania" />
                    </div>
                </div>
                <div class="span4">
                    <div class="input-append">
                        <label><spring:message code="rozwiazania.status"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="text"
                               required
                               readonly
                               ng-model="rozwiazanie.czySprawdzone"
                               name="status" />
                    </div>
                </div>
             </div>
             <div class="row">
                <div class="span4">
                    <div class="input-append">
                        <label><spring:message code="rozwiazania.ocena"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="text"
                               required
                               readonly
                               ng-model="rozwiazanie.czyZatwierdzone"
                               name="ocena" />
                    </div>
                </div>
                <div class="span8">
                    <div class="input-append">
                        <label><spring:message code="rozwiazania.przyczyna"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="text"
                               required
                               readonly
                               ng-model="rozwiazanie.przyczyna"
                               name="przyczyna"
                               class = "big-input" />
                    </div>
                </div>
             </div>
             <div class="row">
                 <div class="input-append code-area-container">
					<textarea class="form-control code-area" 
							readonly
							required
							ng-model="rozwiazanie.kod"
		                    name="kod" >
					</textarea>
                 </div>
             </div> --%>
             
             
             
             
             
    </div>
</div>