xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/EquipoTrabajoClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearEquipoTrabajoCliente/V1/Schema/CrearEquipoTrabajoClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearEquipoTrabajoCliente";
(:: import schema at "../XSD/CrearEquipoTrabajoCliente_table.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $EquipoTrabajoCollection as element() (:: schema-element(ns1:EquipoTrabajoCollection) ::) external;

declare function local:func($EquipoTrabajoCollection as element() (:: schema-element(ns1:EquipoTrabajoCollection) ::)) as element() (:: schema-element(ns2:CrearEquipoTrabajoClienteResponse) ::) {
    <ns2:CrearEquipoTrabajoClienteResponse>
        <ns2:Resultado>
            <res:result>OK</res:result>
            {if(fn:string-length(fn:string($EquipoTrabajoCollection/ns1:EquipoTrabajo/ns1:id)) > 0)then
              <res:message>Operacion exitosa</res:message>
            else
              <res:message>Operacion sin resultados</res:message>
            }  
        </ns2:Resultado>
    </ns2:CrearEquipoTrabajoClienteResponse>
};

local:func($EquipoTrabajoCollection)
