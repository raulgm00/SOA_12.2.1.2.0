xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearAvisoCobroV2_db";
(:: import schema at "../XSD/CrearAvisoCobroV2_db_sp.xsd" ::)

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $avisos as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($avisos as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CrearAvisoCobroV2Response) ::) {
    <ns2:CrearAvisoCobroV2Response>
        <ns2:AvisoCobro>
        {            
            for $row in $avisos/ns1:P_LISTA_AVISOS/ns1:Row
            return
            <ges:aviso>
                <ges:codigoCliente>{fn:data($row/ns1:Column[@name ='CODIGO_CLIENTE'])}</ges:codigoCliente>
                <ges:nombreCompleto>{fn:data($row/ns1:Column[@name ='FULL_NAME'])}</ges:nombreCompleto>
                <ges:pais>{fn:data($row/ns1:Column[@name ='COUNTRY'])}</ges:pais>
                <ges:xml>{fn-bea:inlinedXML(fn:data($row/ns1:Column[@name ='XML']))}</ges:xml>
            </ges:aviso>
        }
        </ns2:AvisoCobro>
        {
            let $count := count($avisos/ns1:P_LISTA_AVISOS/ns1:Row)
            return
            <ns2:Resultado>
                <res:result>
                    {
                        if($count > 0) 
                        then 
                            "OK" 
                        else 
                            "ERROR"
                    }
                </res:result>
                <res:message>
                    {
                        if($count > 0) 
                        then 
                            "Obtener avisos de cobro realizado exitosamente"
                        else 
                            "No se han encontrado registros para los parametros indicados"
                    }
                </res:message>
            </ns2:Resultado>
        }
    </ns2:CrearAvisoCobroV2Response>
};

local:func($avisos)
