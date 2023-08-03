xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/ConsultarConfiguracion";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConsultarConfiguracion/V1/Schema/ConsultarConfiguracionMO.xsd" ::)
declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) external;
declare variable $outConsultarInformacionDesembolso.response as element() (:: schema-element(des:ConsultarInformacionReglasByIdResponse) ::) external;

declare function local:funcXq_creartransferenciaflexcube($outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::), 
                                                         $outConsultarInformacionDesembolso.response as element() (:: schema-element(des:ConsultarInformacionReglasByIdResponse) ::)) 
                                                         as element() (:: schema-element(des:CrearTransferenciaFLEXCUBERequest) ::) {
    <des:CrearTransferenciaFLEXCUBERequest>
        <des:ContratoDesembolso>{
          $outConsultarDesembolsoById.response/des:ContratoDesembolso/*
        }</des:ContratoDesembolso>
        <des:Parametros>
            <com:name>NUMERO_LINEA</com:name>
            <com:value>{fn:data($outConsultarInformacionDesembolso.response/des:LineaCredito/lin:NumeroLineaCredito)}</com:value>
        </des:Parametros>
        <des:Parametros>
            <com:name>TIPO_RECURSO</com:name>
            <com:value>ND</com:value>
        </des:Parametros>
        <des:Parametros>
            <com:name>PAIS</com:name>
            <com:value>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:pais/cat:codigoExterno)}</com:value>
        </des:Parametros>
        <des:Parametros>
            <com:name>SECTOR</com:name>
            <com:value>{fn:data($outConsultarInformacionDesembolso.response/des:Cliente/cli:sector/cat:codigoExterno)}</com:value>
        </des:Parametros>
        <des:Parametros>
          <com:name>EJECUTIVO</com:name>
            <com:value>{fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:usuario)}</com:value>
        </des:Parametros>
        <des:Parametros>
          <com:name>OBJETIVO_ESTRATEGICO</com:name>
            <com:value>{fn:data($outConsultarInformacionDesembolso.response/des:Operacion/ope:objetivosEstrategicos)}</com:value>
        </des:Parametros>
    </des:CrearTransferenciaFLEXCUBERequest>
};

local:funcXq_creartransferenciaflexcube($outConsultarDesembolsoById.response, $outConsultarInformacionDesembolso.response)
