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


declare variable $RegistrarUtilizacionARERequest as element() (:: schema-element(ns1:RegistrarUtilizacionARERequest) ::) external;


declare function local:func($RegistrarUtilizacionARERequest as element() (:: schema-element(ns1:RegistrarUtilizacionARERequest) ::)) as element() (:: element(are:registrarUtilizacion) ::){                           
      <are:registrarUtilizacion>
           <utilizacion>          
             <typ:codigoAsignacion>{fn:data($RegistrarUtilizacionARERequest/ns1:LineaCredito/lin:CodigoAsignacion)}</typ:codigoAsignacion>
             <typ:referencia>{fn:data($RegistrarUtilizacionARERequest/ns1:LineaCredito/lin:atributos[com:name='REFERENCIA']/com:value)}</typ:referencia>
             <typ:fecha>{fn:current-date()}</typ:fecha>
             <typ:listaFuentes>
             {
             for $utilizacion in $RegistrarUtilizacionARERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:Utilizacion
             return
                  <typ:array>
                     <typ:numeroAsignacion>{fn:data($utilizacion/lin:NumeroAsignacion)}</typ:numeroAsignacion>
                     <typ:montoUtilizado>{fn:data($utilizacion/lin:Monto[com:tipo/cat:DescripcionCorta = 'DESEMBOLSO']/com:importe)}</typ:montoUtilizado>                     
                    </typ:array>   
             }
             </typ:listaFuentes>
           </utilizacion>
        </are:registrarUtilizacion>
};

local:func($RegistrarUtilizacionARERequest)