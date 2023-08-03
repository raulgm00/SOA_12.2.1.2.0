xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $ConsultarTreCategoriaByIdCondicionResponse as element() (:: schema-element(ns1:ConsultarTreCategoriaByIdCondicionResponse) ::) external;
declare variable $ConsultarCondicionByIdOperacionResponse as element() (:: schema-element(ns1:ConsultarCondicionByIdOperacionResponse) ::) external;

declare function local:func($ConsultarTreCategoriaByIdCondicionResponse as element() (:: schema-element(ns1:ConsultarTreCategoriaByIdCondicionResponse) ::), 
                            $ConsultarCondicionByIdOperacionResponse as element() (:: schema-element(ns1:ConsultarCondicionByIdOperacionResponse) ::)) 
                            as element() (:: schema-element(ns1:ConsultarCondicionByIdOperacionResponse) ::) {


            <ns1:ListaCondiciones>
                <con:Condicion>
                {
                for $categoriaCondicion  in $ConsultarTreCategoriaByIdCondicionResponse/ns1:TreCategoriaCondicion
                return
                    <con:categoriaCondicion>
                        <con:id>{fn:data($categoriaCondicion/con:id)}</con:id>
                        <con:descripcion>{fn:data($categoriaCondicion/con:descripcion)}</con:descripcion>
                        <con:descripcionCorta>{fn:data($categoriaCondicion/con:descripcionCorta)}</con:descripcionCorta>
                        <con:estatus>{fn:data($categoriaCondicion/con:estatus)}</con:estatus>
                        <con:codigoExterno>{fn:data($categoriaCondicion/con:codigoExterno)}</con:codigoExterno>
                    </con:categoriaCondicion>
                  }
                </con:Condicion>
            </ns1:ListaCondiciones>

       
       
   
};

local:func($ConsultarTreCategoriaByIdCondicionResponse, $ConsultarCondicionByIdOperacionResponse)
