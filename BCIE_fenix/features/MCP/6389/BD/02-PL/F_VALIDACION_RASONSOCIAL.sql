CREATE OR REPLACE FUNCTION F_VALIDACION_RASONSOCIAL
(
P_RasonSocial varchar2 ,
p_delimitador varchar2 
) RETURN VARCHAR2
IS
l_idx VARCHAR2 (250);
l_idxnum  VARCHAR2 (250);
v_rason varchar2(32767) := P_RasonSocial;
Al_value  varchar2(250);
BEGIN
loop                
l_idx := instr(v_rason,p_delimitador);           
IF l_idx > 0 THEN                   
Al_value:=substr(v_rason,1,l_idx-1);                      
v_rason := substr(v_rason,l_idx+LENGTH(p_delimitador));      
l_idxnum := LENGTH(Al_value);     

IF Al_value NOT IN ('COOPERATIVA', 'BANCO', 'INSTITUCI�N', 'FUNDACI�N', 'EMPRESA', 'FINANCIERA', 'FEDERACI�N', 'SOCIEDAD', 'UNIVERSIDAD', 'ASOCIACI�N', 'CORPORACI�N', 'COMERCIALIZADORA',
 'GOBIERNO', 'ESCUELA', 'INSTITUTO', 'REP�BLICA', 'GRUPO', 'SECRETAR�A', 'FONDO', 'COMPA��A', 'CAJA') AND l_idxnum>3  THEN 
     RETURN Al_value;
     EXIT;
     END IF;  
ELSE

IF Al_value  IN ('COOPERATIVA', 'BANCO', 'INSTITUCI�N', 'FUNDACI�N', 'EMPRESA', 'FINANCIERA', 'FEDERACI�N', 'SOCIEDAD', 'UNIVERSIDAD', 'ASOCIACI�N', 'CORPORACI�N', 'COMERCIALIZADORA',
 'GOBIERNO', 'ESCUELA', 'INSTITUTO', 'REP�BLICA', 'GRUPO', 'SECRETAR�A', 'FONDO', 'COMPA��A', 'CAJA') THEN 
     RETURN NULL;
     EXIT;
     
     ELSE Al_value:=Al_value;

exit;
     END IF;  
     

END IF;
END loop ;
RETURN Al_value;
END ;
/
