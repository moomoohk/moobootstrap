function replaceAll(find, replace, str) {
	return str.replace(new RegExp(escapeRegExp(find), 'g', replace));
}

function escapeRegExp(str) {
	return str.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g, "\\$&");
}

/*$(document).ready(function() {
	$("code").text(replaceAll("<", "&lt;", $("code").text()));
	$("code").text(replaceAll(">", "&gt;", $("code").text()));
	$("code").text(replaceAll("\"", "&quot;", $("code").text()));
});*/