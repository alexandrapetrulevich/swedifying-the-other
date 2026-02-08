
function getAllNormalizedForms(callback, page, pageSize) {
    genericGetAll(
        "normalizedForms"
        , "normalizedFormView"
        , function(data) {
            callback(data);
        }
		, typeof page !== "undefined" ? "&page=" + page + "&size=" + pageSize : null);
}

async function getAllNormalizedFormsAsync() {
    return await genericGetAllAsync("normalizedForms", "normalizedFormView");
}

async function getAllNormalizedFormsAsyncNoPaging() {
    return await genericGetAllAsync("normalizedForms?size=1000");
}

function getNormalizedFormById(id, callback, errorCallback) {
    genericGetById(id, "normalizedForms", callback, errorCallback, "normalizedFormView");
}

function getMorphologicalDataValueForTextValue(textValue) {
	if (!textValue) {
		console.log("getMorphologicalDataValueForTextValue returns null, textValue: " + textValue);
		return null;
	}
	return textValue;
}

function getLanguageValueForSelectValue(selectValue, noLanguageSelectValue) {
	if (selectValue === noLanguageSelectValue) {
		console.log("getLanguageValueForSelectValue returns null, selectValue: " + selectValue + ", noLanguageSelectValue: " + noLanguageSelectValue);
		return null;
	}
	return selectValue;
}

function createOrEditNormalizedForm(callback, normalizedFormId, noLanguageSelectValue) {
	let morphologicalDataValue = {
		determinationClauseInPhrase: getMorphologicalDataValueForTextValue(document.getElementById("determinationClauseInPhrase").value)
		, mainClauseInPhrase: getMorphologicalDataValueForTextValue(document.getElementById("mainClauseInPhrase").value)
		, simpleRootMorpheme: getMorphologicalDataValueForTextValue(document.getElementById("simpleRootMorpheme").value)
		, derivationBase: getMorphologicalDataValueForTextValue(document.getElementById("derivationBase").value)
		, derivationMorpheme: getMorphologicalDataValueForTextValue(document.getElementById("derivationMorpheme").value)
		, determinationClauseInComposition: getMorphologicalDataValueForTextValue(document.getElementById("determinationClauseInComposition").value)
		, jointMorphemeInComposition: getMorphologicalDataValueForTextValue(document.getElementById("jointMorphemeInComposition").value)
		, mainClauseInComposition: getMorphologicalDataValueForTextValue(document.getElementById("mainClauseInComposition").value)
	};
	let normalizedFormData = {
        normalizedFormId:null
        , normalizedForm: document.getElementById("normalizedForm").value
		, morphologicalNameType: document.getElementById("availableMorphologicalNameTypes").value
		, morphologicalNameTypeIsShaky: document.getElementById("morphologicalNameTypeIsShaky").checked
		, morphologicalData: morphologicalDataValue
		, comparativeFormInformation: document.getElementById("comparativeFormInformation").value
		, etymology: getLanguageValueForSelectValue(document.getElementById("availableEtymologyLanguages").value)
		, mediatingLanguage: getLanguageValueForSelectValue(document.getElementById("availableMediatingLanguages").value)
    };
    if (normalizedFormId === "") {
        genericCreate(normalizedFormData, "normalizedForms", callback);
    } else {
		normalizedFormData.normalizedFormId = parseInt(normalizedFormId);
        genericUpdate(normalizedFormData, "normalizedForms", normalizedFormId, "PATCH", callback);
    }
}

function doFilterNormalizedForms(newHeaderText, callback) {
    var filterByNormalizedForm = document.getElementById("filterByNormalizedForm").value;
    var queryParams = "?projection=normalizedFormView"
        + "&normalizedFormFilter=" + encodeURIComponent(filterByNormalizedForm);
    if (filterByNormalizedForm != "") {
        newHeaderText = newHeaderText + " normalized form " + filterByNormalizedForm;
    } else {
        newHeaderText = "All normalized forms";
    }
	
	genericGet(
		"/api/normalizedForms/search/findByNormalizedFormContains" + queryParams
        , function(result) {
            callback(newHeaderText, result);
        });
}
