CREATE OR REPLACE PROCEDURE SP_MONEDA_CALENDARIO (P_ANIO NUMBER ) is
	   lst_dia     	VARCHAR2 (100);
	   lst_letra   	VARCHAR2 (100);
	   lst_cadena	  VARCHAR2 (50);	   
	   dia          DATE;
	   
	BEGIN
	
		DELETE FROM TCA_MONEDA_CALENDARIO WHERE ANIO = P_ANIO;
		COMMIT;

		FOR rec IN (SELECT CODIGO_MONEDA,
						  ANIO,
						  MES,
						  DIAS
					 FROM middle.FC_MV_MONEDA_CALENDARIO
					 WHERE  ANIO =P_ANIO
					 )
		LOOP
			lst_dia:=rec.DIAS;	
			
			FOR lin_pos IN 1 .. LENGTH (lst_dia)
		    LOOP
				lst_letra := SUBSTR (lst_dia, lin_pos, 1);		  
			  
				BEGIN
					dia:= TO_DATE(to_char(lin_pos) || '/' || to_char(rec.MES) || '/' || to_char(rec.ANIO), 'DD/MM/YYYY');
					-- * INGRESA A 4 POSICIONES  
					INSERT INTO  TCA_MONEDA_CALENDARIO (ID, CODIGO_MONEDA, MES, ANIO, DIA, TIPO) 
					VALUES (TCA_MONEDA_CALENDARIO_SEQ.NEXTVAL, rec.CODIGO_MONEDA, rec.MES, rec.ANIO, dia, lst_letra);
					
				EXCEPTION
					WHEN OTHERS
					THEN
						DBMS_OUTPUT.put_line ('Fecha a ingresar con error: ' || to_char(lin_pos) || '/' || to_char(rec.MES) || '/' || to_char(rec.ANIO) || ', para la moneda: ' || rec.CODIGO_MONEDA);
						DBMS_OUTPUT.put_line (SQLERRM);
						
				END;			
				-- DBMS_OUTPUT.put_line (dia);
			END LOOP;
		END LOOP;
		COMMIT;
	END;
	/

