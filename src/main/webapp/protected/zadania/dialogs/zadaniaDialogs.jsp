<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<style>
	.row {margin-left:0px;}
	.big-input {width:550px;}
	.code-area {width: 99%; height:100%}
	.code-area-container {width: 100%; height:400px;}
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
            <spring:message code="cancel"/></button>
     	</div>
        <h3 id="updateContactsModalLabel" class="displayInLine">
            <spring:message code="details"/>&nbsp;<spring:message code="rozwiazania.header"/>
        </h3>
    </div>
    <div class="modal-body">
            <input type="hidden"
                   required
                   ng-model="contact.id"
                   name="id"
                   value="{{contact.id}}"/>
            <div class="row">
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
             </div>
    </div>
</div>