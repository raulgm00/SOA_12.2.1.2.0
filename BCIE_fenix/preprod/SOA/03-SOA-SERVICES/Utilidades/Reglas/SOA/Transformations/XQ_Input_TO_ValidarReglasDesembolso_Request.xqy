xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace reg="http://www.bcie.org/ReglaTareaMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ReglaTarea/V1/Schema/ReglaTareaMO.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg1 = "http://www.bcie.org/ReglaTareaBO";

declare namespace reg2 = "http://www.bcie.org/ReglaBO";

declare variable $inputVariable.request as element() (:: schema-element(reg:ValidarReglasRequest) ::) external;

declare function local:funcXq_input_to_validarreglasdesembolso_request($inputVariable.request as element() (:: schema-element(reg:ValidarReglasRequest) ::)) as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::) {
    <des:ValidarReglasDesembolsoRequest>
        <des:idDesembolso>{fn:data($inputVariable.request/reg:Parametros[com:name='ID_DESEMBOLSO']/com:value)}</des:idDesembolso>
        <des:instanciaProceso>{fn:data($inputVariable.request/reg:Parametros[com:name='INSTANCIA']/com:value)}</des:instanciaProceso>
        <des:TareaReglas>
            <reg1:IdTarea>{fn:data($inputVariable.request/reg:Parametros[com:name='ID_TAREA']/com:value)}</reg1:IdTarea>
            <reg1:Proceso>{fn:data($inputVariable.request/reg:Parametros[com:name='PROCESO']/com:value)}</reg1:Proceso>
            <reg1:Descripcion></reg1:Descripcion>
            <reg1:Estatus></reg1:Estatus>
            {
                for $Reglas in $inputVariable.request/reg:Reglas
                return 
                <reg1:ReglasEvaluacion>
                    <reg2:Id>{fn:data($Reglas/reg2:Id)}</reg2:Id>
                     <reg2:Descripcion>{fn:data($Reglas/reg2:Descripcion)}</reg2:Descripcion>
                     <reg2:Transaccion>{fn:data($Reglas/reg2:Transaccion)}</reg2:Transaccion>
                     <reg2:Tipo>
                       <cat:Id>{fn:data($Reglas/reg2:Tipo/cat:Id)}</cat:Id>
                       <cat:Descripcion>{fn:data($Reglas/reg2:Tipo/cat:Descripcion)}</cat:Descripcion>
                       <cat:DescripcionCorta>{fn:data($Reglas/reg2:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                       <cat:estatus>{fn:data($Reglas/reg2:Tipo/cat:estatus)}</cat:estatus>
                       <cat:codigoExterno>{fn:data($Reglas/reg2:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                     </reg2:Tipo>
                     <reg2:PosibleExceptuar>{fn:data($Reglas/reg2:PosibleExceptuar)}</reg2:PosibleExceptuar>
                     <reg2:Exceptuado>
                       <cat:Id>{fn:data($Reglas/reg2:Exceptuado/cat:Id)}</cat:Id>
                       <cat:Descripcion>{fn:data($Reglas/reg2:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                       <cat:DescripcionCorta>{fn:data($Reglas/reg2:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                       <cat:estatus>{fn:data($Reglas/reg2:Exceptuado/cat:estatus)}</cat:estatus>
                       <cat:codigoExterno>{fn:data($Reglas/reg2:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                     </reg2:Exceptuado>
                     <reg2:Estado>
                       <cat:Id>{fn:data($Reglas/reg2:Estado/cat:Id)}</cat:Id>
                       <cat:Descripcion>{fn:data($Reglas/reg2:Estado/cat:Descripcion)}</cat:Descripcion>
                       <cat:DescripcionCorta>{fn:data($Reglas/reg2:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                       <cat:estatus>{fn:data($Reglas/reg2:Estado/cat:estatus)}</cat:estatus>
                       <cat:codigoExterno>{fn:data($Reglas/reg2:Estado/cat:codigoExterno)}</cat:codigoExterno>
                     </reg2:Estado>
                    <reg2:Estatus>{fn:data($Reglas/reg2:Estatus)}</reg2:Estatus>
                    <reg1:EsAdvertencia>{fn:data($Reglas/reg1:EsAdvertencia)}</reg1:EsAdvertencia>
                    <reg1:EsError>{fn:data($Reglas/reg1:EsError)}</reg1:EsError>
                    <reg1:Mensaje>
                        <cat:Id>{fn:data($Reglas/reg1:Mensaje/cat:Id)}</cat:Id>
                        <cat:Descripcion>{fn:data($Reglas/reg1:Mensaje/cat:Descripcion)}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($Reglas/reg1:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        <cat:estatus>{fn:data($Reglas/reg1:Mensaje/cat:estatus)}</cat:estatus>
                        <cat:codigoExterno>{fn:data($Reglas/reg1:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                    </reg1:Mensaje>
                </reg1:ReglasEvaluacion>
                }
        </des:TareaReglas>
    </des:ValidarReglasDesembolsoRequest>
};

local:funcXq_input_to_validarreglasdesembolso_request($inputVariable.request)
