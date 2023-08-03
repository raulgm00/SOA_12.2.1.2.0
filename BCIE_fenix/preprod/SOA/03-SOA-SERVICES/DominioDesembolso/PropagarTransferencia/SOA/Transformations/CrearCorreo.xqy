xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace pro="http:/www.bcie.org/PropagarTransferenciaXSD";
(:: import schema at "../Schemas/PropagarTransferenciaXSD.xsd" ::)

declare variable $tablaCorreo as element() (:: schema-element(pro:tablaCorreo) ::) external;

declare function local:funcCrearcorreo($tablaCorreo as element() (:: schema-element(pro:tablaCorreo) ::)) as element() (:: schema-element(pro:estructuraCorreo) ::) {
    <pro:estructuraCorreo>
    <table>
  <tr>
    <td>Numero de Reserva</td>
    <td>Numero de Cuenta</td>
    <td>Referencia</td>
    <td>Monto</td>
    <td>Operacion</td>
    <td>Estatus</td>
  </tr>
  {
  for $transferencia in $tablaCorreo/pro:tablaTransferencias
  return
  <tr>
    <td>{fn:data($transferencia/pro:numeroReserva)}</td>
    <td>{fn:data($transferencia/pro:numeroCuenta)}</td>
    <td>{fn:data($transferencia/pro:referencia)}</td>
    <td>{fn:data($transferencia/pro:monto)}</td>
    <td>{fn:data($transferencia/pro:operacion)}</td>
    <td>{fn:data($transferencia/pro:estatus)}</td>
  </tr>
  }
</table>
    </pro:estructuraCorreo>
};

local:funcCrearcorreo($tablaCorreo)
