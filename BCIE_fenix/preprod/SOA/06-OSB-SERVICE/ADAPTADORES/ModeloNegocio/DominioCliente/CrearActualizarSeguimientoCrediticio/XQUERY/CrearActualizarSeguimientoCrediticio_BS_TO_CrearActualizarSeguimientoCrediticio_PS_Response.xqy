xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarSeguimientoCrediticio_BD";
(:: import schema at "../XSD/CrearActualizarSeguimientoCrediticio_BD_table.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $SeguimientoCrediticioCollection as element() (:: schema-element(ns1:SeguimientoCrediticioCollection) ::) external;

declare function local:func($SeguimientoCrediticioCollection as element() (:: schema-element(ns1:SeguimientoCrediticioCollection) ::)) as element() (:: schema-element(ns2:CrearActualizarSeguimientoCrediticioResponse) ::) {
    <ns2:CrearActualizarSeguimientoCrediticioResponse>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:concat("ID: ",fn:data($SeguimientoCrediticioCollection/ns1:SeguimientoCrediticio/ns1:id)," insertado o actualizado correctamente")}</res:message>
        </ns2:Resultado>
    </ns2:CrearActualizarSeguimientoCrediticioResponse>
};

local:func($SeguimientoCrediticioCollection)
