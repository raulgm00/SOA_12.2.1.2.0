xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://org/bcie/ws/middle/ARE/ARE.wsdl/types/";
(:: import schema at "../../WSDL/ARE.wsdl" ::)
declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace lin = "http://www.bcie.org/LineaCreditoBO";
declare namespace ns3="http://org/bcie/ws/middle/ARE/ARE.wsdl";

declare variable $PropagarFuenteRequest as element() (:: schema-element(ns1:PropagarFuenteRequest) ::) external;

declare function local:func($PropagarFuenteRequest as element() (:: schema-element(ns1:PropagarFuenteRequest) ::)) as element() (:: element(*, ns2:AsignacionTypeUser) ::) {
    <ns3:salvarAsignacion>
      <asignacion>
        <ns2:codigoAsignacion>{fn:data($PropagarFuenteRequest/ns1:LineaCredito/lin:CodigoAsignacion)}</ns2:codigoAsignacion>
        <ns2:tipoMovimiento>A</ns2:tipoMovimiento>
        <ns2:estadoMovimiento>A</ns2:estadoMovimiento>
        <ns2:descripcion>{fn:data($PropagarFuenteRequest/ns1:LineaCredito/lin:Descripcion)}</ns2:descripcion>
        <ns2:montoTotal>{fn:data($PropagarFuenteRequest/ns1:LineaCredito/lin:MontoLinea)}</ns2:montoTotal>
        <ns2:fechaMovimiento>{fn:current-date()}</ns2:fechaMovimiento>
        <ns2:listaLineas>
            <ns2:array>{fn:data($PropagarFuenteRequest/ns1:LineaCredito/lin:NumeroLineaCredito)}</ns2:array>
        </ns2:listaLineas>
        <ns2:listaDetalles>
            {
                for $Fuente in $PropagarFuenteRequest/ns1:LineaCredito/lin:Fuente
                return 
                <ns2:array>
                    <ns2:numeroAsignacion>{fn:data($Fuente/lin:NumeroAsignacion)}</ns2:numeroAsignacion>
                 
                    <ns2:codigoLineaPasiva>{fn:data($Fuente/lin:idLineaPasiva)}</ns2:codigoLineaPasiva>
                                       
                    <ns2:comentarios>{fn:data($Fuente/lin:Comentario)}</ns2:comentarios>
                    <ns2:montoAsignado>{fn:data($Fuente/lin:MontoAsignado)}</ns2:montoAsignado>
                
                      <ns2:pendienteObtener>{ if(string($Fuente/lin:FechaObtenido)='')then                       
                      'P'
                      else
                      ()}</ns2:pendienteObtener>
                   
                    {
                    if(fn:empty($Fuente/lin:FechaObtenido))then
                      <ns2:fechaObtenido/>
                    else 
                      <ns2:fechaObtenido>{fn:data($Fuente/lin:FechaObtenido)}</ns2:fechaObtenido>
                    }
                </ns2:array>
            }</ns2:listaDetalles>
      </asignacion>
    </ns3:salvarAsignacion>
};

local:func($PropagarFuenteRequest)
