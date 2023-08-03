package org.bcie.fenix.common.events;

public interface ProcessContextualEventInterface {
    public void processOnRefresh(Object payload,Object temp);
    public void processOnChange(Object payload,Object temp);
    public void processOnClick(Object payload,Object temp);
}
