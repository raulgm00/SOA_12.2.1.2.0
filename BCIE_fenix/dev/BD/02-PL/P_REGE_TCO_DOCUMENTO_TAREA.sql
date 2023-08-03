
CREATE OR REPLACE PROCEDURE P_REGE_TCO_DOCUMENTO_TAREA AS

	conteo number ;

    CURSOR tareas_cur
    IS
		SELECT id
        FROM TCA_TAREA_BPM
        ORDER BY 1 ASC;

	CURSOR docs_cur
	IS
		SELECT id
        FROM TCA_DOCUMENTO
        WHERE (ID>999 or ID=0)
		ORDER BY 1 ASC;

	tarea   tareas_cur%ROWTYPE;
	doc  docs_cur%ROWTYPE;
	CADENA VARCHAR2(200);
	idDocto number;
	
	BEGIN
		OPEN tareas_cur;
			LOOP
				FETCH tareas_cur INTO tarea;
					EXIT WHEN tareas_cur%NOTFOUND;
						OPEN docs_cur;
							LOOP
								FETCH docs_cur INTO doc;
									EXIT WHEN docs_cur%NOTFOUND;
										idDocto := doc.ID;
										
										
										SELECT count(*) cont  into conteo  from TCO_DOCUMENTO_TAREA where ID_TCA_DOCUMENTO=doc.ID and ID_TAREA_BPM=tarea.ID;
										if (conteo=0)
										then 
											-- SE INDICA EL ID DEL DOCTO RESTRINGIDO, SE CREA EL REGISTRO PERO CON EL PERMISO DE CONSULTA EN 0
											If ((idDocto = 1022) OR (idDocto = 1026) OR (idDocto = 1028) OR (idDocto = 1029) OR (idDocto = 1031)) THEN
												--DBMS_OUTPUT.put_line (doc.ID|| tarea.ID );
												CADENA :='INSERT INTO TCO_DOCUMENTO_TAREA(ID, ID_TCA_DOCUMENTO, ID_TAREA_BPM, PUEDE_CREAR, PUEDE_MODIFICAR, PUEDE_BORRAR, PUEDE_CONSULTAR) '||
														'VALUES ( TCO_DOCUMENTO_TAREA_SEQ.NEXTVAL, '||doc.ID||', '||tarea.ID||', 0, 0, 0, 0)';
												EXECUTE IMMEDIATE CADENA;
											ELSE
												--DBMS_OUTPUT.put_line (doc.ID|| tarea.ID );
												CADENA :='INSERT INTO TCO_DOCUMENTO_TAREA(ID, ID_TCA_DOCUMENTO, ID_TAREA_BPM, PUEDE_CREAR, PUEDE_MODIFICAR, PUEDE_BORRAR, PUEDE_CONSULTAR) '||
														'VALUES ( TCO_DOCUMENTO_TAREA_SEQ.NEXTVAL, '||doc.ID||', '||tarea.ID||', 0, 0, 0, 1)';
												EXECUTE IMMEDIATE CADENA;
											END IF;
										end if;                      
							END LOOP;
						CLOSE docs_cur;
			END LOOP;
		CLOSE tareas_cur;
	END;