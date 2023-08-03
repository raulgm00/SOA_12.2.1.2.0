xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace cat="http://www.bcie.org/CatalogoBO";
declare namespace are="http://org/bcie/ws/middle/ARE/ARE.wsdl";
declare namespace typ="http://org/bcie/ws/middle/ARE/ARE.wsdl/types/";
(:: import schema at "../../WSDL/ARE.wsdl" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";
declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";


declare variable $EliminarUtilizacionARERequest as element() (:: schema-element(ns1:EliminarUtilizacionARERequest) ::) external;

declare function local:func($EliminarUtilizacionARERequest as element() (:: schema-element(ns1:EliminarUtilizacionARERequest) ::)) as element() (:: element(are:eliminarUtilizacion) ::) {
    <are:eliminarUtilizacion>
           <utilizacion>          
             <typ:codigoAsignacion>{fn:data($EliminarUtilizacionARERequest/ns1:LineaCredito/lin:CodigoAsignacion)}</typ:codigoAsignacion>
             <typ:referencia>{fn:data($EliminarUtilizacionARERequest/ns1:LineaCredito/lin:atributos[com:name='REFERENCIA']/com:value)}</typ:referencia>
             <typ:fecha>{fn:data($EliminarUtilizacionARERequest/ns1:LineaCredito/lin:FechaValor)}</typ:fecha>
             <typ:listaFuentes>
             {
             for $Utilizacion in $EliminarUtilizacionARERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:Utilizacion
             return
                  <typ:array>
                     <typ:numeroAsignacion>{fn:data($Utilizacion/lin:NumeroAsignacion)}</typ:numeroAsignacion>
                     <typ:montoUtilizado>{fn:data($Utilizacion/lin:MontoAsignado)}</typ:montoUtilizado>                     
                    </typ:array> 
             }       
             </typ:listaFuentes>             
           </utilizacion>
     </are:eliminarUtilizacion>
};

local:func($EliminarUtilizacionARERequest)
