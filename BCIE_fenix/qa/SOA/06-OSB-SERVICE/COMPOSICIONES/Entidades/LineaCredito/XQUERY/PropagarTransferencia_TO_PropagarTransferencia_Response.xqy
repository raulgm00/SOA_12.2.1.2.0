xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $PropagarTransferenciaRequest as element() (:: schema-element(ns1:PropagarTransferenciaRequest) ::) external;

declare function local:func($PropagarTransferenciaRequest as element() (:: schema-element(ns1:PropagarTransferenciaRequest) ::)) as element() (:: schema-element(ns1:PropagarTransferenciaResponse) ::) {
    <ns1:PropagarTransferenciaResponse>
        <ns1:Transferencia>
            <des:idTransferencia>{fn:data($PropagarTransferenciaRequest/ns1:idTransferencia)}</des:idTransferencia>
            <des:idDesembolso></des:idDesembolso>
            <des:idFacturador>BHQLD02072720012</des:idFacturador>
            <des:Monto>
                <com:tipo>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:tipo>
                <com:importe></com:importe>
                <com:moneda>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:moneda>
            </des:Monto>
            <des:referenciaMensaje></des:referenciaMensaje>
            <des:esConsolidada></des:esConsolidada>
            <des:idBancoTransferencia></des:idBancoTransferencia>
            <des:numeroCuenta></des:numeroCuenta>
            <des:nombreBanco></des:nombreBanco>
            <des:idOperacion></des:idOperacion>
            <des:tipoMensaje></des:tipoMensaje>
            <des:estado></des:estado>
            <des:Beneficiario>
                <des:tipoOpcion></des:tipoOpcion>
                <des:numeroCta></des:numeroCta>
                <des:identificador></des:identificador>
                <des:beneficiario></des:beneficiario>
                <des:direccion></des:direccion>
            </des:Beneficiario>
            <des:Banco>
                <des:tipoOpcion></des:tipoOpcion>
                <des:numeroCta></des:numeroCta>
                <des:identificador></des:identificador>
                <des:beneficiario></des:beneficiario>
                <des:direccion></des:direccion>
            </des:Banco>
            <des:Intermediario>
                <des:tipoOpcion></des:tipoOpcion>
                <des:numeroCta></des:numeroCta>
                <des:identificador></des:identificador>
                <des:beneficiario></des:beneficiario>
                <des:direccion></des:direccion>
            </des:Intermediario>
        </ns1:Transferencia>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Propagación Exitosa</res:message>
        </ns1:Resultado>
    </ns1:PropagarTransferenciaResponse>
};

local:func($PropagarTransferenciaRequest)
