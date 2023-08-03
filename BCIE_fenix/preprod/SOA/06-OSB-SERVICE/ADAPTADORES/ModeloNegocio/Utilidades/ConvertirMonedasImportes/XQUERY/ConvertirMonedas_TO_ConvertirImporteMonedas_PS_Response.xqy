xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConversorMonedasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConversorMonedas/V1/Schema/ConversorMonedasMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace con = "http://www.bcie.org/ConversorMonedasBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ConvertirMonedasImportesResponse as element() (:: schema-element(ns1:ConvertirMonedasImportesResponse) ::) external;

declare function local:func($ConvertirMonedasImportesResponse as element() (:: schema-element(ns1:ConvertirMonedasImportesResponse) ::)) as element() (:: schema-element(ns1:ConvertirMonedasImportesResponse) ::) {
    <ns1:ConvertirMonedasImportesResponse>
        {
        if(fn:string-length(<ns1:ConvierteMonedasImporte>
                                  <con:montoConvertido></con:montoConvertido>
                            </ns1:ConvierteMonedasImporte>)!=0)then
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta realizada correctamente</res:message>
        </ns1:Resultado>
        else
            <ns1:Resultado>
            <res:result>ERROR</res:result>
            <res:message>Consulta realizada correctamente</res:message>
            </ns1:Resultado>
        }
    </ns1:ConvertirMonedasImportesResponse>
};

local:func($ConvertirMonedasImportesResponse)
