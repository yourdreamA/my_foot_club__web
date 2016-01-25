if (!window["busystatusdemo"]) {
	var busystatusdemo = {};
}
busystatusdemo.onStatusChange = function onStatusChange(data) {
	var status = data.status;
	var componentID = busystatusdemo[data.source.id];
	if (!componentID) { // if there's no request to listen for this one
						// component, then leave
		return;
	}
	var element = document.getElementById(componentID);
	if (status === "begin") { // turn on busy indicator
		element.style.display = "inline";
	} else { // turn off busy indicator, on either "complete" or "success"
		element.style.display = "none";
	}
};

jsf.ajax.addOnEvent(busystatusdemo.onStatusChange);

busystatusdemo.init = function init(componentID, forValue) {
	busystatusdemo[forValue] = componentID;
};