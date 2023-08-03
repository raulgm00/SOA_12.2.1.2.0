xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearEnmiendasTCC";
(:: import schema at "../XSD/CrearEnmiendasTCC_table.xsd" ::)

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare variable $CrearEnmiendaTCCRequest as element() (:: schema-element(ns1:CrearEnmiendaTCCRequest) ::) external;

declare function local:func($CrearEnmiendaTCCRequest as element() (:: schema-element(ns1:CrearEnmiendaTCCRequest) ::)) as element() (:: schema-element(ns2:EnmiendaTccCollection) ::) {
    <ns2:EnmiendaTccCollection>
        <ns2:EnmiendaTcc>
            <ns2:idOperacion>{fn:data($CrearEnmiendaTCCRequest/ns1:enmiendaTCC/mat:idOperacion)}</ns2:idOperacion>
            <ns2:instanciaProceso>{fn:data($CrearEnmiendaTCCRequest/ns1:enmiendaTCC/mat:instanciaProceso)}</ns2:instanciaProceso>
            <ns2:requiereOgc>{fn:data($CrearEnmiendaTCCRequest/ns1:enmiendaTCC/mat:requiereOGC)}</ns2:requiereOgc>
            <ns2:requiereAsjur>{fn:data($CrearEnmiendaTCCRequest/ns1:enmiendaTCC/mat:requiereASJUR)}</ns2:requiereAsjur>
            <ns2:fechaRegistro>{fn:current-dateTime()}</ns2:fechaRegistro>
            <ns2:banEstatus>1</ns2:banEstatus>
			<ns2:banFormalizacion>{fn:data($CrearEnmiendaTCCRequest/ns1:enmiendaTCC/mat:estado_formalizacion)}</ns2:banFormalizacion>
			<ns2:requiereCOF>{fn:data($CrearEnmiendaTCCRequest/ns1:enmiendaTCC/mat:requiereCOF)}</ns2:requiereCOF>
			<ns2:esDesobligacion>{fn:data($CrearEnmiendaTCCRequest/ns1:enmiendaTCC/mat:esDesobligacion)}</ns2:esDesobligacion>
			<ns2:esCambioPlazo>{fn:data($CrearEnmiendaTCCRequest/ns1:enmiendaTCC/mat:esCambioPlazo)}</ns2:esCambioPlazo>
		</ns2:EnmiendaTcc>
    </ns2:EnmiendaTccCollection>
};

local:func($CrearEnmiendaTCCRequest)