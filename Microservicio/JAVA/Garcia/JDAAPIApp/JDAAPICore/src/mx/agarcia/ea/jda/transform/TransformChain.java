package mx.agarcia.ea.jda.transform;

import java.io.PrintStream;

import java.nio.file.Files;
import java.nio.file.Paths;

import mx.agarcia.ea.jda.core.ActionException;
import mx.agarcia.ea.jda.core.ConnectChain;
import mx.agarcia.ea.jda.core.ExecutionException;
import mx.agarcia.ea.jda.core.IConnectAction;

/**
 *
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public class TransformChain extends ConnectChain<TransformPayload, TransformResponse> {
    
    public TransformChain(String pId) {
        super(pId);
    }




    public TransformResponse apply(TransformPayload payload) throws ExecutionException {
        TransformResponse response = null;
        TransformContext context = new TransformContext();
        System.out.println("[DEBUG] Iniciando ejecuci�n de Chain ");
        try {
            for (IConnectAction<TransformPayload, TransformResponse, TransformContext> action : this.actions) {
                System.out.println("[DEBUG] Preparando ejecuci�n de Action " + action.getId());
                response = (TransformResponse) action.execute(context, payload);
                System.out.println("[DEBUG] Actualizando Payload para Sgte Action " + response.getDocument() ) ;
                payload.setCurrentDoc(response.getDocument());
            }
        } catch (ActionException e) {
            super.error("No es posible ejecutar Chain", e);
        }
        return response;
    }

    protected void prepare(TransformPayload payload) throws ExecutionException {
    }


}
