function removeTrailingProjection(string) {
    return string.replace(/\{\?projection\}/, "");
}

function getIdFromHref(href) {
    return removeTrailingProjection(href).split("/").pop();
}

function removeAllOptionsFromSelect(selectElement) {
	let i, L = selectElement.options.length - 1;
	for (i = L; i >= 0; i--) {
		selectElement.remove(i);
	}
}

function genericGetAll(resource, projection, callback, additionalParams) {
	let params = "?projection=" + projection;
	if (additionalParams) {
		params += additionalParams;
	}
    genericGet("/api/" + resource + params, callback);
}

async function genericGetAllAsync(resource, projection) {
    var url = "/api/" + resource;
    if (projection != null) {
        url += "?projection=" + projection;
    }
    const response = await fetch(url);
    return response.json();
}

function genericGet(url, callback) {
    fetch(url)
        .then((response) => response.json())
        .then((result) => callback(result));
}

function genericGetById(id, resource, callback, errorCallback, projection) {
    var url = "/api/" + resource + "/" + id;
    if (projection != null) {
        url += "?projection=" + projection;
    }

    fetch(url)
        .then(async function(response) {
            if (!response.ok) {
                console.log("response nok, called GET " + url + ". Status: " + response.status);
                throw new Error(response.status);
            } else {
                console.log("response ok, called GET " + url);
                var result = await response.json();
                console.log("Calling callback...");
                callback(result);
            }
        })
        .catch((error) => {
            console.log("Calling error callback...");
            console.log("error: " + error.message);
            errorCallback(error.message);
        });
}

function genericCreate(dataToSend, resource, callback) {
    fetch("/api/" + resource, {
        method: 'POST'
        , headers: {
            'Content-Type': 'application/json; charset=utf-8'
        }
        , body: JSON.stringify(dataToSend)})
        .then((response) => response.json())
        .then((result) => callback(result));
}

// We need to use PATCH sometimes due to the "problem" when updating referenced types mentioned in
// https://stackoverflow.com/questions/45620195/spring-data-rest-put-request-does-not-work-properly-since-v-2-5-7
function genericUpdate(dataToSend, resource, id, putOrPath, callback) {
    fetch("/api/" + resource + "/" + id, {
        method: putOrPath
        , headers: {
            'Content-Type': 'application/json; charset=utf-8'
        }
        , body: JSON.stringify(dataToSend)})
        .then((response) => response.json())
        .then((result) => callback(result));
}
