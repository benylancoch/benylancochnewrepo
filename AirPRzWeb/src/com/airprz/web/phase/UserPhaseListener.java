package com.airprz.web.phase;

import java.io.IOException;

import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.airprz.web.model.UserBean;

public class UserPhaseListener implements PhaseListener {
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
	@Override
	public void beforePhase(PhaseEvent pe) {
		FacesContext facesContext = pe.getFacesContext();
		ExternalContext externalContext = facesContext.getExternalContext();
		if (!"/login.xhtml".equals(externalContext.getRequestPathInfo()) && !"/register.xhtml".equals(externalContext.getRequestPathInfo())) {
			Application application = facesContext.getApplication();
			UserBean userBean = (UserBean) application.evaluateExpressionGet(facesContext, "#{userBean}", UserBean.class);
			
			if (userBean == null || userBean.getUserId() == null ) {
				try {
					externalContext.redirect(externalContext.getRequestContextPath() + '/');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void afterPhase(PhaseEvent event) {

	}

}
