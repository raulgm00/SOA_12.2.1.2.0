
CREATE OR REPLACE TYPE T_ID_DOCUMENTO IS TABLE OF NUMBER;
/
CREATE OR REPLACE TYPE T_ID_TAREA_BPM IS TABLE OF NUMBER;
/

CREATE OR REPLACE PROCEDURE SP_DOCTOS_RESTRINGIDOS(
	P_ID_DOCUMENTO IN T_ID_DOCUMENTO,
	P_ID_TAREA_BPM IN T_ID_TAREA_BPM,
	P_CODIGO OUT VARCHAR2,
	P_MENSAJE OUT VARCHAR2) 	
IS
    V_ID_DOCUMENTO VARCHAR (4000);
	V_ID_TAREA_BPM VARCHAR (4000);
	CADENA VARCHAR (200);
	
    BEGIN
		-- RESTRICCION DE TIPOS DE DOCUMENTOS
		FOR IND IN 1 .. P_ID_DOCUMENTO.COUNT
			LOOP
				V_ID_DOCUMENTO :=  V_ID_DOCUMENTO ||  P_ID_DOCUMENTO(IND) || ', ';
			END LOOP;
			
		FOR IND IN 1 .. P_ID_TAREA_BPM.COUNT
			LOOP
				V_ID_TAREA_BPM :=  V_ID_TAREA_BPM ||  P_ID_TAREA_BPM(IND) || ', ';
			END LOOP;
		
		IF (LENGTH(V_ID_DOCUMENTO) > 0) THEN
			V_ID_DOCUMENTO := LPAD(V_ID_DOCUMENTO, LENGTH(V_ID_DOCUMENTO)-2);
		END IF;
		IF (LENGTH(V_ID_TAREA_BPM) > 0) THEN
			V_ID_TAREA_BPM := LPAD(V_ID_TAREA_BPM, LENGTH(V_ID_TAREA_BPM)-2);
		END IF;

	-- SE RESTRINGE EL TIPO DE DOCUMENTO 1031-Borrador de Reporte de Calificación Inicial de Riesgo, para que solo pueda ser considerado en la tarea 20, 21 y 22		
        -- SE ASIGNAN LOS PERMISOS PARA LOS DOCTOS RESTRINGIDOS EN LAS TAREAS BPM
        CADENA := 'UPDATE TCO_DOCUMENTO_TAREA SET PUEDE_CREAR=1, PUEDE_MODIFICAR=1, PUEDE_BORRAR=1,  PUEDE_CONSULTAR = 1 WHERE  ID_TCA_DOCUMENTO IN (' || V_ID_DOCUMENTO || ') AND  ID_TAREA_BPM  IN (' || V_ID_TAREA_BPM || ')';
		EXECUTE IMMEDIATE CADENA;
		
        -- SE ELIMINAN LOS PERMISOS PARA LOS DOCTOS RESTRINGIDOS EN LAS TAREAS BPM
		CADENA := 'UPDATE TCO_DOCUMENTO_TAREA SET PUEDE_CREAR=0, PUEDE_MODIFICAR=0, PUEDE_BORRAR=0,  PUEDE_CONSULTAR = 0 WHERE  ID_TCA_DOCUMENTO IN (' || V_ID_DOCUMENTO || ') AND  ID_TAREA_BPM NOT IN (' || V_ID_TAREA_BPM || ')';
		EXECUTE IMMEDIATE CADENA;
        
	    P_CODIGO := 0;
		P_MENSAJE := 'Procedimiento  ejecutado  correctamente';
  
  EXCEPTION 
  WHEN OTHERS
		THEN 
		P_CODIGO := 1;
		P_MENSAJE := SQLERRM;
END;
/





