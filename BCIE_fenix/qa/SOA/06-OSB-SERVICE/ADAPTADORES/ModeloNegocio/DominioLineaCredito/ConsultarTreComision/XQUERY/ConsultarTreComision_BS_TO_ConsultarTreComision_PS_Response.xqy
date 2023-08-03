xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTreComision_DB";
(:: import schema at "../XSD/ConsultarTreComision_DB.xsd" ::)

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarTreComision_OutputCollection as element() (:: schema-element(ns1:ConsultarTreComision_DBOutputCollection) ::) external;

declare function local:func($ConsultarTreComision_OutputCollection as element() (:: schema-element(ns1:ConsultarTreComision_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarTreComisionResponse) ::) {
    <ns2:ConsultarTreComisionResponse>              
         <ns2:Comision>
            {for $idComision in fn:data($ConsultarTreComision_OutputCollection/ns1:ConsultarTreComision_DBOutput/ns1:ID)      
              return
                <com:idComision>{$idComision}</com:idComision>
            }
            {for $idOperacion in fn:data($ConsultarTreComision_OutputCollection/ns1:ConsultarTreComision_DBOutput/ns1:ID_OPERACION)
              return
                <com:idOperacion>{$idOperacion}</com:idOperacion>
            }
            <com:tipoComision>{
                for $tipoComision in fn:data($ConsultarTreComision_OutputCollection/ns1:ConsultarTreComision_DBOutput/ns1:ID_TCA_COMISION)
                  return
                    <com:idCatComision>{$tipoComision}</com:idCatComision>            
            }
            </com:tipoComision>
        </ns2:Comision>          
      <ns2:Resultado>{
          if(empty(fn:data($ConsultarTreComision_OutputCollection/ns1:ConsultarTreComision_DBOutput/ns1:ID)))
            then(<res:message>Registro No Encontrado</res:message>)
              else(<res:message>Registro Encontrado</res:message>)
      }
      </ns2:Resultado>
    </ns2:ConsultarTreComisionResponse>
};

local:func($ConsultarTreComision_OutputCollection)
