xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearClasificacionAmbiental";
(:: import schema at "../XSD/CrearClasificacionAmbiental_table.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $responseClasificacionAmbiental as element() (:: schema-element(ns1:ClasificacionAmbientalCollection) ::) external;

declare function local:func($responseClasificacionAmbiental as element() (:: schema-element(ns1:ClasificacionAmbientalCollection) ::)) as element() (:: schema-element(ns2:CrearClasificacionAmbientalResponse) ::) {
    <ns2:CrearClasificacionAmbientalResponse>
        <ns2:ClasificacionesAmbiental>
            {
                for $ClasificacionAmbiental in $responseClasificacionAmbiental/ns1:ClasificacionAmbiental
                return 
                <ope:clasificacionAmbiental>
                    <ope:id>{fn:data($ClasificacionAmbiental/ns1:id)}</ope:id>
                    {
                        if ($ClasificacionAmbiental/ns1:idOperacion)
                        then <ope:idOperacion>{fn:data($ClasificacionAmbiental/ns1:idOperacion)}</ope:idOperacion>
                        else ()
                    }
                    
                       <ope:sectorAmbiental>
                        {
                            if ($ClasificacionAmbiental/ns1:idTcaSectorAmbiental)
                            then <cat:Id>{fn:data($ClasificacionAmbiental/ns1:idTcaSectorAmbiental)}</cat:Id>
                            else ()
                        }</ope:sectorAmbiental>

                        <ope:aporteAmbiental>
                        {
                            if ($ClasificacionAmbiental/ns1:idTcaAporteAmbiental)
                            then <cat:Id>{fn:data($ClasificacionAmbiental/ns1:idTcaAporteAmbiental)}</cat:Id>
                            else ()
                        }</ope:aporteAmbiental>
                    
                    <ope:categoriaAmbiental>
                        {
                            if ($ClasificacionAmbiental/ns1:idTcaCategoriaAmbiental)
                            then <cat:Id>{fn:data($ClasificacionAmbiental/ns1:idTcaCategoriaAmbiental)}</cat:Id>
                            else ()
                        }</ope:categoriaAmbiental>
                    <ope:subCategoriaAmbiental>
                        {
                            if ($ClasificacionAmbiental/ns1:idTcaSubcategoriaAmbiental)
                            then <cat:Id>{fn:data($ClasificacionAmbiental/ns1:idTcaSubcategoriaAmbiental)}</cat:Id>
                            else ()
                        }</ope:subCategoriaAmbiental>
                    
                    
                    
                     
                    
                    
                    {
                        if ($ClasificacionAmbiental/ns1:banEstatus)
                        then <ope:banStatus>{fn:data($ClasificacionAmbiental/ns1:banEstatus)}</ope:banStatus>
                        else ()
                    }
                    {
                        if ($ClasificacionAmbiental/ns1:fechaRegistro)
                        then <ope:fechaRegistro>{fn:data($ClasificacionAmbiental/ns1:fechaRegistro)}</ope:fechaRegistro>
                        else ()
                    }
                    
             
                    
                    </ope:clasificacionAmbiental>
                    
                    
                    
            }</ns2:ClasificacionesAmbiental>
        
        
                    <ns2:Resultado>
            <res:result>Ok</res:result>
            <res:message>Creación realizada correctamente</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
        
        
        
        </ns2:CrearClasificacionAmbientalResponse>
};

local:func($responseClasificacionAmbiental)