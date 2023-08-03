/*
 * Invoca acción de botón que actualiza el árbol de términos. Hace un executeQuery del iterador padre y sus hijos.
 * @author Francisco Cuevas Pineda 
 * @since 17/mayo/2016
 */

/*
function refreshTerminosTree(clientIdBtnRefresh) {
    //Method to get component using id
    var button = AdfPage.PAGE.findComponentByAbsoluteId(clientIdBtnRefresh); // 'r1:1:btnRefreshTerTree'
    //Method to queue ActionEvent from component
    if(button !== null)
        AdfActionEvent.queue(button, button.getPartialSubmit());
}

function refreshCondicionesTree(clientIdBtnRefresh) {
    //Method to get component using id
    var button = AdfPage.PAGE.findComponentByAbsoluteId(clientIdBtnRefresh); // 'r1:1:btnRefreshConTree'
    //Method to queue ActionEvent from component
    if(button !== null)
        AdfActionEvent.queue(button, button.getPartialSubmit());
}

function refreshComisionesTree(clientIdBtnRefresh) {
    //Method to get component using id
    var button = AdfPage.PAGE.findComponentByAbsoluteId(clientIdBtnRefresh); // 'r1:1:btnRefreshComTree'
    //Method to queue ActionEvent from component
    if(button !== null)
        AdfActionEvent.queue(button, button.getPartialSubmit());
}
*/

function refreshTccTree(clientIdBtnRefresh) {
    //Method to get component using id
    console.log(clientIdBtnRefresh);
    var button = AdfPage.PAGE.findComponentByAbsoluteId(clientIdBtnRefresh); // 'r1:1:btnRefreshTccTree'
    console.log(button);
    //Method to queue ActionEvent from component
    if(button !== null)
        AdfActionEvent.queue(button, button.getPartialSubmit());
}

/*
 * Invoca acción de botón que actualiza el proceso de Enmiendas en la tarea de Ingresar enmienda.
 * @author Francisco Cuevas Pineda 
 * @since 14/junio/2016
 */
function refreshDatosEnmienda(clientIdBtnRefreshEnmienda) {
    var button = AdfPage.PAGE.findComponentByAbsoluteId(clientIdBtnRefreshEnmienda); 
    
    if(button !== null)
        AdfActionEvent.queue(button, button.getPartialSubmit());
}
/*
 * Cerrar el popUp "p2"
 * */
 function hideWindowExport(event) {
    var dialog = event.getSource();
    var popup = dialog.findComponent("p2");
    popup.hide();
}