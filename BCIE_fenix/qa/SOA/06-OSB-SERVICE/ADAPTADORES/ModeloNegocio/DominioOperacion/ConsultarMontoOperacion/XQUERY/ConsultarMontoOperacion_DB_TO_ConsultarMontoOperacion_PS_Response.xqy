xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarMontoOperacion_db";
(:: import schema at "../XSD/ConsultarMontoOperacion_db.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ResponseMontoOperacion as element() (:: schema-element(ns1:ConsultarMontoOperacion_dbOutputCollection) ::) external;

declare function local:func($ResponseMontoOperacion as element() (:: schema-element(ns1:ConsultarMontoOperacion_dbOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarMontoOperacionResponse) ::) {
    <ns2:ConsultarMontoOperacionResponse>
        <ns2:MontoOperacion>
            {
                for $ConsultarMontoOperacion_dbOutput in $ResponseMontoOperacion/ns1:ConsultarMontoOperacion_dbOutput
                return 
                <ope:montoOperacion>
                    <ope:id>{fn:data($ConsultarMontoOperacion_dbOutput/ns1:ID)}</ope:id>
                    <ope:idOperacion>{fn:data($ConsultarMontoOperacion_dbOutput/ns1:ID_OPERACION)}</ope:idOperacion>
                    <ope:monto>{fn:data($ConsultarMontoOperacion_dbOutput/ns1:MONTO)}</ope:monto>
					<ope:IdTcaTipoMonto>{fn:data($ConsultarMontoOperacion_dbOutput/ns1:ID_TCA_TIPO_MONTO)}</ope:IdTcaTipoMonto>
                    <ope:Descripcion>{fn:data($ConsultarMontoOperacion_dbOutput/ns1:DESCRIPCION)}</ope:Descripcion></ope:montoOperacion>
            }</ns2:MontoOperacion>
        <ns2:Resultado>
            <res:result>Ok</res:result>
            <res:message>Consulta realizada existosamente</res:message>
            
        </ns2:Resultado>
    </ns2:ConsultarMontoOperacionResponse>
};

local:func($ResponseMontoOperacion)
