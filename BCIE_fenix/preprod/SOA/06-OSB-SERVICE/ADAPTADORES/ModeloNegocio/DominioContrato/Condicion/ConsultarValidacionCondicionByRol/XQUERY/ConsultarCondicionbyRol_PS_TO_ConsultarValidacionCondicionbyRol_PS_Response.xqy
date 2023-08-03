xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarValidacionCondicionByRol";
(:: import schema at "../XSD/ConsultarValidacionCondicionByRol.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarValidacionCondicionByRolOutputCollection as element() (:: schema-element(ns1:ConsultarValidacionCondicionByRolOutputCollection) ::) external;
declare variable $ConsultarCondicionByRolResponse as element() (:: schema-element(ns2:ConsultarCondicionByRolResponse) ::) external;

declare function local:func($ConsultarValidacionCondicionByRolOutputCollection as element() (:: schema-element(ns1:ConsultarValidacionCondicionByRolOutputCollection) ::), 
                            $ConsultarCondicionByRolResponse as element() (:: schema-element(ns2:ConsultarCondicionByRolResponse) ::)) 
                            as element() (:: schema-element(ns2:ConsultarCondicionValidacionByRolResponse) ::) {
    <ns2:ConsultarCondicionValidacionByRolResponse>
 {
            for $ConsultarCondicionByRolOutput in $ConsultarCondicionByRolResponse/ns2:Condicion
            return
        <ns2:Condicion>
          
            
            {$ConsultarCondicionByRolOutput}
              
              {for $ConsultarValidacionCondicionByRolOutput in $ConsultarValidacionCondicionByRolOutputCollection/ns1:ConsultarValidacionCondicionByRolOutput
            where ($ConsultarValidacionCondicionByRolOutput/ns1:ID_CONDICION=$ConsultarCondicionByRolOutput/con:idCondicion)
            return
            
            <ns2:ValidacionCondicion>
                <con:idCondicion>{fn:data($ConsultarValidacionCondicionByRolOutput/ns1:ID_CONDICION)}</con:idCondicion>
                <con:rolBPM>
                    <cat:Id>{fn:data($ConsultarValidacionCondicionByRolOutput/ns1:ID_ROL_BPM)}</cat:Id>                 
                </con:rolBPM>
                <con:loginUsuario>{fn:data($ConsultarValidacionCondicionByRolOutput/ns1:LOGIN_USUARIO)}</con:loginUsuario>
                <con:nombreUsuario>{fn:data($ConsultarValidacionCondicionByRolOutput/ns1:NOMBRE_USUARIO)}</con:nombreUsuario>
                <con:observacion>
                    <con:id>{fn:data($ConsultarValidacionCondicionByRolOutput/ns1:ID_OBSERVACION)}</con:id>
              
                </con:observacion>
                <con:esValidador>{if (  string($ConsultarValidacionCondicionByRolOutput/ns1:ES_VALIDADOR)='') then false() else 
                if (                fn:data($ConsultarValidacionCondicionByRolOutput/ns1:ES_VALIDADOR)=1) then true() else false()
                }</con:esValidador>
                <con:estado>{
                if (string($ConsultarValidacionCondicionByRolOutput/ns1:ESTADO)='') then false() else if(
                fn:data($ConsultarValidacionCondicionByRolOutput/ns1:ESTADO)=1) then true() else false()
                
                }</con:estado>
                <con:fechaRegistro>{fn:data($ConsultarValidacionCondicionByRolOutput/ns1:FECHA_REGISTRO)}</con:fechaRegistro>
            </ns2:ValidacionCondicion>
         }
        
        </ns2:Condicion>
            }
 <ns2:Resultado>
     <res:result>OK</res:result>
     <res:message>{
     if(count($ConsultarCondicionByRolResponse/ns2:Condicion)=0) then 'No existen registros' else 'Operación exitosa'
     }</res:message>
 </ns2:Resultado> 
    </ns2:ConsultarCondicionValidacionByRolResponse>
};

local:func($ConsultarValidacionCondicionByRolOutputCollection, $ConsultarCondicionByRolResponse)