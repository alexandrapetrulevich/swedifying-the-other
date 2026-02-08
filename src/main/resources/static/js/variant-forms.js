
function getAllVariantForms(callback, page, pageSize) {
    genericGetAll(
        "variantForms"
        , "variantFormView"
        , function(data) {
            callback(data);
        }
		, typeof page !== "undefined" ? "&page=" + page + "&size=" + pageSize : null);
}

async function getAllVariantFormsAsync() {
    let returnedData = await genericGetAllAsync("variantForms", "variantFormView");
	return returnedData._embedded.variantForms;
}

async function getAllVariantFormsAsyncNoPaging() {
    let returnedData = await genericGetAllAsync("variantForms?size=1000");
	return returnedData._embedded.variantForms;
}

function getVariantFormById(id, callback, errorCallback) {
    genericGetById(id, "variantForms", callback, errorCallback, "variantFormView");
}

function createOrEditVariantForm(callback, variantFormId, addedAdaptationTypes) {
    const variantFormValue = document.getElementById("variantForm").value;
	const isAdaptedToSwedishValue = document.getElementById("availableIsAdaptedToSwedishTypes").value;
	const normalizedFormValue = document.getElementById("availableNormalizedForms").value;
	var variantFormData = {
        variantFormId: null
        , variantForm: variantFormValue
		, isAdaptedToSwedish: isAdaptedToSwedishValue
		, adaptationTypes: Array.from(addedAdaptationTypes)
		, normalizedForm: normalizedFormValue
    };
    if (variantFormId === "") {
        genericCreate(variantFormData, "variantForms", callback);
    } else {
		variantFormData.variantFormId = parseInt(variantFormId);
        genericUpdate(variantFormData, "variantForms", variantFormId, "PATCH", callback);
    }
}

function doFilterVariantForms(newHeaderText, callback) {
    var filterByVariantForm = document.getElementById("filterByVariantForm").value;
    var queryParams = "?projection=variantFormView"
        + "&variantFormFilter=" + encodeURIComponent(filterByVariantForm);
    if (filterByVariantForm != "") {
        newHeaderText = newHeaderText + " variant form name " + filterByVariantForm;
    } else {
        newHeaderText = "All variant forms";
    }
	
	genericGet(
		"/api/variantForms/search/findByVariantFormContains" + queryParams
        , function(result) {
            callback(newHeaderText, result);
        });
}
