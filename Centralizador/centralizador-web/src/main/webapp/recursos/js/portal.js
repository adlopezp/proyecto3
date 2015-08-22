function hideOverlay() {
    el = document.getElementById("ajaxOverlay");
    el.style.visibility = "hidden";
}
function showOverlay() {
    el = document.getElementById("ajaxOverlay");
    el.style.visibility = "visible";
}
function refresh() {
    location.reload();
}
function abrirPagina(codigo) {
    window.location = codigo + '.jsf';
}