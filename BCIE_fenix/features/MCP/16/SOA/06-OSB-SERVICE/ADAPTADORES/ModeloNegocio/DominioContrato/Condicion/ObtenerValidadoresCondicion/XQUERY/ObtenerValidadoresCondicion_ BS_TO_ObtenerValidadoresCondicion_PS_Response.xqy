xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ObtenerValidadoresCondicion_BS";
(:: import schema at "../XSD/ObtenerValidadoresCondicion_BS_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ObtenerValidadoresCondicionResponse as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($ObtenerValidadoresCondicionResponse as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ObtenerValidadoresCondicionResponse) ::) {

    <ns2:ObtenerValidadoresCondicionResponse>
    
        <ns2:Validadores>
        {
        for $VarObtenerValidadoresCondicion in $ObtenerValidadoresCondicionResponse/ns1:RS_VALIDADORES/ns1:Row
        return
            <ns2:Validador>
                <ns2:CodigoRol>{fn:data($VarObtenerValidadoresCondicion/ns1:Column[@name='CODIGO_ROL'])}</ns2:CodigoRol>
                <ns2:DescripcionRol>{fn:data($VarObtenerValidadoresCondicion/ns1:Column[@name='DESCRIPCION_ROL'])}</ns2:DescripcionRol>
                <ns2:NombreRol>{fn:data($VarObtenerValidadoresCondicion/ns1:Column[@name='NOMBRE_ROL'])}</ns2:NombreRol>
                <ns2:CodigoTarea>{fn:data($VarObtenerValidadoresCondicion/ns1:Column[@name='CODIGO_TAREA'])}</ns2:CodigoTarea>
                <ns2:NombreTarea>{fn:data($VarObtenerValidadoresCondicion/ns1:Column[@name='NOMBRE_TAREA'])}</ns2:NombreTarea>
                <ns2:TareaDescripcionCorta>{fn:data($VarObtenerValidadoresCondicion/ns1:Column[@name='TAREA_DESCRIPCION_CORTA'])}</ns2:TareaDescripcionCorta>
                <ns2:Pendiente>{fn:data($VarObtenerValidadoresCondicion/ns1:Column[@name='PENDIENTE'])}</ns2:Pendiente>
                <ns2:Validado>{fn:data($VarObtenerValidadoresCondicion/ns1:Column[@name='VALIDADO'])}</ns2:Validado>
                <ns2:PendienteN2>{fn:data($VarObtenerValidadoresCondicion/ns1:Column[@name='PENDIENTE_N2'])}</ns2:PendienteN2>
                <ns2:NombreValidadores>{fn:data($VarObtenerValidadoresCondicion/ns1:Column[@name='VALIDADORES'])}</ns2:NombreValidadores>
            </ns2:Validador>
        }
        </ns2:Validadores>
        <ns2:Resultado>
            <res:result></res:result>
            <res:message></res:message>
        </ns2:Resultado>
    </ns2:ObtenerValidadoresCondicionResponse>
};

local:func($ObtenerValidadoresCondicionResponse)
