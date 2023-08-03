CREATE OR REPLACE FUNCTION base64encoder(p_blob IN BLOB)
RETURN CLOB
IS
l_clob CLOB;
l_step PLS_INTEGER := 12000; -- make sure you set a multiple of 3 not higher than 24573
BEGIN
FOR i IN 0 .. TRUNC((DBMS_LOB.getlength(p_blob) - 1 )/l_step) LOOP
l_clob := l_clob || UTL_RAW.cast_to_varchar2(UTL_ENCODE.base64_encode(DBMS_LOB.substr(p_blob, l_step, i * l_step + 1)));
END LOOP;
RETURN l_clob;


EXCEPTION
WHEN OTHERS THEN

l_clob :=NULL;

RETURN l_clob;

END;
/
