xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTransferenciasRecursosById_DB";
(:: import schema at "../XSD/ConsultarTransferenciasRecursosById_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarTransferenciasRecursosById_DBOutputCollection as element() (:: schema-element(ns1:ConsultarTransferenciasRecursosById_DBOutputCollection) ::) external;

declare function local:func($ConsultarTransferenciasRecursosById_DBOutputCollection as element() (:: schema-element(ns1:ConsultarTransferenciasRecursosById_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarTransferenciasRecursosByIdResponse) ::) {
    <ns2:ConsultarTransferenciasRecursosByIdResponse>
        <ns2:ContratoDesembolso>
          {
        for $transferencia in $ConsultarTransferenciasRecursosById_DBOutputCollection/ns1:ConsultarTransferenciasRecursosById_DBOutput
        return
        <des:TransferenciaRecursos>
            <des:idTransferencia>{fn:data($transferencia/ns1:ID_TRANSFERENCIA)}</des:idTransferencia>
            <des:idDesembolso>{fn:data($transferencia/ns1:ID_CONTRATO_DESEMBOLSO)}</des:idDesembolso>
            <des:idFacturador>{fn:data($transferencia/ns1:BHQ_TRANSFERENCIA_FT05)}</des:idFacturador>
            <des:idLineaPasiva></des:idLineaPasiva>
            <des:Monto>
                <com:tipo>
                    <cat:Id></cat:Id>   
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta>DESEMBOLSADO</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:tipo>
                <com:importe>{fn:data($transferencia/ns1:MONTO_DESEMBOLSADO)}</com:importe>
                <com:moneda>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:moneda>
            </des:Monto>
            <des:fecha>{fn:data($transferencia/ns1:FECHA)}</des:fecha>
            <des:fechaEfectiva>{fn:data($transferencia/ns1:FECHA_EFECTIVA_FT05)}</des:fechaEfectiva>
            <des:FormaTransferencia>
                <cat:Id>{fn:data($transferencia/ns1:ID_TCA_FORMA_TRANSFERENCIA)}</cat:Id>
                <cat:Descripcion>{fn:data($transferencia/ns1:DESCRIPCION_FORMA_TRANS)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($transferencia/ns1:DESCRIPCION_CORTA_FORMA_TRANS)}</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($transferencia/ns1:COD_EXTERNO_FORMA_TRANS)}</cat:codigoExterno>
            </des:FormaTransferencia>
            <des:idBanco>{fn:data($transferencia/ns1:ID_BANCO_TRANSFERENCIA)}</des:idBanco>
            <des:nombreBanco>{fn:data($transferencia/ns1:NOMBRE_BANCO_TRANSFERENCIA)}</des:nombreBanco>
            <des:numeroCuenta>{fn:data($transferencia/ns1:NUMERO_CUENTA)}</des:numeroCuenta>
            <des:nombreCuenta>{fn:data($transferencia/ns1:NOMBRE_CUENTA)}</des:nombreCuenta>
            <des:estatus></des:estatus>
            <des:fechaRegistro>{fn:data($transferencia/ns1:FECHA_REGISTRO)}</des:fechaRegistro>
        </des:TransferenciaRecursos>
        }
        </ns2:ContratoDesembolso>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{
            if (count($ConsultarTransferenciasRecursosById_DBOutputCollection/ns1:ConsultarTransferenciasRecursosById_DBOutput)>0)then 'Consulta Exitosa'
            else 'No existen Registros'
            }</res:message>
        </ns2:Resultado>
    </ns2:ConsultarTransferenciasRecursosByIdResponse>
};

local:func($ConsultarTransferenciasRecursosById_DBOutputCollection)
