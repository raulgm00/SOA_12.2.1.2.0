xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarBitacoraProcesoClienteDB";
(:: import schema at "../XSD/ConsultarBitacoraProcesoClienteDB.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarBitacoraProcesoClienteDBOutputCollection as element() (:: schema-element(ns1:ConsultarBitacoraProcesoClienteDBOutputCollection) ::) external;

declare function local:func($ConsultarBitacoraProcesoClienteDBOutputCollection as element() (:: schema-element(ns1:ConsultarBitacoraProcesoClienteDBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarBitacoraProcesoClienteResponse) ::) {
    <ns2:ConsultarBitacoraProcesoClienteResponse>
        {
            for $ConsultarBitacoraProcesoClienteDBOutput in $ConsultarBitacoraProcesoClienteDBOutputCollection/ns1:ConsultarBitacoraProcesoClienteDBOutput
            return 
            if($ConsultarBitacoraProcesoClienteDBOutput/ns1:BAN_FINALIZADO = 0)
            then
            <ns2:Bitacora>
                <cre:id>{fn:data($ConsultarBitacoraProcesoClienteDBOutput/ns1:ID)}</cre:id>
                <cre:idCliente>{fn:data($ConsultarBitacoraProcesoClienteDBOutput/ns1:ID_CLIENTE)}</cre:idCliente>
                <cre:idProceso>{fn:data($ConsultarBitacoraProcesoClienteDBOutput/ns1:ID_PROCESO)}</cre:idProceso>
                <cre:instanciaProceso>{fn:data($ConsultarBitacoraProcesoClienteDBOutput/ns1:INSTANCIA_PROCESO)}</cre:instanciaProceso>
                <cre:idTarea>{fn:data($ConsultarBitacoraProcesoClienteDBOutput/ns1:ID_TAREA)}</cre:idTarea>
                <cre:usuario>{fn:data($ConsultarBitacoraProcesoClienteDBOutput/ns1:USUARIO)}</cre:usuario>
                <cre:nombreUsuario>{fn:data($ConsultarBitacoraProcesoClienteDBOutput/ns1:NOMBRE_USUARIO)}</cre:nombreUsuario>
                <cre:fechaRegistro>{fn:data($ConsultarBitacoraProcesoClienteDBOutput/ns1:FECHA_REGISTRO)}</cre:fechaRegistro>
             </ns2:Bitacora>
             else()
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message></res:message>
        </ns2:Resultado>
    </ns2:ConsultarBitacoraProcesoClienteResponse>
};

local:func($ConsultarBitacoraProcesoClienteDBOutputCollection)
