xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $ConsultarCondicionValidacionByRolResponse as element() (:: schema-element(ns1:ConsultarCondicionValidacionByRolResponse) ::) external;

declare function local:func($ConsultarCondicionValidacionByRolResponse as element() (:: schema-element(ns1:ConsultarCondicionValidacionByRolResponse) ::)) as element() (:: schema-element(ns1:ConsultarValidacionCondicionByRolSJResponse) ::) {
    <ns1:ConsultarValidacionCondicionByRolSJResponse>
    { 
    for $condicion in $ConsultarCondicionValidacionByRolResponse/ns1:Condicion
    return
        <ns1:Condicion>
            <ns1:Condicion>
                <con:idCondicion>{fn:data($condicion/ns1:Condicion/con:idCondicion)}</con:idCondicion>
                <con:operacion>{fn:data($condicion/ns1:Condicion/con:operacion)}</con:operacion>
                <con:nombre>{fn:data($condicion/ns1:Condicion/con:nombre)}</con:nombre>
                <con:descripcion>{fn:data($condicion/ns1:Condicion/con:descripcion)}</con:descripcion>
                {
                    for $categoriaCondicion in $condicion/ns1:Condicion/con:categoriaCondicion
                    return 
                    <con:categoriaCondicion>
                        <con:id>{fn:data($categoriaCondicion/con:id)}</con:id>
                        <con:descripcion>{fn:data($categoriaCondicion/con:descripcion)}</con:descripcion>
                        <con:descripcionCorta>{fn:data($categoriaCondicion/con:descripcionCorta)}</con:descripcionCorta>
                        <con:estatus>{fn:data($categoriaCondicion/con:estatus)}</con:estatus>
                        <con:codigoExterno>{fn:data($categoriaCondicion/con:codigoExterno)}</con:codigoExterno>
                        {
                            for $validadores in $categoriaCondicion/con:validadores
                            return 
                            <con:validadores>
                                <cat:Id>{fn:data($validadores/cat:Id)}</cat:Id>
                                <cat:Descripcion>{fn:data($validadores/cat:Descripcion)}</cat:Descripcion>
                                <cat:DescripcionCorta>{fn:data($validadores/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                <cat:estatus>{fn:data($validadores/cat:estatus)}</cat:estatus>
                                <cat:codigoExterno>{fn:data($validadores/cat:codigoExterno)}</cat:codigoExterno>
                            </con:validadores>
                        }
                    </con:categoriaCondicion>
                }
               
                        <con:estadoTCC>
                            <atr:id>{fn:data($condicion/ns1:Condicion/con:estadoTCC/atr:id)}</atr:id>
                            <atr:descripcion>{fn:data($condicion/ns1:Condicion/con:estadoTCC/atr:descripcion)}</atr:descripcion>
                            <atr:descripcionCorta>{fn:data($condicion/ns1:Condicion/con:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                            <atr:estatus>{fn:data($condicion/ns1:Condicion/con:estadoTCC/atr:estatus)}</atr:estatus>
                            <atr:codigoExterno>{fn:data($condicion/ns1:Condicion/con:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                            <atr:esSubEstado>{fn:data($condicion/ns1:Condicion/con:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                        </con:estadoTCC>
                
                    
                
            </ns1:Condicion>
            {
                for $ValidacionCondicion in $condicion/ns1:ValidacionCondicion
                return 
                <ns1:ValidacionCondicion>
                    <con:idCondicion>{fn:data($ValidacionCondicion/con:idCondicion)}</con:idCondicion>
                    <con:rolBPM>
                        <cat:Id>{fn:data($ValidacionCondicion/con:rolBPM/cat:Id)}</cat:Id>
                    </con:rolBPM>
                    <con:loginUsuario>{fn:data($ValidacionCondicion/con:loginUsuario)}</con:loginUsuario>
                    <con:nombreUsuario>{fn:data($ValidacionCondicion/con:nombreUsuario)}</con:nombreUsuario>
                    <con:observacion>
                         <con:id>{fn:data($ValidacionCondicion/con:observacion/con:id)}</con:id>
                    </con:observacion>
                    <con:esValidador>{fn:data($ValidacionCondicion/con:esValidador)}</con:esValidador>
                    <con:estado>{fn:data($ValidacionCondicion/con:estado)}</con:estado>
                    <con:fechaRegistro>{fn:data($ValidacionCondicion/con:fechaRegistro)}</con:fechaRegistro>
                </ns1:ValidacionCondicion>
            }
        </ns1:Condicion>
    }
    </ns1:ConsultarValidacionCondicionByRolSJResponse>
};

local:func($ConsultarCondicionValidacionByRolResponse)
