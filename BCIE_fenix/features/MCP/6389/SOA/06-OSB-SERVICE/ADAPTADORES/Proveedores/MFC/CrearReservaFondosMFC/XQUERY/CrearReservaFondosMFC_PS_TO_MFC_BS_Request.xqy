xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace mfc="http://org/bcie/ws/middle/MFC/MFC.wsdl";

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $CrearReservaFondosMFCRequest as element() (:: schema-element(ns1:CrearReservaFondosMFCRequest) ::) external;

declare function local:func($CrearReservaFondosMFCRequest as element() (:: schema-element(ns1:CrearReservaFondosMFCRequest) ::)) as element() (:: element(mfc:crearReservaFondos) ::) {
    <mfc:crearReservaFondos>
         <referencia>{fn:data($CrearReservaFondosMFCRequest/ns1:Referencia)}</referencia>
         <codigoAplicacion>FENIX</codigoAplicacion>
         <fechaRequerido>{fn:current-date()}</fechaRequerido>
         <monto>{fn:data($CrearReservaFondosMFCRequest/ns1:Monto/com:importe)}</monto>
         <moneda>{fn:data($CrearReservaFondosMFCRequest/ns1:Monto/com:moneda/cat:codigoExterno)}</moneda>
         <descripcion>{fn:data($CrearReservaFondosMFCRequest/ns1:Descripcion)}</descripcion>
         <rubro>2003001</rubro>
         <codigoUsuario>{fn:data($CrearReservaFondosMFCRequest/ns1:CodigoUsuario)}</codigoUsuario>
         <autorizar>{fn:data($CrearReservaFondosMFCRequest/ns1:Autorizar)}</autorizar>
         <cuentaBancaria>{fn:data($CrearReservaFondosMFCRequest/ns1:CuentaBancaria)}</cuentaBancaria>
         <disponible>{
         if ($CrearReservaFondosMFCRequest/ns1:Autorizar = 'A')then 'S'else(fn:data($CrearReservaFondosMFCRequest/ns1:Disponible))
         }</disponible>
         <fechaAplicacion>{fn:data($CrearReservaFondosMFCRequest/ns1:FechaAplicacion)}</fechaAplicacion>
         <fechaAprobacion>{fn:current-date()}</fechaAprobacion>
         <usuarioAutoriza>{fn:data($CrearReservaFondosMFCRequest/ns1:UsuarioAutoriza)}</usuarioAutoriza>
    </mfc:crearReservaFondos>
};

local:func($CrearReservaFondosMFCRequest)
