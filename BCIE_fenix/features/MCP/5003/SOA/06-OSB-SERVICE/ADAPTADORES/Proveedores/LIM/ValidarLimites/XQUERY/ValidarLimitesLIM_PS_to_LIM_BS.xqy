xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace lim="http://org/bcie/ws/middle/LIM/LIM.wsdl";
declare namespace typ="http://org/bcie/ws/middle/LIM/LIM.wsdl/types/";
(:: import schema at "../../WSDL/LIM.wsdl" ::)
declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace functx = "http://www.functx.com";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $ValidarLimitesLIMRequest as element() (:: schema-element(ns1:ValidarLimitesLIMRequest) ::) external;

declare function local:func($ValidarLimitesLIMRequest as element() (:: schema-element(ns1:ValidarLimitesLIMRequest) ::)) as element() (:: element(*, typ:LimOperacionTypeUserArray) ::) {
    <lim:validarLimitesOperacion>
         <operaciones>
            <typ:LimOperacionTypeUser> 
               {
               if(fn:string( data($ValidarLimitesLIMRequest/ns1:Operacion/ope:contrato/con:LineaCredito/lin:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta = "DESEMBOLSO_USD"]/com:importe))='')then
                   <typ:montoUsd>{fn:data($ValidarLimitesLIMRequest/ns1:Operacion/ope:contrato/con:LineaCredito/lin:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta = "DESEMBOLSO_USD"]/com:importe)}</typ:montoUsd>
               else
                   <typ:montoUsd>{data($ValidarLimitesLIMRequest/ns1:Operacion/ope:contrato/con:LineaCredito/lin:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta = "DESEMBOLSO_USD"]/com:importe)div 1000000}</typ:montoUsd>
                }
               <typ:destino>{fn:data($ValidarLimitesLIMRequest/ns1:Operacion/ope:contrato/con:LineaCredito/lin:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)}</typ:destino>
               <typ:grupoEconomico>{fn:data($ValidarLimitesLIMRequest/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:codigoExterno)}</typ:grupoEconomico>
               <typ:pais>{fn:data($ValidarLimitesLIMRequest/ns1:Operacion/ope:cliente/cli:pais/cat:codigoExterno)}</typ:pais>
               <typ:cliente>{fn:data($ValidarLimitesLIMRequest/ns1:Operacion/ope:cliente/cli:idFacturador)}</typ:cliente>
               <typ:tipoPersona>{fn:data($ValidarLimitesLIMRequest/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:codigoExterno)}</typ:tipoPersona>       
               <typ:sectorInstitucional>{fn:data($ValidarLimitesLIMRequest/ns1:Operacion/ope:cliente/cli:sector/cat:codigoExterno)}</typ:sectorInstitucional>
               <typ:tipoGarantia>{fn:data($ValidarLimitesLIMRequest/ns1:Operacion/ope:tipoGarantia/cat:codigoExterno)}</typ:tipoGarantia>
            {
            if (fn:empty(data($ValidarLimitesLIMRequest/ns1:Operacion/ope:contrato/con:LineaCredito/lin:EsRevolvente)) and $ValidarLimitesLIMRequest/ns1:Operacion/ope:contrato/con:LineaCredito/lin:EsRevolvente = true()) 
            then <typ:revolvente>S</typ:revolvente>
            else <typ:revolvente>N</typ:revolvente>
            }
            </typ:LimOperacionTypeUser>
            
            {
                if (fn:count($ValidarLimitesLIMRequest/ns1:Monto[com:tipo/cat:DescripcionCorta = "RECORDSET_GE"]) > 0) then
                
                <typ:LimOperacionTypeUser>
                      {
                      if (functx:between-inclusive($ValidarLimitesLIMRequest/ns1:Operacion/ope:contrato/con:LineaCredito/lin:ContratoDesembolso/des:estado/cat:Id,14,16)) 
                      then
                       <typ:montoUsd>{((fn:data($ValidarLimitesLIMRequest/ns1:Monto[com:tipo/cat:DescripcionCorta = "RECORDSET_GE"]/com:importe)-fn:data($ValidarLimitesLIMRequest/ns1:Operacion/ope:contrato/con:LineaCredito/lin:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta = "DESEMBOLSO_USD"]/com:importe))) div 1000000}</typ:montoUsd>
                       else<typ:montoUsd>{fn:data($ValidarLimitesLIMRequest/ns1:Monto[com:tipo/cat:DescripcionCorta = "RECORDSET_GE"]/com:importe) div 1000000}</typ:montoUsd>
                       }
                       <typ:destino></typ:destino>
                       <typ:grupoEconomico>{fn:data($ValidarLimitesLIMRequest/ns1:Operacion/ope:cliente/cli:grupoEconomico/cat:codigoExterno)}</typ:grupoEconomico>
                       <typ:pais>{fn:data($ValidarLimitesLIMRequest/ns1:Operacion/ope:cliente/cli:pais/cat:codigoExterno)}</typ:pais>
                       <typ:cliente></typ:cliente>
                       <typ:tipoPersona></typ:tipoPersona>              
                       <typ:sectorInstitucional></typ:sectorInstitucional>
                       <typ:tipoGarantia></typ:tipoGarantia>
                       <typ:revolvente></typ:revolvente>
                    </typ:LimOperacionTypeUser>
                   
                else()
            }
            
            <typ:LimOperacionTypeUser>
            
            {
            let $idesembolso := fn:data($ValidarLimitesLIMRequest/ns1:Operacion/ope:contrato/con:LineaCredito/lin:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta = "DESEMBOLSO_USD"]/com:importe)
            let $igpoEcDes := fn:data($ValidarLimitesLIMRequest/ns1:Monto[com:tipo/cat:DescripcionCorta = "RECORDSET_GE"]/com:importe)
            let $ipais := fn:data($ValidarLimitesLIMRequest/ns1:Monto[com:tipo/cat:DescripcionCorta = "RECORDSET_PAIS"]/com:importe)
            return 
            if (functx:between-inclusive($ValidarLimitesLIMRequest/ns1:Operacion/ope:contrato/con:LineaCredito/lin:ContratoDesembolso/des:estado/cat:Id,14,16)) 
              then
                if (fn:count($ValidarLimitesLIMRequest/ns1:Monto[com:tipo/cat:DescripcionCorta = "RECORDSET_GE"]) > 0) then
                  <typ:montoUsd>{($ipais  -  $igpoEcDes )div 1000000}</typ:montoUsd>
                else
                  <typ:montoUsd>{($ipais  -  $idesembolso )div 1000000}</typ:montoUsd>
               else<typ:montoUsd>{($ipais  -  $igpoEcDes)div 1000000}</typ:montoUsd>
               }
               <typ:destino></typ:destino>
               <typ:grupoEconomico></typ:grupoEconomico>
               <typ:pais>{fn:data($ValidarLimitesLIMRequest/ns1:Operacion/ope:cliente/cli:pais/cat:codigoExterno)}</typ:pais>
               <typ:cliente></typ:cliente>
               <typ:tipoPersona></typ:tipoPersona>              
               <typ:sectorInstitucional></typ:sectorInstitucional>
               <typ:tipoGarantia></typ:tipoGarantia>
               <typ:revolvente></typ:revolvente>
            </typ:LimOperacionTypeUser>
         </operaciones>
      </lim:validarLimitesOperacion>
};
declare function functx:between-inclusive
  ( $value as xs:anyAtomicType? ,
    $minValue as xs:anyAtomicType ,
    $maxValue as xs:anyAtomicType )  as xs:boolean {

   $value >= $minValue and $value <= $maxValue
 } ;
local:func($ValidarLimitesLIMRequest)