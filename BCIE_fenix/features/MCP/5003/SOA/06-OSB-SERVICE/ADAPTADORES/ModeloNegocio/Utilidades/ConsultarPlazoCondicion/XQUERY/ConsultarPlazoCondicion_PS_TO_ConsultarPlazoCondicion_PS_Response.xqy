xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns3="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTerminoByIdOperacion";
(:: import schema at "../XSD/ConsultarTerminoByIdOperacion.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare variable $ConsultarTerminoByIdOperacionOutputCollection as element() (:: schema-element(ns1:ConsultarTerminoByIdOperacionOutputCollection) ::) external;
declare variable $ConsultarCondicionByRolResponse as element() (:: schema-element(ns2:ConsultarCondicionByRolResponse) ::) external;

declare function local:func($ConsultarTerminoByIdOperacionOutputCollection as element() (:: schema-element(ns1:ConsultarTerminoByIdOperacionOutputCollection) ::), 
                            $ConsultarCondicionByRolResponse as element() (:: schema-element(ns2:ConsultarCondicionByRolResponse) ::)) 
                            as element() (:: schema-element(ns3:ConsultarPlazoCondicionResponse) ::) {
    <ns3:ConsultarPlazoCondicionResponse>
    {
    for $termino in $ConsultarTerminoByIdOperacionOutputCollection/ns1:ConsultarTerminoByIdOperacionOutput
    return
        <ns3:listaTermino>
            <ter:idTermino>{fn:data($termino/ns1:ID_TERMINO)}</ter:idTermino>
            <ter:operacion>{fn:data($termino/ns1:ID_OPERACION)}</ter:operacion>
            <ter:nombre></ter:nombre>
            <ter:descripcion></ter:descripcion>
            <ter:tipoTermino>
                <ter:idCatTermino>{fn:data($termino/ns1:ID_TCA_TERMINO)}</ter:idCatTermino>
            </ter:tipoTermino>
            <ter:tipoFechaInicio>
                <cat:Id>{fn:data($termino/ns1:ID_TCA_TIPO_FECHA_INICIO)}</cat:Id>
                <cat:Descripcion>{fn:data($termino/ns1:TIPO_FECHA_INICIO)}</cat:Descripcion>
            </ter:tipoFechaInicio>
            <ter:fechaInicio>{fn:data($termino/ns1:FECHA_INICIO)}</ter:fechaInicio>
            <ter:plazo>{fn:data($termino/ns1:PLAZO)}</ter:plazo>
            <ter:frecuenciaPlazo>
                <cat:Id>{fn:data($termino/ns1:ID_TIPO_PLAZO)}</cat:Id>
                <cat:Descripcion>{fn:data($termino/ns1:TIPO_PLAZO)}</cat:Descripcion>
            </ter:frecuenciaPlazo>
            <ter:estadoTCC>
                <atr:id>{fn:data($termino/ns1:ID_TCA_ESTADO)}</atr:id>
            </ter:estadoTCC>
            <ter:fechaRegistro>{fn:data($termino/ns1:FECHA_REGISTRO)}</ter:fechaRegistro>
            <ter:estado>{
             if(fn:data($termino/ns1:ESTADO)= 1) 
             then true()
             else false()}</ter:estado>
        </ns3:listaTermino>
        }
        {
            for $Condicion in $ConsultarCondicionByRolResponse/ns2:Condicion
            return 
            <ns3:listaCondicion>
                <con:idCondicion>{fn:data($Condicion/con:idCondicion)}</con:idCondicion>
                <con:operacion>{fn:data($Condicion/con:operacion)}</con:operacion>
                {
                    if ($Condicion/con:nombre)
                    then <con:nombre>{fn:data($Condicion/con:nombre)}</con:nombre>
                    else ()
                }
                {
                    if ($Condicion/con:descripcion)
                    then <con:descripcion>{fn:data($Condicion/con:descripcion)}</con:descripcion>
                    else ()
                }
                {
                    if ($Condicion/con:tipoCondicion)
                    then 
                        <con:tipoCondicion>
                            {
                                if ($Condicion/con:tipoCondicion/con:idCatCondicion)
                                then <con:idCatCondicion>{fn:data($Condicion/con:tipoCondicion/con:idCatCondicion)}</con:idCatCondicion>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoCondicion/con:descripcion)
                                then <con:descripcion>{fn:data($Condicion/con:tipoCondicion/con:descripcion)}</con:descripcion>
                                else ()
                            }
                        </con:tipoCondicion>
                    else ()
                }
                {
                    for $categoriaCondicion in $Condicion/con:categoriaCondicion
                    return 
                    <con:categoriaCondicion>
                        <con:id>{fn:data($categoriaCondicion/con:id)}</con:id>
                        <con:descripcion>{fn:data($categoriaCondicion/con:descripcion)}</con:descripcion>
                        {
                            if ($categoriaCondicion/con:descripcionCorta)
                            then <con:descripcionCorta>{fn:data($categoriaCondicion/con:descripcionCorta)}</con:descripcionCorta>
                            else ()
                        }
                    </con:categoriaCondicion>
                }
                {
                    if ($Condicion/con:controlCondicion)
                    then 
                        <con:controlCondicion>
                            {
                                if ($Condicion/con:controlCondicion/cat:Id)
                                then <cat:Id>{fn:data($Condicion/con:controlCondicion/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Condicion/con:controlCondicion/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Condicion/con:controlCondicion/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                        </con:controlCondicion>
                    else ()
                }
                {
                    for $eventoCondicion in $Condicion/con:eventoCondicion
                    return 
                    <con:eventoCondicion>
                        {
                            if ($eventoCondicion/cat:Id)
                            then <cat:Id>{fn:data($eventoCondicion/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($eventoCondicion/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($eventoCondicion/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                    </con:eventoCondicion>
                }
                {
                    if ($Condicion/con:tipoFechaInicio)
                    then 
                        <con:tipoFechaInicio>
                            {
                                if ($Condicion/con:tipoFechaInicio/cat:Id)
                                then <cat:Id>{fn:data($Condicion/con:tipoFechaInicio/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoFechaInicio/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Condicion/con:tipoFechaInicio/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                        </con:tipoFechaInicio>
                    else ()
                }
                {
                    if ($Condicion/con:fechaInicio)
                    then <con:fechaInicio>{fn:data($Condicion/con:fechaInicio)}</con:fechaInicio>
                    else ()
                }
                {
                    if ($Condicion/con:plazo)
                    then <con:plazo>{fn:data($Condicion/con:plazo)}</con:plazo>
                    else ()
                }
                {
                    if ($Condicion/con:frecuenciaPlazo)
                    then 
                        <con:frecuenciaPlazo>
                            {
                                if ($Condicion/con:frecuenciaPlazo/cat:Id)
                                then <cat:Id>{fn:data($Condicion/con:frecuenciaPlazo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Condicion/con:frecuenciaPlazo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Condicion/con:frecuenciaPlazo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                        </con:frecuenciaPlazo>
                    else ()
                }
                {
                    if ($Condicion/con:fechaFinal)
                    then <con:fechaFinal>{fn:data($Condicion/con:fechaFinal)}</con:fechaFinal>
                    else ()
                }
                {
                    if ($Condicion/con:estadoTCC)
                    then 
                        <con:estadoTCC>
                            {
                                if ($Condicion/con:estadoTCC/atr:id)
                                then <atr:id>{fn:data($Condicion/con:estadoTCC/atr:id)}</atr:id>
                                else ()
                            }
                            {
                                if ($Condicion/con:estadoTCC/atr:descripcion)
                                then <atr:descripcion>{fn:data($Condicion/con:estadoTCC/atr:descripcion)}</atr:descripcion>
                                else ()
                            }
                        </con:estadoTCC>
                    else ()
                }
                {
                    if ($Condicion/con:fechaRegistro)
                    then <con:fechaRegistro>{fn:data($Condicion/con:fechaRegistro)}</con:fechaRegistro>
                    else ()
                }
                {
                    if ($Condicion/con:estado)
                    then <con:estado>{fn:data($Condicion/con:estado)}</con:estado>
                    else ()
                }
                {
                    for $observaciones in $Condicion/con:observaciones
                    return 
                    <con:observaciones>
                        <con:id>{fn:data($observaciones/con:id)}</con:id>
                        {
                            if ($observaciones/con:observacion)
                            then <con:observacion>{fn:data($observaciones/con:observacion)}</con:observacion>
                            else ()
                        }
                        {
                            if ($observaciones/con:loginUsuario)
                            then <con:loginUsuario>{fn:data($observaciones/con:loginUsuario)}</con:loginUsuario>
                            else ()
                        }
                        {
                            if ($observaciones/con:nombreUsuario)
                            then <con:nombreUsuario>{fn:data($observaciones/con:nombreUsuario)}</con:nombreUsuario>
                            else ()
                        }
                        {
                            if ($observaciones/con:fechaRegistro)
                            then <con:fechaRegistro>{fn:data($observaciones/con:fechaRegistro)}</con:fechaRegistro>
                            else ()
                        }
                        {
                            if ($observaciones/con:estatus)
                            then <con:estatus>{fn:data($observaciones/con:estatus)}</con:estatus>
                            else ()
                        }
                        {
                            if ($observaciones/con:esPrincipal)
                            then <con:esPrincipal>{fn:data($observaciones/con:esPrincipal)}</con:esPrincipal>
                            else ()
                        }
                        {
                            if ($observaciones/con:tareaBPM)
                            then 
                                <con:tareaBPM>
                                    {
                                        if ($observaciones/con:tareaBPM/cat:Id)
                                        then <cat:Id>{fn:data($observaciones/con:tareaBPM/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($observaciones/con:tareaBPM/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($observaciones/con:tareaBPM/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($observaciones/con:tareaBPM/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($observaciones/con:tareaBPM/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($observaciones/con:tareaBPM/cat:estatus)
                                        then <cat:estatus>{fn:data($observaciones/con:tareaBPM/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($observaciones/con:tareaBPM/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($observaciones/con:tareaBPM/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </con:tareaBPM>
                            else ()
                        }
                    </con:observaciones>
                }
            </ns3:listaCondicion>
        }
        <ns3:Result>
            <res:result>OK</res:result>
            {if (string(fn:data($ConsultarTerminoByIdOperacionOutputCollection/ns1:ConsultarTerminoByIdOperacionOutput[1]/ns1:ID_TERMINO)) != '')then
            <res:message>Operacion Exitosa</res:message>
            else
            <res:message>No existe plazo para esa operacion</res:message>
            }
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns3:Result>
    </ns3:ConsultarPlazoCondicionResponse>
};

local:func($ConsultarTerminoByIdOperacionOutputCollection, $ConsultarCondicionByRolResponse)
