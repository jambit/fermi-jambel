<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Fermi problem</title>
	</head>
	<body>
		<div id="create-organizationalEntity" class="content" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${organizationalEntityInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${organizationalEntityInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="answer" >
				<fieldset class="form">
					<div class="fieldcontain">
						<label for="questionChoice">Fragen:</label>
						<g:radioGroup name="questionChoice" labels="${questions}" values="${new IntRange(0, questions.size() - 1)}" >
						<p>${it.radio}<g:message code="${it.label}" /></p>
						</g:radioGroup>
					</div>
					<div class="fieldcontain ${hasErrors(bean: organizationalEntityInstance, field: 'name', 'error')} ">
						<label for="name">Antwort</label>
						<g:field type="number" name="answer"/>
					</div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton class="save" name="submit" value="Beantworten" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
