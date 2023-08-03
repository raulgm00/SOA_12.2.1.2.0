xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/CondicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace mat="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)

declare namespace mat1 = "http://www.bcie.org/MatrizTCCBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare variable $outInvokeConsultarCondicionByIdEvento.response as element() (:: schema-element(con:ConsultarCondicionByIdEventoResponse) ::) external;

declare function local:funcConsultarcondicionbyidevento_to_actualizaresatdotcc_request($outInvokeConsultarCondicionByIdEvento.response as element() (:: schema-element(con:ConsultarCondicionByIdEventoResponse) ::)) as element() (:: schema-element(mat:ActualizarEstadoTCCRequest) ::) {
    <mat:ActualizarEstadoTCCRequest>
        <mat:ListaTCC>
          {for $condicion in $outInvokeConsultarCondicionByIdEvento.response/con:Condicion
          return
            <mat1:TCC>
                <mat1:id>{fn:data($condicion/con1:idCondicion)}</mat1:id>
                <mat1:tipo>CONDICION</mat1:tipo>
                <mat1:estado>23</mat1:estado>
                <mat1:subEstado>29</mat1:subEstado>
            </mat1:TCC>
          }  
        </mat:ListaTCC>
    </mat:ActualizarEstadoTCCRequest>
};

local:funcConsultarcondicionbyidevento_to_actualizaresatdotcc_request($outInvokeConsultarCondicionByIdEvento.response)
